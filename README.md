# Aluraflix
<p>
    <img alt="GitHub top language" src="https://img.shields.io/github/languages/top/adrianoavelino/aluraflix">
    <a href="https://github.com/my-study-area">
        <img alt="Made by" src="https://img.shields.io/badge/made%20by-adriano%20avelino-gree">
    </a>
    <img alt="Repository size" src="https://img.shields.io/github/repo-size/adrianoavelino/aluraflix">
    <a href="https://github.com/EliasGcf/readme-template/commits/master">
    <img alt="GitHub last commit" src="https://img.shields.io/github/last-commit/adrianoavelino/aluraflix">
    </a>
</p>

Aluraflix é uma implementação de uma `API REST` desenvolvida durante o **Alura Challenge Back-End** da Alura.

## Sobre
É uma API para uma Plataforma de compatilhamento de vídeos. A plataforma deve permitir ao usuário montar playlists com links para seus vídeos preferidos, separados por categorias.

### Tecnologias
- Java
- Spring Boot
- Spring Data
- Spring Boot Developer Tools
- H2 Database
- Mysql
- Docker e Docker Compose (Banco de dados Mysql)
- Junit Jupiter
- Hibernate/Jakarta Validator

## Começando
```bash
# clone o projeto
git clone https://github.com/adrianoavelino/aluraflix.git

# entre no diretório
cd aluraflix

# inicie o container mysql
docker-compose up -d

# inicie a aplicação
./mvnw spring-boot:run
```
Para inicialalizar a aplicação no container no ambiente de produção execute os seguintes passos, após clonar e entrar no diretório da aplicação:
```bash
# gera o jar da aplicação
./mvnw clean package -DskipTests

# gera a imagem docker da aplicação
docker build -t adrianoavelino/aluraflix:1 .

# inicia o container do banco de dados
docker-compose up -d

# inicia a aplicação no docker
docker run -it -p 8080:8080 \
-e SPRING_PROFILES_ACTIVE='prod' \
-e DATASOURCE_URL='jdbc:postgresql://db:5432/aluraflix' \
-e DATASOURCE_USERNAME='adriano' \
-e DATASOURCE_PASSWORD='adriano' \
-e JWT_SECRET='123456' \
-e JWT_EXPIRATION='8640000' \
-e SERVER_PORT='8080' \
--network=aluraflix_default adrianoavelino/aluraflix:1
```

### Outras informações
Para acessar o banco de dados via linha de comando no docker execute:
```bash
# acessa o banco de dados na linha de comando
docker-compose exec db mysql -u adriano -p
```
Usuário e senha do banco de dados:
|Usuário|Senha  |
--------|----   |
|adriano|adriano|

## Testes

### Testes automatizados
Comando para executar testes no terminal:
- Repository:
```bash
./mvnw test -Dtest=br.com.alura.aluraflix.repository.VideoRepositoryTest -e
```
- Controller:
```bash
./mvnw test -Dtest=br.com.alura.aluraflix.controller.VideoControllerTest -e
```

### Testes manuais
- GET `/v1/videos`:
```bash
curl --location --request GET 'http://localhost:8080/v1/videos'
```
Exemplo de reposta:
```json
{
    "content": [
        {
            "id": 2,
            "titulo": "Mysql",
            "descricao": "Do básico ao avançado com Mysql",
            "url": "https://www.meusite.com.br/mysql"
        },
        {
            "id": 3,
            "titulo": "Docker",
            "descricao": "Primeiros passos com docker",
            "url": "https://www.meusite.com.br/docker"
        }
    ],
    "pageable": {
        "sort": {
            "sorted": false,
            "unsorted": true,
            "empty": true
        },
        "pageNumber": 0,
        "pageSize": 20,
        "offset": 0,
        "paged": true,
        "unpaged": false
    },
    "totalPages": 1,
    "totalElements": 2,
    "last": true,
    "sort": {
        "sorted": false,
        "unsorted": true,
        "empty": true
    },
    "number": 0,
    "first": true,
    "numberOfElements": 2,
    "size": 20,
    "empty": false
}
```
- GET `/v1/videos/{id}`:
```bash
curl --location --request GET 'http://localhost:8080/v1/videos/1'
```

Exemplo de reposta:
```json
{
    "id": 1,
    "titulo": "Domain Driven Design com Alberto Sousa, o Dev Eficiente",
    "descricao": "Domain Driven Design, o que ? Por que foi criada e qual objetivo dessa linguagem dentro da programao?",
    "url": "https://www.youtube.com/watch?v=n40Z1c9Ryog"
}
```
- POST `/v1/videos`:
```bash
curl --location --request POST 'http://localhost:8080/v1/videos' \
--header 'Content-Type: application/json' \
--data-raw '{
    "titulo": "Algumas Dicas de CSS",
    "descricao": "Dicas de uso de SASS e especificadade CSS",
    "url": "https://youtu.be/bOdrGg5oc3E"
}'
```
Exemplo de reposta:
```json
{
    "id": 4,
    "titulo": "Algumas Dicas de CSS",
    "descricao": "Dicas de uso de SASS e especificadade CSS",
    "url": "https://youtu.be/bOdrGg5oc3E"
}
```
- PUT `/v1/videos/{id}`:
```bash
curl --location --request PUT 'http://localhost:8080/v1/videos' \
--header 'Content-Type: application/json' \
--data-raw '{
    "id": 22,
    "titulo": "Atualizado Domain Driven Design com Alberto Sousa, o Dev Eficiente",
    "descricao": "Atualizado Domain Driven Design, o que ? Por que foi criada e qual objetivo dessa linguagem dentro da programao?",
    "url": "https://www.youtube.com/watch?v=atualizado"
}'
```
- DELETE `/v1/videos/{id}`:
```bash
curl --location --request DELETE 'http://localhost:8080/v1/videos/1' \
--data-raw ''
```
