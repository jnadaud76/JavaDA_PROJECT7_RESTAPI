# Poseidon
This application is an API REST using Spring Boot framework and Spring Security.
It's a web-deployed enterprise software that aims to drive more trades for institutional investors buying and selling fixed income securities.

### Getting Started

These instructions will get you a copy of the project up and running on your local machine for development and testing purposes. See deployment for notes on how to deploy the project on a live system.

### Prerequisites

What things you need to install the software and how to install them

1. Framework: Spring Boot v2.6.4
2. Java 1.8
3. Thymeleaf
4. Bootstrap v.4.3.1
5. Maven 3.8.2
6. MySql 8.0.27

### Installing

A step by step series of examples that tell you how to get a development env running:

1.Install Java:

https://docs.oracle.com/javase/8/docs/technotes/guides/install/install_overview.html

2.Install Maven:

https://maven.apache.org/install.html

3.Install DataBase:

For security reasons, you should use your own database system with name "demo" by changing spring.datasource.url and spring.datasource.username in the application.properties file. In any case the password or the whole configuration will have to be outsourced.
Run sql script to create table doc/data.sql

### Default configuration

Controller URL = http://localhost:8080

Port can be change in application.properties

Log file location : c:/temp/logs/poseidon

### Running App

Import the code into an IDE of your choice and run the Application.java to launch the application.

Or with "spring-boot-skeleton-1.0.0.jar" file, open a Terminal and execute the below command.

`java -jar spring-boot-skeleton-1.0.0.jar`

### Testing

The app has unit tests and integration tests written.

To run the tests from maven, open a Terminal and execute the below command.

`mvn verify`

### Endpoints and URLS

For more information, run app and consult : http://localhost:8080/swagger-ui/index.html

### Maven site

You can generate a maven site containing :

- FailSafe report;
- SureFire report;
- Jacoco report;

To do so, open a Terminal and execute the below command.

`mvn verify site`