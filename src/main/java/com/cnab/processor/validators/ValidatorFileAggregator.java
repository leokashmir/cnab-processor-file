package com.cnab.processor.validators;

import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j2;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
@Log4j2
@AllArgsConstructor
public class ValidatorFileAggregator {

    private final List<Validator> listValidations;

    private final File file;

    /**
     * Valida o arquivo com base em uma lista de validações e retorna uma lista de resultados.
     *
     * @return Uma lista de objetos representando os resultados das validações.
     */
    public List<Object> validate()  {
        List listResult = new ArrayList<>();

        listValidations.forEach(val -> {
            Optional<List<Object>> lst = val.validate(file);
            lst.ifPresent(listResult::addAll);

        } );
        return listResult;
    }

}
