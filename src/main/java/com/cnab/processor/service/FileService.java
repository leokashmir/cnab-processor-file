package com.cnab.processor.service;

import com.cnab.processor.dto.TransactionDto;
import com.cnab.processor.exceptions.InvalidFileException;
import com.cnab.processor.exceptions.response.ErroFile;
import com.cnab.processor.model.Company;
import com.cnab.processor.process.ProcessorFile;
import com.cnab.processor.response.Data;
import com.cnab.processor.response.ProcessorResponse;
import com.cnab.processor.validators.*;
import lombok.SneakyThrows;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
@Log4j2
@Service
public class FileService {


    @Autowired
    private ProcessorFile processor;
    private ValidatorFileAggregator validatorFileAggregator;
    private List<TransactionDto> transactionDtoList;
    private List<ErroFile> lstErroFile;
    private Company company ;


    /**
     * Processa um arquivo e retorna uma resposta do processador.
     *
     * @param file O arquivo a ser processado.
     * @return Uma resposta do processador contendo informações sobre o processamento do arquivo.
     * @throws InvalidFileException Se o arquivo não for válido.
     */
    public ProcessorResponse processorFile(MultipartFile file) {

        preProcessorFile(this.validateFile(file));
        processor.checkErro(lstErroFile);
        processor.saveTransactions(company, transactionDtoList);
        return response(transactionDtoList);

    }

    /**
     * Retorna uma lista de validadores de arquivos.
     *
     * @return Uma lista contendo instâncias de validadores de cabeçalho, rodapé e transações.
     */
    private List<Validator> validationsFileList(){
        List<Validator> validatorList = new ArrayList<>();
        validatorList.add(new HeaderValidator());
        validatorList.add(new FootValidator());
        validatorList.add(new TransactionValidator());

        return validatorList;
    }

    /**
     * Valida o arquivo fornecido e retorna uma lista de objetos validados.
     *
     * @param file O arquivo a ser validado, fornecido como um objeto MultipartFile.
     * @return Uma lista de objetos validados extraídos do arquivo.
     * @throws InvalidFileException Se ocorrer um erro durante a validação do arquivo.
     */

    private List<Object> validateFile(MultipartFile file)  {

        File newFile = new File(Objects.requireNonNull(file.getOriginalFilename()));
        try (FileOutputStream fos = new FileOutputStream(newFile)) {
            fos.write(file.getBytes());

        }catch (InvalidFileException | IOException i){
            throw new InvalidFileException();
        }

        validatorFileAggregator = new ValidatorFileAggregator(validationsFileList(), newFile);
        return validatorFileAggregator.validate();

    }

    /**
     * Pré-processa os dados validados do arquivo, Filtrando
     * e mapeando os objetos da lista validatedFile
     *
     * @param validatedFile A lista de objetos validados do arquivo.
     */
    @SneakyThrows
    private void preProcessorFile(List<Object> validatedFile){

            transactionDtoList = validatedFile.stream()
                    .filter( obj -> obj instanceof TransactionDto)
                    .map(obj -> (TransactionDto) obj)
                    .toList();

            lstErroFile = validatedFile.stream()
                    .filter(  obj -> obj instanceof ErroFile )
                    .map(obj -> (ErroFile) obj)
                    .toList();

            company = validatedFile.stream()
                    .filter(  obj -> obj instanceof Company)
                    .map(obj -> (Company) obj )
                    .findFirst().get();

    }

    private ProcessorResponse response(List<TransactionDto> transactionDtoList){
        return ProcessorResponse.builder()
                .data(Data.builder()
                        .transactionDtos(transactionDtoList)
                        .build())
                .status("success")
                .message("Arquivo CNAB enviado e processado com sucesso.")
                .build();
    }
}
