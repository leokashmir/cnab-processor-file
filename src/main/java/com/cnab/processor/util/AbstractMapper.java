package com.cnab.processor.util;

import com.cnab.processor.dto.TransactionDto;
import com.cnab.processor.model.Transaction;
import org.modelmapper.ModelMapper;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.List;
import java.util.stream.Collectors;

public abstract class AbstractMapper<S,T> {
    protected static final ModelMapper mapper = new ModelMapper();
    private final Class<S> entityClass;
    private final Class<T> dtoClass;

    public AbstractMapper() {
        final Type[] actualTypeArguments = ((ParameterizedType) this.getClass().getGenericSuperclass())
                .getActualTypeArguments();
        this.entityClass = (Class<S>) actualTypeArguments[0];
        this.dtoClass = (Class<T>) actualTypeArguments[1];
    }

    protected List<T> mapList(List<S> source, Class<T> targetClass) {
        return source
                .stream()
                .map(element -> mapper.map(element, targetClass))
                .collect(Collectors.toList());
    }


}
