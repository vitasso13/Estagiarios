

## Aplicativo Spring Boot+Vue.js para controle de estagiários
**Está aplicação implementa e consome uma API REST utilizando Spring Boot com Maven e JAVA + VueJS + PostgreSQL.**
![Adição de estagiários com método http POST ](https://ibb.co/F7RHbL9)

![Listagem, remoção e edição com os métodos http GET, PUT e DELETE](https://ibb.co/2NkdBBb)

Seguem as dependências utilizadas no desenvolvimento e suas versões:

## Spring Boot 2.3.5.RELEASE
https://docs.spring.io/spring-boot/docs/2.3.5.RELEASE/reference/html/
a API Spring Boot está configurada para a porta 9000 para evitar conflitos com o VueJS(8080).
O servidor está **configurado para criar automaticamente a tabela de estagiarios** dentro do banco de dados.
O banco de dados é servido em **localhost:5432/estagiariosdb**.
Para modificar o banco de dados que servira a API, modificar a url do [jdbc](https://jdbc.postgresql.org/) no arquivo  application.yml
![/src/main/resources/application.yml](https://ibb.co/5rSy15w) 
**Trocar estagiariosdb para o nome do DB de preferencia juntamente com a porta e as credenciais. As configurações estão de acordo com a configuração padrão do PostgreSQL**![Trocar estagiariosdb para o nome do DB de preferencia juntamente com a porta e as credenciais.](https://ibb.co/qNDXxJQ)
 - Flywaydb para controle de versão e facil implementação do banco de dados. https://flywaydb.org/

## JAVA  utilizando JDK 11.0.9
Configurações do [IntelliJ IDEA](https://www.jetbrains.com/idea/resources/) inclusas no repositório. 
 `

 - `cd ./.idea`
 
   A pasta não é necessária, apenas facilita o trabalho. Em caso de uso de outra IDE(NetBeans, Eclipse, NodeJS, etc), desconsiderar.

https://www.oracle.com/java/technologies/javase-jdk11-downloads.html

## PostgreSQL 13 e pgAdmin4
A tabela é composta por VARCHAR para os dados do usuário e TEXT para receber os arquivos convertidos para o formato Base64. o ID é gerado automaticamente pela API e serve de índice para as operações CRUD.
https://www.postgresql.org/docs/13/release-13.html


## VueJS
[@vue/cli](https://cli.vuejs.org/) 4.5.0 + [ESlint](https://eslint.org/)
https://www.npmjs.com/package/@vue/cli/v/4.5.0
Os arquivos do front estão localizados na pasta `./public`.
Para instalar as dependências :
 

       npm install

Para rodar o programa do front:

    npm run serve

