# Micro Service - Processador de Arquivos Cnab  (Back-End)


### Sobre o Projeto
API Rest de exemplo para validação e persistencia de dados enviados através de um arquivo texto.<br>
Este aquivo contem informações sobre transações bancarias de crédito, débito e transferencias.



## Tecnologias

- Java JDK 17     -> https://www.oracle.com/java/technologies/javase/jdk17-archive-downloads.html
- Maven           -> https://maven.apache.org/
- Swagger         -> https://swagger.io/
- SpringBoot      -> https://spring.io/projects/spring-boot
- SpringData      -> https://spring.io/projects/spring-data
- Lombok          -> https://projectlombok.org/
- Hibernate       -> https://hibernate.org/
- H2 (Em Memoria)  -> https://www.h2database.com/html/main.html


## Premissas
- Windows

- Ter o Java JDK 11 ou superior instalado. Para verificar execute os seguintes passos: <br>

    - Abra o menu iniciar
        - Em "pesquise" digite: cmd (Prompt de Comando)
        - Clique em Abrir
        - Com o prompt aberto, digite na linha de comando: Java --Version, aperte enter. <br>
          Se o Java estiver instalado, algo similar a isto ira aparecer:<br>


       java 11.0.12 2021-07-20 LTS<br>
       Java(TM) SE Runtime Environment 18.9 (build 11.0.12+8-LTS-237)<br>
       Java HotSpot(TM) 64-Bit Server VM 18.9 (build 11.0.12+8-LTS-237, mixed mode)<br>

Caso contrario, instale o JDK. Basta clicar no link acima, achar a versão compativel e seguir as intruções da pagina.


- Ter o Maven instalado, para verificar basta seguir o mesmo procedimento acima para abrir o prompt.
    - Após aberto, digite:  mvn -v  e aperte "Enter"<br>
      Se o Maven, já estiver instalado, algo similar a isto ira aparecer:


    Apache Maven 3.8.5 (3599d3414f046de2324203b78ddcf9b5e4388aa0)<br>
    Maven home: C:\work\apache-maven-3.8.5<br>
    Java version: 11.0.12, vendor: Oracle Corporation, runtime: C:\Program Files\Java\jdk-11.0.12<br>
    Default locale: pt_BR, platform encoding: Cp1252<br>
    OS name: "windows 10", version: "10.0", arch: "amd64", family: "windows"

Caso contrario, instale o Maven. Basta clicar no link acima, e seguir as intruções da pagina.


# Executando o Projeto
### Atraves de uma  IDE (Intellij, Eclipse...)
```
- De um "Git Clone" no repositorio ou baixe e descompacte os arquivos do projeto em seu computador.
- Importe o projeto para sua IDE.
- A IDE ira carregar todas as dependcias sozinha.
- Apos as depencias serem carregas basta executar o projeto através da classe principal : CnabProcessorFileApplication
```

### Atraves do prompt via Maven
```
- Entrar na pasta raiz do projeto
- digitar "mvn clean package" e aperte o "Enter" e aguardar o processo build.
- Encerrado o processo anterior, agora digite: "java -jar target/cnab-processor-file-0.0.1-SNAPSHOT.jar" aperte o "Enter"
- Aguarde o processo terminar, em seguida teste o serviço.
```

### Testando o Serviço

Faça uma chamada direta de seu browser, utlizando esta URL:
http://localhost:9410/cnab/processor/v1/status <br>
Retorno: -- Versão 1.0 do serviço esta On Line --- 2024-03-06T20:05:58.362782700 <br>
O Serviço esta OK.

<hr>

# Utilizando o Projeto



## API 


```http
  POST /cnab/processor/v1/file/upload 
```

| Parametro | Tipo               | Descrição                |
|:----------|:-------------------| :------------------------- |
| `file`    | `string ($binary)` |  Realiza o Upload de um arquivo|

```http
  GET /cnab/processor/v1/transactions/find 
```

