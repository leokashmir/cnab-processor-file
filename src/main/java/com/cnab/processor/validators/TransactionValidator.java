package com.cnab.processor.validators;

import com.cnab.processor.enumerator.TransactionErroEnum;
import com.cnab.processor.exceptions.InvalidFileException;
import com.cnab.processor.exceptions.response.ErroFile;
import com.cnab.processor.response.Transaction;
import com.cnab.processor.util.ProcessorUtils;
import lombok.SneakyThrows;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Stream;

public class TransactionValidator implements Validator {

    private List<Object> listTransactions;
    private static int numberLine;
    @Override
    public Optional<List<Object>> validate(File file) {
        listTransactions = new ArrayList<>();
         numberLine = 1;

        try (Stream<String> lines =  Files.lines(Path.of(file.getAbsolutePath()))) {
            lines.forEach(this::transactionLine);

        }catch (Exception e){
            throw new InvalidFileException();
        }

        return Optional.of(listTransactions);



    }

    @SneakyThrows
    private void transactionLine(String line){
        boolean addTransaction = true;
        numberLine++;

        if(!ProcessorUtils.isCorrectlength(line)){
            throw new InvalidFileException();
        }

        if("001".equals(line.substring(0,3))){
            listTransactions.add(line.substring(32,47));
        }

        if("002".equals(line.substring(0,3))){
            if(!ProcessorUtils.isNumeric(line.substring(0,3))){
                listTransactions.add(ErroFile.builder()
                        .error(TransactionErroEnum.IDENTIFICACAO_TIPO_REGISTRO.getValue())
                        .line(String.valueOf(numberLine))
                        .build());
                addTransaction = false;

            };
            if(!line.substring(3,4).matches("[CDT]")){
                listTransactions.add(ErroFile.builder()
                        .error(TransactionErroEnum.TIPO_TRANSACAO.getValue())
                        .line(String.valueOf(numberLine))
                        .build());
                addTransaction = false;


            };
            if(!ProcessorUtils.isNumeric(line.substring(4,20))){
                listTransactions.add(ErroFile.builder()
                        .error(TransactionErroEnum.VALOR_TRANSACAO.getValue())
                        .line(String.valueOf(numberLine))
                        .build());
                addTransaction = false;


            };
            if(!ProcessorUtils.isNumeric(line.substring(20,36))){
                listTransactions.add(ErroFile.builder()
                        .error(TransactionErroEnum.CONTA_ORIGEM.getValue())
                        .line(String.valueOf(numberLine))
                        .build());
                addTransaction = false;

            };
            if(!ProcessorUtils.isNumeric(line.substring(36,52))){
                listTransactions.add(ErroFile.builder()
                        .error(TransactionErroEnum.CONTA_DESTINO.getValue())
                        .line(String.valueOf(numberLine))
                        .build());
                addTransaction = false;

            };

            if(addTransaction ) {
                listTransactions.add(Transaction.builder()
                        .type(line.substring(3, 4))
                        .accountOrigin(line.substring(20, 36))
                        .accountDestination(line.substring(36, 52))
                        .value(ProcessorUtils.parseStringToValueDecinal(line.substring(4, 20)))
                        .build());
            }

        }
    }
}
