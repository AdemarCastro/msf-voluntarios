# Sistema de Gerenciamento de Voluntários da MSF

## Descrição
O **Sistema de Gerenciamento de Voluntários da MSF (SGV-MSF)** é uma aplicação desenvolvida para atender às necessidades de gerenciamento de voluntários da organização **Médicos Sem Fronteiras (MSF)**. A solução permite o cadastro, listagem, consulta e exclusão de voluntários interessados em participar de missões médicas internacionais.

## Regras de Negócio
- **RN-01**: O voluntário deve possuir os seguintes dados cadastrados (todos obrigatórios):
    - Passaporte
    - Nome Completo
    - Idade
    - Telefone
    - E-mail
    - Tipo Sanguíneo
    - Cidade de Interesse
    - Situação de Saúde
- **RN-02**: Não é permitido cadastrar voluntários com passaportes duplicados no sistema.
- **RN-03**: Um voluntário deve estar associado a uma cidade de um determinado país.
- **RN-04**: O voluntário deve declarar sua situação de saúde entre as opções:
    - Ruim
    - Bom
    - Ótimo

## Tecnologias Utilizadas
- **Java 21**
- **Spring Boot**
- **Spring MVC**
- **Spring Data JPA**
- **MySQL** como banco de dados relacional
- **Jakarta EE** com `jakarta.` imports
- **Swagger/OpenAPI** para documentação e testes interativos da API

## Funcionalidades
- **Cadastro de Voluntários**: Permite adicionar novos voluntários ao sistema, verificando os critérios obrigatórios.
- **Listagem de Voluntários**: Exibe todos os voluntários cadastrados no sistema.
- **Consulta de Voluntários**: Recupera detalhes sobre um voluntário específico pelo número do passaporte.
- **Exclusão de Voluntários**: Remove voluntários do sistema.

## Estrutura do Projeto
O projeto segue uma arquitetura baseada em camadas:
- **Model**: Contém as entidades de domínio, como `Voluntário`, `Cidade`, `País`, e `SituaçãoSaude`.
- **Repository**: Define os repositórios utilizando Spring Data JPA para interação com o banco de dados.
- **Service**: Implementa as regras de negócio para validação e manipulação dos dados.
- **Swagger**: 
- **Controller**: Exposta como API REST para manipulação de voluntários.
- **DTO**: Utilizado para transferência de dados entre a camada de apresentação e a lógica de negócio.

## Estrutura de Diretórios
A estrutura do projeto segue o padrão do Spring Boot:

```
src/main/java
└── com.msf.voluntarios
    ├── Voluntario
    │   ├── Voluntario.java                  # Classe model do voluntário
    │   ├── VoluntarioRepository.java        # Repositório do voluntário
    │   ├── VoluntarioService.java           # Lógica de negócios do voluntário
    │   ├── VoluntarioController.java        # API REST controller
    │   └── dto
    │       ├── VoluntarioInputDTO.java      # Transferência de dados (entrada)
    │       └── VoluntarioOutputDTO.java     # Transferência de dados (saída)
    │
    ├── SituacaoVoluntario
    │   ├── SituacaoVoluntario.java             # Enum ou classe para situação de saúde
    │   ├── SituacaoVoluntarioRepository.java   # Repositório associado, caso necessário
    │   └── enums
    │       └── SituacaoVoluntarioEnum.java     # Enum para situação de saúde
    │
    ├── Cidade
    │   ├── Cidade.java                      # Classe model de cidade
    │   ├── CidadeRepository.java            # Repositório associado
    │
    ├── Pais
    │   ├── Pais.java                        # Classe model de país
    │
    ├── swagger
    │   └── SwaggerConfig.java               # Configuração do Swagger para a API
```

## Instalação e Configuração
Siga os passos abaixo para instalar e configurar o projeto:

1. Clone o repositório do projeto:
   ```bash
   git clone <URL_DO_REPOSITORIO>
   ```

2. Abra o projeto em uma IDE compatível com Java, como IntelliJ IDEA.

3. Configure as propriedades do banco de dados no arquivo `application.properties`:
   ```properties
   spring.datasource.url=jdbc:mysql://localhost:3306/msf
   spring.datasource.username=<SEU_USUARIO>
   spring.datasource.password=<SUA_SENHA>
   spring.jpa.hibernate.ddl-auto=update
   ```

4. Certifique-se de que o MySQL esteja em execução e crie o banco de dados:
   ```sql
   CREATE DATABASE msf;
   ```

5. Execute o projeto usando sua IDE ou pelo terminal:
   ```bash
   ./mvnw spring-boot:run
   ```

6. Acesse as APIs REST através de:
   ```
   http://localhost:8080/api/voluntario
   ```

## **Swagger UI**

Para facilitar a visualização e testes das APIs, o Swagger UI está configurado no projeto.
Acesse a interface gráfica do Swagger no seguinte endereço após rodar o projeto:

---

```
http://localhost:8080/swagger-ui.html
```

*A seguir estão imagens ilustrativas da interface do Swagger em execução:*

## **1. Criar um Novo Voluntário**

## **2. Atualizar um Voluntário**

## **3. Listar Voluntários**

## **4. Buscar Voluntário por ID**

## **5. Deletar um Voluntário**

## **Endpoints da API**

Abaixo estão listados os principais endpoints para operações de voluntários:

---

## **1. Criar um Novo Voluntário**

### **POST** `/api/voluntario`

Cadastra um novo voluntário no sistema.

#### **Corpo da Requisição (JSON)**

```json
{
  "passaporte": "12345678",
  "nome": "João Silva",
  "idade": "35",
  "telefone": "(92) 99999-9999",
  "email": "joao.silva@email.com",
  "tipoSanguineo": "O+",
  "cidade": "3",
  "situacaoVoluntario": "3"
}
```

