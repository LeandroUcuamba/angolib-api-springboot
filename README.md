
# Livraria API - Autenticação e Autorização.

* Continuação do projeto: https://github.com/LeandroUcuamba/livraria-api-springboot

O foco foi aplicar autenticação na API.


## Instalação

Certifique-se que você possui instalado o:
```
- Java e JDK
- Intellij, Eclipse ou VScode
- Insomnia
```

## Algumas Funcionalidades da API

#### Para se registar

```http
  GET /v1/api/registro
```

enviar:

```json
{
	"nome":"leandro",
	"email":"leandro@gmail.com",
	"senha":"senha123"
}
```
retorno:

```json
{
	"id": 1,
	"nome": "leandro",
	"email": "leandro@gmail.com",
	"senha": "senha123"
}
```

#### Para se registar

```http
  GET /auth/usuarios/{email}
```
Exemplo:

```http
  GET /auth/usuarios/leandro@gmail.com
```

```json
{
	"id": 1,
	"nome": "leandro",
	"email": "leandro@gmail.com",
	"senha": "senha123"
}
```

#### Para fazer login

```http
  GET /v1/api/login
```

enviar:

```json
{
	"email": "leandro@gmail.com",
	"senha": "senha123"
}
```
retorno:

```json
{
	"token": "eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJKb2FvQGdtYWlsLmNvbSIsImV4cCI6MTc1NDk0OTMzNH0.eWgQwv-vpqcWJW48hz9ugtr-ue2shLkNY1UhUhS3kqXIR1c5oG7R4ppCGIz1owC5xEEkrHPtFy9_ENbtzPHunA"
}
```


## Tecnologias

Esse projeto utiliza as seguintes tecnologias:

- Java
- Spring Web
- JWT
- Spring Data JPA
- Lombok
- Banco de Dados H2


## Run

Para rodar o projeto utilize o comando:

```bash
  mvn spring-boot:run
```

