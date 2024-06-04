# market-solution

Aplicação de exemplo da UC de Gestão e Qualidade de Software

**Tecnologias utilizadas**

- Maven para gestão de dependências e build
- SpringBoot + Spring Data JPA + Spring Rest + Spring Test
- Swagger para a documentação da API REST
- Junit para os testes

**Ambiente de Desenvolvimento**

- Java OpenJDK 19
- VSCode com plugin Java Extension Pack
- Versionamento com GitHub

**Configuração da aplicação**

application.properties

```
spring.datasource.url=jdbc:h2:file:./db
spring.datasource.username=sa
spring.datasource.password=
spring.datasource.driver-class-name=org.h2.Driver
spring.datasource.platform=h2
spring.jpa.show-sql=true
spring.jpa.defer-datasource-initialization=true
spring.jpa.hibernate.ddl-auto=create-drop
spring.jpa.properties.hibernate.dialect=org.hibernate.dialect.H2Dialect
spring.h2.console.enabled=true
spring.sql.init.mode=always
```

O arquivo import.sql pode ser utilizado para incluir registros de exemplo ao inicializar a aplicação.

**Configuração para testes**

application.properties

```
spring.datasource.url=jdbc:h2:mem:testdb;DB_CLOSE_DELAY=-1;
spring.datasource.username=sa
spring.datasource.password=
spring.datasource.driver-class-name=org.h2.Driver
spring.datasource.platform=h2
spring.jpa.show-sql=true
spring.jpa.defer-datasource-initialization=true
spring.jpa.generate-ddl=true
spring.jpa.hibernate.ddl-auto=create-drop
spring.jpa.properties.hibernate.dialect=org.hibernate.dialect.H2Dialect
spring.sql.init.mode=always
spring.h2.console.enabled=true
```

