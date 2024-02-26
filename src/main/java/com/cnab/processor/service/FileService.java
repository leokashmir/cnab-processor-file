package com.cnab.processor.service;

import com.cnab.processor.config.StorageFileConfig;
import com.cnab.processor.exceptions.response.ErroFile;
import com.cnab.processor.response.Data;
import com.cnab.processor.response.ProcessorResponse;
import com.cnab.processor.response.Transaction;
import com.cnab.processor.validators.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class FileService {


    @Autowired
    private StorageFileConfig storageFileConfig;

    private ValidatorFileAggregator validatorFileAggregator;


    public ProcessorResponse validateFile(MultipartFile file) throws IOException {
        Path path =  storageFileConfig.getDirFileUpload();

        File newFile = new File(Objects.requireNonNull(file.getOriginalFilename()));
        try (FileOutputStream fos = new FileOutputStream(newFile)) {
            fos.write(file.getBytes());
        }


        validatorFileAggregator = new ValidatorFileAggregator(setValidationsFile(), newFile);
        processorFile(validatorFileAggregator.validate());


        return response();


    }


    private void processorFile( List<Object> validatedFile){

        if(!validatedFile.isEmpty()){

            List<Transaction> transactionList = validatedFile.stream()
                    .filter( obj -> obj instanceof Transaction )
                    .map(obj -> (Transaction) obj)
                    .toList();

            List<ErroFile> lstErroFile = validatedFile.stream()
                    .filter(  obj -> obj instanceof ErroFile )
                    .map(obj -> (ErroFile) obj)
                    .toList();

            String idEmpresa = validatedFile.stream()
                    .filter(  obj -> obj instanceof String )
                    .map(Object::toString )
                    .collect(Collectors.joining());



            System.out.println("IdEmpresa =>" + idEmpresa);
            System.out.println("Lista Erro tamanho =>" + lstErroFile.size());
            System.out.println("Lista transactionList tamanho =>" + transactionList.size());
        }





    }



    private List<Validator> setValidationsFile(){
        List<Validator> validatorList = new ArrayList<>();
        validatorList.add(new HeaderValidator());
        validatorList.add(new FootValidator());
        validatorList.add(new TransactionValidator());

        return validatorList;
    }


    private ProcessorResponse response(){
        List<Transaction> transactionList = new ArrayList<Transaction>();

        transactionList.add(
                Transaction.builder()
                        .value(123.9)
                        .type("C")
                        .accountOrigin("2394")
                        .accountDestination("342")
                        .build()

        );

        return ProcessorResponse.builder()
                .data(Data.builder()
                        .transactions(transactionList)
                        .build())
                .status("OK")
                .message("message")
                .build();
    }
}
