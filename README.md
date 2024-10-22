# Currency Converter - Backend

Este repositório contém o código-fonte do back-end do projeto Currency Converter, responsável pelo gerenciamento de autenticação de usuários e serviços de conversão de moedas.

## Estrutura

O back-end foi desenvolvido utilizando **Spring Boot** com as seguintes funcionalidades principais:

- Registro de usuários
- Autenticação de usuários com JWT
- Integração com banco de dados MySQL
- Proteção de endpoints com autenticação via token JWT

## Principais Tecnologias Utilizadas

- **Java 17**
- **Spring Boot 3.3.4**
- **Spring Security 6.1** (para segurança e autenticação)
- **JWT** (para autenticação stateless)
- **MySQL** (para persistência de dados)
- **Maven** (para gerenciamento de dependências)

## Funcionamento

### Segurança

A segurança da aplicação é garantida através do **Spring Security** com autenticação baseada em **JWT**. Abaixo estão os principais componentes:

- **JwtAuthorizationFilter**: Filtro que intercepta as requisições, valida o token JWT e autentica o usuário.
- **SecurityConfig**: Classe de configuração de segurança que define as regras de autenticação e autorização dos endpoints.
- **UserService**: Serviço responsável pela criação e gerenciamento de usuários, incluindo a criptografia de senhas usando o **PasswordEncoder**.

---

Este back-end foi desenvolvido em **Java**, utilizando **Spring Boot** e **Spring Security** para demonstrar, no contexto acadêmico, como implementar autenticação com JWT em uma aplicação de conversão de moedas. Além de gerenciar o registro e login de usuários, ele protege as rotas da aplicação e integra com um banco de dados **MySQL** para persistir dados de forma segura, utilizando criptografia de senhas com o **PasswordEncoder**.
