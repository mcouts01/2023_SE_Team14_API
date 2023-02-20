# Backend Documentation

# How to run the application

To run the backend application, ensure you have Java 17 installed on your computer, and Maven installed as well.
With those installed, open our project in the command line and run

```bash
mvn spring-boot:run -Drun.arguments=--db-password=RKm123PckLpuoyUp
```

This will run the application, with a connection to the database. The API will be open at [http://localhost:8080/](http://localhost:8080/) - which is needed for the application to work, in full, with the frontend.