| Parametro            | Tipo      | Descrição                                                                                 |
|:---------------------|:----------|:------------------------------------------------------------------------------------------|
| `companyName`        | `string ` | Pesquisa pelo Nome da Empresa                                                             |
| `companyId`          | `string ` | Pesquisa pelo CNPJ da Empresa                                                             |
| `accountOrigin`      | `string ` | Pesquisa pela conta de origem                                                             |
| `accountDestination` | `string`  | Pesquisa pela conta de destino                                                            |
| `type`               | `string ` | Pesquisa pelo tipo de operação <br> (C para Crédito, D para Débito, T para Transferência) |
| `pageNumber`         | `int`     | Número da pagina para paginação                                                           |
| `pageSize`           | `int `    | Número de registros por pagina                                                            |


```http
  GET /cnab/processor/v1/status
```
| Parametro | Tipo               | Descrição                                    |
|:----------|:-------------------|:---------------------------------------------|
| ``    | `` | Verifica se a aplicação esta sendo executada |

## Base de Dados
<hr>

### / H2 Database (Em Memoria)
```
http://localhost:9410/cnab/processor/v1/h2-console

URL-JDBC : jdbc:h2:mem:registrocreditorio
User: super
Password: master
```


## Documentação 
<hr>

### / Swagger
- http://localhost:9410/cnab/processor/v1/swagger-ui/index.html#

### / Postman - Collection
- Pasta postman -> Processor-File-Cnab.postman_collection.json

### / Script BD
- File: SCRIPT-DB.sql

### / Layout Arquivo 
 - O arquivo para teste de ser 1 por empresa, do tipo "texto" e composto por:<br>
   * 1 Registro de Cabeçalho<br>
   * n Registros de Transações<br>
   * 1 Registro de Rodapé<br>
   * 80 colunas


 ##### Registro de Cabeçalho<br>

| Posições  | Tipo               | Descrição                | Regra                  |
|:----------|:-------------------| :----------------------- |------------------------|
|  1-3   | Númerico | Identificação do tipo de registro | Deve iniciar com `001` |
|  4-33   | Alfa-Numerico | Razão social da empresa  | Não pode estar vazio   |
|  34-47   | Númerico | Identificador da empresa(CNPJ) | Não pode estar vazio   |
|  48-80   | Alfa-Numerico |Espaço reservado para uso futuro |       Sem validação                 |
<br>

##### Registro de Transações<br>

| Posições | Tipo          | Descrição                                                          | Regra                                             |
|:---------|:--------------|:-------------------------------------------------------------------|---------------------------------------------------|
| 1-3      | Númerico      | Identificação do tipo de registro                                  | Deve iniciar com `002`                            |
| 4        | Caracter      | Tipo de operação <br> (C = Crédito, D = Débito, T = Transferência) | Não pode estar vazio. <br> Não poder Ser Númerico |
| 5 -20    | Númerico      | Valor da Transação em formato decimal, sem ponto decimal           | Não pode estar zerado ou vazio                    |
| 21-36    | Númerico | Conta origem                                   | Não pode estar zerado ou vazio                    |
| 37-52    | Númerico | Conta destino                                   | Não pode estar zerado ou vazio                    |
| 53-80    | Alfa-Numerico | Espaço reservado para uso futuro                                  | Sem validação                                     |
<br>

##### Registro de Rodapéo<br>

| Posições  | Tipo               | Descrição                | Regra                  |
|:----------|:-------------------| :----------------------- |------------------------|
|  1-3   | Númerico | Identificação do tipo de registro | Deve iniciar com `003` |
|  4-80   | Alfa-Numerico |Espaço reservado para uso futuro |      Sem validação                  |


### Arquivos de exemplo
- Pasta ArquivosTestes
  - cnab-valido.txt
  - cnab-invalido.txt
  - cnab-valores-nulos.txt




## Author


[@leokashmir](https://www.github.com/leokashmir) 

[![linkedin](https://img.shields.io/badge/linkedin-0A66C2?style=for-the-badge&logo=linkedin&logoColor=white)](https://www.linkedin.com/in/leokashmir/)



