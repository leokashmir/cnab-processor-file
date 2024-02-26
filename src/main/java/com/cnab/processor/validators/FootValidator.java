package com.cnab.processor.validators;

import com.cnab.processor.exceptions.InvalidFileException;
import com.cnab.processor.util.ProcessorUtils;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import java.util.Optional;
import java.util.stream.Stream;

public class FootValidator implements Validator {


    @Override
    public Optional<List<Object>> validate(File file ) {


        try (Stream<String> lines =  Files.lines(Path.of(file.getAbsolutePath()))) {

            var footer = lines.reduce((first, second) -> second);
            footer.ifPresent(this::footerLine);

        }catch (Exception e){
            throw new InvalidFileException();
        }

        return Optional.empty();
    }


    private void footerLine(String headerLine){

        if(!ProcessorUtils.isCorrectlength(headerLine)){
            throw new InvalidFileException();
        }else{

            if(!ProcessorUtils.isNumeric(headerLine.substring(0,3))){
                    throw new InvalidFileException();
            };

        }

    }
}
