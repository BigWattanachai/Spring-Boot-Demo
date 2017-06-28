Spring Boot Demo
This application implements a Spring Boot Demo application using MySQL db

Branch https://github.com/BigWattanachai/Spring-Boot-Demo  CRUD Simple Version

Branch https://github.com/BigWattanachai/Spring-Boot-Demo/tree/refactor_version  CRUD Refactor Version

Branch https://github.com/BigWattanachai/Spring-Boot-Demo/tree/currencyconverter  Using RxJava and call external api.

1. Git-Clone this repository:
  $ git clone https://github.com/BigWattanachai/Spring-Boot-Demo.git Spring-Boot-Demo
  
2. Setup local database:(Docker required)
  $ docker-compose up -d

3. Change directory to your clone folder:
  $ cd Spring-Boot-Demo 

4. Start Application:
  $ mvn clean spring-boot:run

5. Compile everything and run all tests:
  $ mvn clean test

6. Browse to [http://localhost:8080/health](http://localhost:8080/health)

