package com.cnab.processor.service;

import com.cnab.processor.config.StorageFileConfig;
import com.cnab.processor.exceptions.response.ErroFile;
import com.cnab.processor.process.ProcessorFile;
import com.cnab.processor.response.Data;
import com.cnab.processor.response.ProcessorResponse;
import com.cnab.processor.response.Transaction;
import com.cnab.processor.validators.*;
import lombok.SneakyThrows;
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

    @Autowired
    private ProcessorFile processor;

    private ValidatorFileAggregator validatorFileAggregator;

    private List<Transaction> transactionList;

    private List<ErroFile> lstErroFile;

    private String idEmpresa ;


    @SneakyThrows
    public ProcessorResponse processorFile(MultipartFile file) {

        preProcessorFile(this.validateFile(file));
        processor.checkErro(lstErroFile);
        processor.saveTransactions(idEmpresa, transactionList );
        return response(transactionList);

    }
    private List<Object>  validateFile(MultipartFile file) throws IOException {

        File newFile = new File(Objects.requireNonNull(file.getOriginalFilename()));
        try (FileOutputStream fos = new FileOutputStream(newFile)) {
            fos.write(file.getBytes());
        }

        validatorFileAggregator = new ValidatorFileAggregator(setValidationsFile(), newFile);
        return validatorFileAggregator.validate();

    }


    private void preProcessorFile(List<Object> validatedFile){

            transactionList = validatedFile.stream()
                    .filter( obj -> obj instanceof Transaction )
                    .map(obj -> (Transaction) obj)
                    .toList();

            lstErroFile = validatedFile.stream()
                    .filter(  obj -> obj instanceof ErroFile )
                    .map(obj -> (ErroFile) obj)
                    .toList();

             idEmpresa = validatedFile.stream()
                    .filter(  obj -> obj instanceof String )
                    .map(Object::toString )
                    .collect(Collectors.joining());

    }

    private List<Validator> setValidationsFile(){
        List<Validator> validatorList = new ArrayList<>();
        validatorList.add(new HeaderValidator());
        validatorList.add(new FootValidator());
        validatorList.add(new TransactionValidator());

        return validatorList;
    }


    private ProcessorResponse response(List<Transaction> transactionList){
        return ProcessorResponse.builder()
                .data(Data.builder()
                        .transactions(transactionList)
                        .build())
                .status("success")
                .message("Arquivo CNAB enviado e processado com sucesso.")
                .build();
    }
}
