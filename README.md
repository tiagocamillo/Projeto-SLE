# Electronic Auction System Project
Academic project for Programming Language II. Creation of an Electronic Auction System.

## Acess Swagger Documentation
http://localhost:8080/swagger/

## Members

| Avatar            							| Student         | GitHub                                                      | LinkedIn                                              |
| -------------------------------------------- | ---------------- | -------------------------------------------------------------- | ----------------------------------------------------- |
| <img src = "./Documentation/imgAna.jpg" width="60" >|__Ana Clara Leal__| [![](https://bit.ly/3f9Xo0P)](https://github.com/heyanaleal)      | [![](https://bit.ly/2P1ZogM)](https://www.linkedin.com/in/ana-clara-oliveira-leal-723169220/) |
| <img src = "./Documentation/imgTiago.jpg" width="60" >|__Tiago Camillo__| [![](https://bit.ly/3f9Xo0P)](https://github.com/tiagocamillo) | [![](https://bit.ly/2P1ZogM)](https://www.linkedin.com/in/tiago-camillo-277257192/) |



## Documentation

This project uses Quarkus, the Supersonic Subatomic Java Framework.

If you want to learn more about Quarkus, please visit its website: https://quarkus.io/ .

### Running the application in dev mode

You can run your application in dev mode that enables live coding using:
```shell script
./mvnw compile quarkus:dev
```

> **_NOTE:_**  Quarkus now ships with a Dev UI, which is available in dev mode only at http://localhost:8080/q/dev/.

### Packaging and running the application

The application can be packaged using:
```shell script
./mvnw package
```
It produces the `quarkus-run.jar` file in the `target/quarkus-app/` directory.
Be aware that it’s not an _über-jar_ as the dependencies are copied into the `target/quarkus-app/lib/` directory.

The application is now runnable using `java -jar target/quarkus-app/quarkus-run.jar`.

If you want to build an _über-jar_, execute the following command:
```shell script
./mvnw package -Dquarkus.package.type=uber-jar
```

The application, packaged as an _über-jar_, is now runnable using `java -jar target/*-runner.jar`.

### Creating a native executable

You can create a native executable using: 
```shell script
./mvnw package -Dnative
```

Or, if you don't have GraalVM installed, you can run the native executable build in a container using: 
```shell script
./mvnw package -Dnative -Dquarkus.native.container-build=true
```

You can then execute your native executable with: `./target/lp2-sle-1.0.0-SNAPSHOT-runner`

If you want to learn more about building native executables, please consult https://quarkus.io/guides/maven-tooling.

### Related Guides

- Hibernate ORM ([guide](https://quarkus.io/guides/hibernate-orm)): Define your persistent model with Hibernate ORM and Jakarta Persistence
- JDBC Driver - H2 ([guide](https://quarkus.io/guides/datasource)): Connect to the H2 database via JDBC
- SmallRye OpenAPI ([guide](https://quarkus.io/guides/openapi-swaggerui)): Document your REST APIs with OpenAPI - comes with Swagger UI
- RESTEasy Classic ([guide](https://quarkus.io/guides/resteasy)): REST endpoint framework implementing Jakarta REST and more

### Provided Code

#### Hibernate ORM

Create your first JPA entity

[Related guide section...](https://quarkus.io/guides/hibernate-orm)



#### RESTEasy JAX-RS

Easily start your RESTful Web Services

[Related guide section...](https://quarkus.io/guides/getting-started#the-jax-rs-resources)
