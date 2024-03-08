package com.cnab.processor.validators;

import com.cnab.processor.exceptions.InvalidFileException;
import com.cnab.processor.util.ProcessorUtils;
import lombok.extern.log4j.Log4j2;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.stream.Stream;
@Log4j2
public class HeaderValidator implements  Validator{

    /**
     * Valida o Header do arquivo
     *
     * @param file O arquivo a ser validado.
     * @return Um Optional.empty() se a validação for bem-sucedida e não houver erros de validação.
     * @throws InvalidFileException Se ocorrer um erro durante a validação do arquivo.
     */
    @Override
    public Optional<List<Object>> validate(File file ) {
        log.info(" ===== > Iniciando a validação do Header ");
        try (Stream<String> lines =  Files.lines(Path.of(file.getAbsolutePath()))) {
           var header = lines.findFirst().orElseThrow();
            headerLine(header);

        }catch (NoSuchElementException | InvalidFileException | IOException e){
            log.error(" ===== >Erro na validação do Header ");
            throw new InvalidFileException();
        }
        log.info(" ===== > Finalizndo a validação do Header ");
        return Optional.empty();
    }


    private void headerLine(String headerLine){

        if(!ProcessorUtils.isCorrectlength(headerLine)){
            throw new InvalidFileException();
        }else{

            if(!ProcessorUtils.isNumeric(headerLine.substring(0,3))){
                throw new InvalidFileException();
            };

            if(ProcessorUtils.isWhitespace(headerLine.substring(3,33))){
                throw new InvalidFileException();
            }
            if(!ProcessorUtils.isAlphanumericWithSpecialCharAndSpaces(headerLine.substring(3,33))){
                throw new InvalidFileException();
            }
            if(ProcessorUtils.isWhitespace(headerLine.substring(33,47))){
                throw new InvalidFileException();
            }
            if(!ProcessorUtils.isNumeric(headerLine.substring(33,47))){
                throw new InvalidFileException();
            }


        }

    }
}
