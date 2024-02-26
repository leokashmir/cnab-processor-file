package com.cnab.processor.validators;

import com.cnab.processor.exceptions.InvalidFileException;
import com.cnab.processor.util.ProcessorUtils;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import java.util.Optional;
import java.util.stream.Stream;

public class HeaderValidator implements  Validator{

    @Override
    public Optional<List<Object>> validate(File file ) {

        try (Stream<String> lines =  Files.lines(Path.of(file.getAbsolutePath()))) {
           var header = lines.findFirst();
           header.ifPresent(this::headerLine);

        }catch (InvalidFileException | IOException e){
            throw new InvalidFileException();
        }

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
            if(ProcessorUtils.isWhitespace(headerLine.substring(47,80))){
                throw new InvalidFileException();
            }

        }

        System.out.println("HEADER => " + headerLine.substring(3,33));
    }
}
