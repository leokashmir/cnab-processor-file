package com.cnab.processor.validators;

import com.cnab.processor.enumerator.TransactionErroEnum;
import com.cnab.processor.exceptions.InvalidFileException;
import com.cnab.processor.exceptions.response.ErroFile;
import com.cnab.processor.dto.TransactionDto;
import com.cnab.processor.model.Company;
import com.cnab.processor.util.ProcessorUtils;
import lombok.SneakyThrows;
import lombok.extern.log4j.Log4j2;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Stream;

@Log4j2
public class TransactionValidator implements Validator {

    private List<Object> listTransactions;
    private static int numberLine;

    /**
     * Valida o arquivo e retorna uma lista de objetos representando as transações.
     *
     * @param file O arquivo a ser validado.
     * @return Um Optional contendo uma lista de objetos representando as transações se a validação for bem-sucedida;
     *         ou um Optional vazio se não houver transações no arquivo.
     * @throws InvalidFileException Se ocorrer um erro durante a validação do arquivo.
     */
    @Override
    public Optional<List<Object>> validate(File file) {
        log.info("===== >Iniciando a validação das Transações");
        listTransactions = new ArrayList<>();
        numberLine = 1;

        try (Stream<String> lines = Files.lines(Path.of(file.getAbsolutePath()))) {
            lines.forEach(this::transactionLine);
            log.info("===== >Fim ds validação das Transações");

        } catch (Exception e) {
            log.error("===== >Erro na validação das Transações");
            throw new InvalidFileException();
        }

        return Optional.of(listTransactions);


    }

    @SneakyThrows
    private void transactionLine(String line) {
        boolean addTransaction = true;
        numberLine++;

        if (!ProcessorUtils.isCorrectlength(line)) {
            throw new InvalidFileException();
        }

        if ("001".equals(line.substring(0, 3))) {
            var companyName = ProcessorUtils.removeTrailingSpaces(line.substring(3, 32));
            listTransactions.add(Company.of(
                    null, companyName, line.substring(32, 47)));
        }

        if ("002".equals(line.substring(0, 3))) {
            if (!ProcessorUtils.isNumeric(line.substring(0, 3))) {
                listTransactions.add(ErroFile.builder()
                        .error(TransactionErroEnum.IDENTIFICACAO_TIPO_REGISTRO.getValue())
                        .line(String.valueOf(numberLine))
                        .build());
                addTransaction = false;

            }
            if (!line.substring(3, 4).matches("[CDT]")) {
                listTransactions.add(ErroFile.builder()
                        .error(TransactionErroEnum.TIPO_TRANSACAO.getValue())
                        .line(String.valueOf(numberLine))
                        .build());
                addTransaction = false;


            }
            if (!ProcessorUtils.isNumeric(line.substring(4, 20))) {
                listTransactions.add(ErroFile.builder()
                        .error(TransactionErroEnum.FORMATO_TRANSACAO.getValue())
                        .line(String.valueOf(numberLine))
                        .build());
                addTransaction = false;


            }
            if (ProcessorUtils.containsOnlyZero(line.substring(4, 20))) {
                listTransactions.add(ErroFile.builder()
                        .error(TransactionErroEnum.VALOR_TRANSACAO.getValue())
                        .line(String.valueOf(numberLine))
                        .build());
                addTransaction = false;


            }
            if (!ProcessorUtils.isNumeric(line.substring(20, 36))) {
                listTransactions.add(ErroFile.builder()
                        .error(TransactionErroEnum.CONTA_ORIGEM.getValue())
                        .line(String.valueOf(numberLine))
                        .build());
                addTransaction = false;

            }
            if (ProcessorUtils.containsOnlyZero(line.substring(20, 36))) {
                listTransactions.add(ErroFile.builder()
                        .error(TransactionErroEnum.CONTA_ORIGEM.getValue())
                        .line(String.valueOf(numberLine))
                        .build());
                addTransaction = false;

            }
            if (!ProcessorUtils.isNumeric(line.substring(36, 52))) {
                listTransactions.add(ErroFile.builder()
                        .error(TransactionErroEnum.CONTA_DESTINO.getValue())
                        .line(String.valueOf(numberLine))
                        .build());
                addTransaction = false;

            }
            if (ProcessorUtils.containsOnlyZero(line.substring(36, 52))) {
                listTransactions.add(ErroFile.builder()
                        .error(TransactionErroEnum.CONTA_DESTINO.getValue())
                        .line(String.valueOf(numberLine))
                        .build());
                addTransaction = false;

            }
            if (addTransaction) {
                listTransactions.add(TransactionDto.builder()
                        .type(line.substring(3, 4))
                        .accountOrigin(line.substring(20, 36))
                        .accountDestination(line.substring(36, 52))
                        .value(ProcessorUtils.parseStringToValueDecinal(line.substring(4, 20)))
                        .build());
            }

        }
    }
}
