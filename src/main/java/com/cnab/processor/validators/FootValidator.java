package com.cnab.processor.validators;

import com.cnab.processor.exceptions.InvalidFileException;
import com.cnab.processor.util.ProcessorUtils;
import lombok.extern.log4j.Log4j2;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import java.util.Optional;
import java.util.stream.Stream;
@Log4j2
public class FootValidator implements Validator {

    /**
     * Valida o footer do arquivo
     *
     * @param file O arquivo a ser validado.
     * @return Um Optional.empty() se a validação for bem-sucedida e não houver erros de validação.
     * @throws InvalidFileException Se ocorrer um erro durante a validação do arquivo.
     */
    @Override
    public Optional<List<Object>> validate(File file ) {
        log.info(" ===== > Iniciando a validação do footer ");

        try (Stream<String> lines =  Files.lines(Path.of(file.getAbsolutePath()))) {

            var footer = lines.reduce((first, second) -> second);
            footer.ifPresent(this::footerLine);

        }catch (Exception e){
            log.error(" ===== > Erro na validação do footer ");
            throw new InvalidFileException();
        }
        log.info(" ===== > fim da validação do footer ");
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
