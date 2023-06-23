# data-warehouse-project
 
## Usage

### Prerequisites

In order to run this project you will need:

    - Postgresql 15 
    - JDK 17
    - Eclipse, Intellij or any IDE capable of running Java Applications
    - Docker Desktop (Optional for running docker containers)

### Installation
```
git clone https://github.com/TheDarkHorse111/data-warehouse-project.git
```
Firstly we need to create the database the project will use as well as the entity table, we could do that by:

    - Navigate to src\main\resources\sql
    - Copy and excute script.sql file on your local postgres database

To run the project make sure to do the following:

    - Open the project directory and navigate to src\main\resources
    - Open application.properties file
    - Change these properties to allow the project to connect to the database 

    spring.datasource.url=jdbc:postgresql://localhost:5432/data_warehouse
    spring.datasource.username={{YOUR_USERNAME}}
    spring.datasource.password={{YOUR_PASSWORD}}

### Running the project

To run the project simply:

    - Navigate to src\main\java
    - Run DatawarehouseApplication.java as a java application

The project should be running and you should be able to use postman to test the exposed APIs


### Documentation
For the full API documentation visit:

    - http://localhost:8080/docs after running the application

### Docker

To run a Containerized version of the application:

    - Make sure to disable postgres and shutdown any java web application on port 8080
    - Navigate to src\main\docker
    - Open docker-compose.yaml file
    - Change the following properties to match the application.properties file
        app:
            environment:
                'SPRING_DATASOURCE_USERNAME={{YOUR_USERNAME}}'
                'SPRING_DATASOURCE_PASSWORD={{YOUR_PASSWORD}}'
        postgres:
            environment:
                'POSTGRES_USER={{YOUR_USERNAME}}'
                'POSTGRES_PASSWORD={{YOUR_PASSWORD}}'
    



run ```docker compose -f "src\main\docker\docker-compose.yaml" up -d --build ``` and you should have a container with the application and database running
