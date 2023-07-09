# Desenvolvimento Backend

## O que foi feito na aula passada

Nesta aula, entendemos o funcionamento básico do Spring Boot, entendemos as camadas do MVC, e criamos as primeiras rotas
para cadastrar e listar um usuário.

Classes criadas:

- `User`: Representa a nossa entidade de modelo
- `UserRepository`: Armazena os usuários. Criamos um repositório apenas na memória, usando uma lista
- `UserService`: Faz o acesso ao repositório
- `UserController`: Permite a configuração das rotas.

E a view? Como estamos implementando um servidor baseado em Rest, não possuímos uma view (ou podemos considerar que
os JSONs de entrada/saída são essa view).

Finalmente, adicionamos ao arquivo `application.properties` a propriedade `server.servlet.context-path` com o
valor `/api`. Assim, todas as nossas rotas passaram a exigir esse valor na frente.
Exemplo: http://localhost:8080/api/users