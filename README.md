# Exercício Stack Overflow

Exercício de criação de uma API de perguntas e respostas simples, utilizando o princípio do [Stack Overflow](https://stackoverflow.com/).
<br>
<br>

---
### Documentação

Os endpoints foram documentados através do Postman e podem ser acessados [aqui](https://documenter.getpostman.com/view/20205755/UzdwWnHM).
<br>
<br>

---
### Regras

01. O sistema deve ser capaz de receber a criação de usuário e senha (não é necessário implementar alteração de senha);
00. O sistema deve ser capaz de autenticar usuário e senha;
00. Usuários autenticados podem enviar perguntas e responder perguntas de qualquer outro usuário;
00. Usuários autenticados podem editar somente as próprias perguntas e respostas (nunca de outros usuários);
00. Todos os usuários, autenticados ou não, podem visualizar perguntas e respostas.
<br>
<br>

---
### Ferramentas utilizadas

- Java 17;
- Spring Boot;
- Spring Security;
- Spring Data JPA;
- JWT;
- H2 Database.