#### **Resposta (201 - Created)**

```json
{
  "id": 1,
  "passaporte": "12345678",
  "nome": "João Silva",
  "idade": "35",
  "telefone": "(92) 99999-9999",
  "email": "joao.silva@email.com",
  "tipoSanguineo": "O+",
  "cidade": "Manaus",
  "situacaoVoluntario": "ÓTIMO"
}
```

#### **Resposta (400 - Bad Request)**

```json
{
  "error": "Erro ao cadastrar o voluntário. Campo 'nome' é obrigatório."
}
```

---

## **2. Atualizar um Voluntário**

### **PUT** `/api/voluntario/{id}`

Atualiza um voluntário existente.

#### **Parâmetros**

- `id` (Path Variable): ID do voluntário que será atualizado.

#### **Corpo da Requisição (JSON)**

```json
{
  "passaporte": "12345678",
  "nome": "João Silva Atualizado",
  "idade": "36",
  "telefone": "(92) 99999-9999",
  "email": "joao.silva@email.com",
  "tipoSanguineo": "B+",
  "cidade": "3",
  "situacaoVoluntario": "1"
}
```

#### **Resposta (200 - OK)**

```json
{
  "id": 1,
  "passaporte": "12345678",
  "nome": "João Silva Atualizado",
  "idade": "36",
  "telefone": "(92) 99999-9999",
  "email": "joao.silva@email.com",
  "tipoSanguineo": "B+",
  "cidade": "Manaus",
  "situacaoVoluntario": "RUIM"
}
```

#### **Resposta (404 - Not Found)**

```json
{
  "error": "Voluntário com ID 100 não encontrado."
}
```

---

## **3. Listar Voluntários**

### **GET** `/api/voluntario`

Retorna a lista de todos os voluntários cadastrados.

#### **Resposta (200 - OK)**

```json
[
  {
    "id": 1,
    "passaporte": "12345678",
    "nome": "João Silva Atualizado",
    "idade": "36",
    "telefone": "(92) 99999-9999",
    "email": "joao.silva@email.com",
    "tipoSanguineo": "B+",
    "cidade": "Manaus",
    "situacaoVoluntario": "RUIM"
  },
  {
    "id": 2,
    "passaporte": "98765432",
    "nome": "Maria Souza",
    "idade": "28",
    "telefone": "(92) 98888-8888",
    "email": "maria.souza@email.com",
    "tipoSanguineo": "A-",
    "cidade": "Boa Vista",
    "situacaoVoluntario": "ÓTIMO"
  }
]
```

---

## **4. Buscar Voluntário por ID**

### **GET** `/api/voluntario/{id}`

Busca um voluntário com o `id` especificado.

#### **Parâmetros**

- `id` (Path Variable): ID do voluntário que será buscado.

#### **Resposta (200 - OK)**

```json
{
  "id": 1,
  "passaporte": "12345678",
  "nome": "João Silva Atualizado",
  "idade": "36",
  "telefone": "(92) 99999-9999",
  "email": "joao.silva@email.com",
  "tipoSanguineo": "B+",
  "cidade": "Manaus",
  "situacaoVoluntario": "RUIM"
}
```

#### **Resposta (404 - Not Found)**

```json
{
  "error": "Voluntário com ID 100 não encontrado."
}
```

---

## **5. Deletar um Voluntário**

### **DELETE** `/api/voluntario/{id}`

Remove um voluntário com o `id` especificado.

#### **Parâmetros**

- `id` (Path Variable): ID do voluntário que será removido.

#### **Resposta (204 - No Content)**

*(Sem corpo de resposta)*

#### **Resposta (404 - Not Found)**

```json
{
  "error": "Voluntário com ID 100 não encontrado."
}
```

---

## **6. Estrutura Completa de Resposta do Voluntário**

Aqui está o formato completo esperado na resposta de endpoints que retornam os dados do voluntário.

### **Formato JSON**

```json
{
  "id": 1,
  "passaporte": "12345678",
  "nome": "João Silva",
  "idade": "35",
  "telefone": "(92) 99999-9999",
  "email": "joao.silva@email.com",
  "tipoSanguineo": "O+",
  "cidade": "3",
  "situacaoVoluntario": "3"
}
```

---

## **7. Possíveis Valores para `cidade` e `situacaoVoluntario`**

### **Cidades**

| Valor (Enum) | Descrição         |
|--------------|-------------------|
| `1`          | São Paulo         |
| `2`          | Rio de Janeiro    |
| `3`          | Manaus            |
| `4`          | Nova York         |
| `5`          | Toronto           |
| `6`          | Berlim            |
| `7`          | Tóquio            |

### **Situação do Voluntário**

| Valor (Enum) | Descrição                         |
|--------------|-----------------------------------|
| `1`          | RUIM - Voluntário com saúde péssima |
| `2`          | BOM - Voluntário com saúde satisfatória |
| `3`          | ÓTIMO - Voluntário com saúde excelente |

---

## **Resumo dos Endpoints**

| Método HTTP | Endpoint               | Descrição                        |
|-------------|------------------------|----------------------------------|
| POST        | `/api/voluntario`      | Criar um novo voluntário         |
| PUT         | `/api/voluntario/{id}` | Atualizar um voluntário existente|
| GET         | `/api/voluntario`      | Retornar todos os voluntários    |
| GET         | `/api/voluntario/{id}` | Buscar voluntário por ID         |
| DELETE      | `/api/voluntario/{id}` | Deletar um voluntário            |
---

## Testes
Certifique-se de preencher o banco de dados com informações relevantes antes de testar. Utilize ferramentas como o **Postman** ou **cURL** para testar os endpoints.
