package com.cnab.processor.validators;

import org.junit.jupiter.api.Test;
import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class ValidatorFileAggregatorTest {

    @Test
    void noValidationsReturnsEmptyList() {

        List<Validator> listValidations = new ArrayList<>();
        File file = mock(File.class);
        ValidatorFileAggregator aggregator = new ValidatorFileAggregator(listValidations, file);

        List<Object> result = aggregator.validate();

        assertTrue(result.isEmpty());
    }

    @Test
    void withValidationsReturnsAggregatedResults() {

        Validator validator1 = mock(Validator.class);
        Validator validator2 = mock(Validator.class);

        List<Object> result1 = new ArrayList<>();
        result1.add("Result 1");

        List<Object> result2 = new ArrayList<>();
        result2.add("Result 2");

        when(validator1.validate(any())).thenReturn(Optional.of(result1));
        when(validator2.validate(any())).thenReturn(Optional.of(result2));

        List<Validator> listValidations = List.of(validator1, validator2);
        File file = mock(File.class);
        ValidatorFileAggregator aggregator = new ValidatorFileAggregator(listValidations, file);

        List<Object> result = aggregator.validate();

        assertEquals(2, result.size());
        assertEquals("Result 1", result.get(0));
        assertEquals("Result 2", result.get(1));
    }
}
