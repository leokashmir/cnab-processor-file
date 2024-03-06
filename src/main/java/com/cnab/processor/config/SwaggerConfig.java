package com.cnab.processor.config;

import com.cnab.processor.exceptions.response.ErroFile;
import com.cnab.processor.exceptions.response.ExceptionHandleResponse;
import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.examples.Example;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.media.Content;
import io.swagger.v3.oas.models.responses.ApiResponse;
import org.springdoc.core.models.GroupedOpenApi;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.ArrayList;
import java.util.List;

@Configuration
public class SwaggerConfig {
    @Bean
    public GroupedOpenApi controllerApi() {
        return GroupedOpenApi.builder()
                .group("Json")
                .packagesToScan("com.cnab.processor")
                .build();
    }

    @Bean
    public OpenAPI baseOpenAPI()  {
        ApiResponse uploadSuccess = new ApiResponse().content(
                new Content().addMediaType("application/json",
                        new io.swagger.v3.oas.models.media.MediaType().addExamples("default",
                                new Example().value(
                                        getUploadSuccess()
                                ))));
        ApiResponse findSuccess = new ApiResponse().content(
                new Content().addMediaType("application/json",
                        new io.swagger.v3.oas.models.media.MediaType().addExamples("default",
                                new Example().value(
                                        getFindTransactions()
                                ))));

        ApiResponse badRequestUpload = new ApiResponse().content(
                new Content().addMediaType("application/json",
                        new io.swagger.v3.oas.models.media.MediaType().addExamples("default",
                                new Example().value(
                                        ExceptionHandleResponse.builder()
                                                .status("Error")
                                                .message("Mensage de Erro")
                                                .erros( getErroExemple())
                                                .build()

                                ))));
        ApiResponse badRequestFind = new ApiResponse().content(
                new Content().addMediaType("application/json",
                        new io.swagger.v3.oas.models.media.MediaType().addExamples("default",
                                new Example().value(
                                        ExceptionHandleResponse.builder()
                                                .status("Error")
                                                .message("Mensage de Erro")
                                                .build()

                                ))));
        ApiResponse internalServerError = new ApiResponse().content(
                new Content().addMediaType("application/json",
                        new io.swagger.v3.oas.models.media.MediaType().addExamples("default",
                                new Example().value("{\"status\" : \"Error\", \"message\" : \"internalServerError\"}"))));


        Components components = new Components();
        components.addResponses("uploadSuccess", uploadSuccess);
        components.addResponses("findSuccess", findSuccess);
        components.addResponses("badRequestUpload", badRequestUpload);
        components.addResponses("badRequestFind", badRequestFind);
        components.addResponses("internalServerError", internalServerError);

        return new OpenAPI().components(components).info(new Info().title("Project Cnab - Tratamento de Arquivos").version("1.0.0").description("Micro Serviço que simula o processamento de um arquivo padrão Cnab"));
    }

    private List<ErroFile> getErroExemple() {

        List<ErroFile> erros = new ArrayList<>();
        erros.add(ErroFile.builder()
                .line("10")
                .error("Erro na linha 10")
                .build());
        erros.add(ErroFile.builder()
                .line("8")
                .error("Erro na linha 8")
                .build());

        return erros;
    }

    private String getFindTransactions()  {
            String json = """
                    {
                        "content": [
                            {
                                "id": 1,
                                "company": {
                                    "id": 1,
                                    "companyName": "Empresa A",
                                    "companyId": " 19000112000100"
                                },
                                "type": "C",
                                "value": 14548.00,
                                "accountOrigin": "0001234560000012",
                                "accountDestination": "3450075385612347"
                            },
                            {
                                "id": 2,
                                "company": {
                                    "id": 1,
                                    "companyName": "Empresa A",
                                    "companyId": " 19000112000100"
                                },
                                "type": "D",
                                "value": 22151.30,
                                "accountOrigin": "0001234561234560",
                                "accountDestination": "0000045685612348"
                            }
                        ],
                        "pageable": {
                            "pageNumber": 0,
                            "pageSize": 5,
                            "sort": {
                                "empty": true,
                                "sorted": false,
                                "unsorted": true
                            },
                            "offset": 0,
                            "unpaged": false,
                            "paged": true
                        },
                        "last": true,
                        "totalPages": 0,
                        "totalElements": 0,
                        "size": 5,
                        "number": 0,
                        "sort": {
                            "empty": true,
                            "sorted": false,
                            "unsorted": true
                        },
                        "first": true,
                        "numberOfElements": 0,
                        "empty": true
                    }""";


        return json;
    }

    private String getUploadSuccess()  {


        var json = """
                {
                    "status": "success",
                    "message": "Arquivo CNAB enviado e processado com sucesso.",
                    "data": {
                        "transactionDtos": [
                            {
                                "type": "C",
                                "value": 14548.00,
                                "accountOrigin": "0001234560000012",
                                "accountDestination": "3450075385612347"
                            },
                            {
                                "type": "D",
                                "value": 22151.30,
                                "accountOrigin": "0001234561234560",
                                "accountDestination": "0000045685612348"
                            },
                            {
                                "type": "T",
                                "value": 521.00,
                                "accountOrigin": "0001234562345660",
                                "accountDestination": "0000098765612349"
                            }
                         
                        ]
                    }
                }""";

        return json;
    }
}