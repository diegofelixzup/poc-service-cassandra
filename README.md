# poc-service-cassandra

## Ferramentas Utilizadas:

- Java 8
- Lombok
- Spring
- Cassandra DB
- Docker

## Instalação

Realizar o clone do projeto e abrir na IDE de sua escolha.
Caso não tenha instalado Docker, baixar no link https://www.docker.com/products/docker-desktop a versão relacionada ao seu
sistema operacional e seguir as instruções de instalação.
Entrar na pasta Dockerfiles, e executar o comando $docker-compose up para criação do container do Cassandra.
Criar um keyspace com nome "cashmanagement".

## Descrição

Esta é uma poc demonstrativa de algumas funcionalidades que possivelmente estarão presentes no projeto de cash management. A princípio foram desenvolvidas APIs de CRUD em cima das estruturas de qrcode estático. Logo abaixo uma relação das APIs presentes até o momento:

- Salvar dados de um qrcode estático
- Listagem de todos os qrcodes estáticos salvos
- Listagem de qrcodes pertencentes a uma chave de autenticação
- Listagem de qrcodes pertencentes a uma chave de autenticação e que possuam um status (INATIVO ou ATIVO)
- Atualizar dados de um qrcode estático passando como parâmetro o id.
- Exclusão lógica de um qrcode estático, alterando seu status para CANCELADO

