package com.cnab.processor.validators;

import lombok.AllArgsConstructor;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@AllArgsConstructor
public class ValidatorFileAggregator {

    private final List<Validator> listValidations;

    private final File file;


    public List<Object> validate()  {
        List listResult = new ArrayList<>();

        listValidations.forEach(val -> {
            Optional<List<Object>> lst = val.validate(file);
            lst.ifPresent(listResult::addAll);

        } );
        return listResult;
    }

}
