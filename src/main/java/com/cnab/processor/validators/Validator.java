package com.cnab.processor.validators;

import com.cnab.processor.exceptions.response.ErroFile;

import java.io.File;
import java.util.List;
import java.util.Optional;

public interface Validator {

     Optional<List<Object>> validate(File file);
}
