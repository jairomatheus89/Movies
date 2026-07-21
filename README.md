# Projeto de galeria de Filmes

## Initial config:

In this project i dont push my ``application-local.properties`` because its my local configurations, obviously...

In ``src/main/resources/application.properties`` you just have:

``` 
# Project  
spring.application.name=backend  
spring.profiles.active=local  
  
# Banco de dados  
spring.datasource.driver-class-name=org.postgresql.Driver  
  
#PAGINAÇÃO  
spring.data.web.pageable.default-page-size=12  
spring.data.web.pageable.max-page-size=100  
  
# JPA / Hibernate  
spring.jpa.properties.hibernate.format_sql=true  
  
# Dialeto (opcional nas versões mais recentes)  
spring.jpa.properties.hibernate.dialect=org.hibernate.dialect.PostgreSQLDialect 
```
And in your ``src/main/resources/application.properties`` you must have:
``` 
spring.datasource.url=jdbc:postgresql://localhost:5432/your_db  
spring.datasource.username=you_user  
spring.datasource.password=you_password 
  
tmdb.api.key=you_tmdb_api_key  
tmdb.api.url=https://api.themoviedb.org/3  
  
server.port=3000(or whatever you want)  
  
# JWT SECRETS  
jwt.secret=(your secret)
jwt.expiration=3600000  
  
spring.jpa.hibernate.ddl-auto=update  
spring.jpa.show-sql=true
```

So you need to config your own local properties and the url to connect a data base, ok?...

To Run the API you must be in the `backend/` directory and run:
if(you use windows):
```
.\mvnw.cmd spring-boot:run
```
if(you use linux):
```
./mvnw spring-boot:run
```

To run the FrontEnd to consume the API, its very simple, i think you already know, but lets remember:

in the `frontend/` directory run:
```
npm run dev -- --host --port=8080(or whatever port you want)

well the application its very simple, enjoy it :)
