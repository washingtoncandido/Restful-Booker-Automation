# Copilot Instructions — API Automation

## 1. Projeto

Este projeto é uma automação de testes de API utilizando:

* Java 21
* Maven
* JUnit 5
* Cucumber / Gherkin
* REST Assured
* Allure

API principal:

`https://restful-booker.herokuapp.com`

---

## 2. Regra principal

Sempre analisar a estrutura existente antes de criar ou alterar código.

A implementação existente de `auth` é a referência arquitetural do projeto.

Não criar uma arquitetura diferente sem necessidade.

Prioridade:

1. Reutilizar código existente
2. Adaptar código existente
3. Criar novo código somente quando necessário

---

## 3. Arquitetura

Manter o padrão:

```text
src/main/java/org/example
├── core
│   ├── config
│   └── requests
│
├── auth
│   ├── client
│   ├── dto
│   └── factory
│
├── <feature>
│   ├── client
│   ├── dto
│   └── factory
```

Testes:

```text
src/test/java/org/example
├── runner
└── steps
```

Features:

```text
src/test/resources/features
├── auth
└── <feature>
```

Não criar camadas desnecessárias como:

* Service
* Repository
* Controller
* Manager
* Helper
* Utils

a menos que exista uma necessidade real.

---

## 4. Responsabilidade das camadas

### Client

Responsável pelas chamadas HTTP utilizando REST Assured.

Os Steps não devem realizar chamadas HTTP diretamente.

### DTO

Representar request e response da API.

Utilizar Java `record` sempre que possível.

Não utilizar Lombok.

### Factory

Responsável pela criação dos dados de teste.

Não realizar chamadas HTTP dentro das Factories.

### Steps

Responsáveis apenas pela orquestração:

1. preparar dados
2. chamar Client
3. armazenar resposta
4. realizar assertions

Não colocar lógica HTTP nos Steps.

### Feature

Representar o comportamento da API utilizando Gherkin.

Cada Step do Gherkin deve possuir uma implementação correspondente.

---

## 5. Regras de implementação

Sempre:

* utilizar Java 21
* utilizar REST Assured
* utilizar JUnit 5
* utilizar Cucumber
* utilizar Allure conforme padrão existente
* reutilizar `RequestSpecs.defaultSpec()`
* reutilizar configurações existentes
* reutilizar autenticação existente
* evitar duplicação
* utilizar Java `record` para DTOs quando aplicável
* centralizar endpoints quando isso seguir o padrão existente

Nunca:

* utilizar Lombok
* utilizar `Thread.sleep()`
* desabilitar SSL
* duplicar lógica de autenticação
* colocar credenciais diretamente nos Steps
* duplicar RequestSpecifications
* criar classes desnecessárias
* inventar endpoints ou comportamentos da API

---

## 6. Autenticação

O módulo `auth` já existe.

Não recriar a lógica de autenticação.

Operações que exigem autenticação devem reutilizar a implementação existente.

Não duplicar chamadas para:

`POST /auth`

---

## 7. Gherkin

Utilizar português seguindo o padrão existente.

Cenários principais:

`@smoke`

Cenários negativos e complementares:

`@regression`

Os Steps devem possuir correspondência exata com o texto utilizado nas Features.

---

## 8. API

Antes de implementar qualquer endpoint:

1. confirmar endpoint
2. confirmar método HTTP
3. confirmar autenticação
4. confirmar request
5. confirmar response
6. confirmar status code

Utilizar somente comportamentos existentes na API.

Nunca inventar:

* endpoints
* parâmetros
* status codes
* campos
* regras de negócio

---

## 9. Alterações

Antes de alterar um arquivo existente:

* verificar se a alteração é realmente necessária
* preservar o comportamento existente
* não quebrar funcionalidades já implementadas

Não recriar arquivos existentes.

---

## 10. Validação

Após implementar uma funcionalidade:

1. verificar compilação
2. verificar correspondência Feature × Steps
3. executar os testes relacionados
4. corrigir erros encontrados
5. verificar se os testes existentes continuam funcionando

Comando principal:

```bash
mvn test
```

Smoke:

```bash
mvn test -Dcucumber.filter.tags="@smoke"
```

Regression:

```bash
mvn test -Dcucumber.filter.tags="@regression"
```

---

## 11. Comportamento esperado do Copilot

Antes de implementar:

* analisar os arquivos existentes
* identificar padrões
* identificar código reutilizável
* informar quais arquivos serão criados
* informar quais arquivos serão alterados

Depois:

* implementar seguindo o padrão existente
* validar compilação
* validar testes
* apresentar resumo das alterações

Nunca modificar a arquitetura do projeto sem justificar a necessidade.
