# pismo-challenge
Desafio pismo 
Tecnologias usadas: Java Spring Boot, DynamoDB, Docker, Swagger2. Foi utilizado o projeto base da Baeldung: https://www.baeldung.com/spring-data-dynamodb

DynamoDB rodando local com docker compose.
Docker da imagem java - incompeto.

Alteração da estrutura de banco para atender proposta single table do DynamoDB visando escalabilidade.

Para rodar, run na classe principal Application.java
Rodar imagem docker do Dynamo: docker-compose up

Verificar se existe a tabela:

aws --endpoint-url=http://localhost:4566 --region=us-east-1  dynamodb list-tables

Testar requisições do desafio. Um detalhe é que o ID é gerado automático com UUID, logo precisa de um comando manual para recuperar do banco as ID:

aws --endpoint-url=http://localhost:4566 --region=us-east-1 dynamodb scan --table-name pismo-account

Usar o id gerado para poder usar método POST de criar operação.

A tabela foi projetada para poder futuramente filtar por tipo de operacao e se precisar por data também. Dependendo da necessidade daria para implementar índices globais para facilitar e melhorar a performance das consultas. Imagem do projeto:
![alt text](https://github.com/boscocp/pismo-challenge/blob/main/tabela-pismo.png?raw=true)

Os testes de integração foram feitos usando JUnit e mocking do banco DynamoDB.
Pela simplicidade das chamadas não foram feitos testes unitários.

A documentação automatizada apenas incluindo a dependência springdoc-openapi-ui. Para acessar a documentação pela vizualização do swagger2 utilizar o link:

http://localhost:8080/swagger-ui.html
