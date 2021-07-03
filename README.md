# spring-kafka-jaeger
The goal of this project is to play with [`Spring Kafka`](https://docs.spring.io/spring-kafka/reference/html/) 
and [`Jaeger`](https://www.jaegertracing.io/) integration .

## Prerequisites

- [`Java 11+`](https://www.oracle.com/java/technologies/javase-jdk11-downloads.html)
- [`Docker`](https://www.docker.com/)
- [`Docker-Compose`](https://docs.docker.com/compose/install/)

## Start Environment

### Docker Images
  
- Open a terminal and inside `spring-kafka-jaeger` root folder run
  ```
  docker-compose up -d
  ```

- Wait until all containers are `Up (healthy)`. To check the status of the containers run
  ```
  docker-compose ps
  ```

### Build and run the app
- To build the application, go to a terminal and, inside `spring-kafka-jaeger` root folder, run the command below
  ```
  mvn clean install
  ```
- Wait until `BUILD SUCCESS` and then run the app
  ```
  mvn spring-boot:run
  ```

## Useful Links

- **Jaeger UI**

  `Jaeger UI` can be accessed at http://localhost:16686/search

## Test the app

- **Controller API**
  
  ```
  curl --location --request POST 'http://localhost:9080/user/publish' \
    header 'Content-Type: application/x-www-form-urlencoded' \
    data-urlencode 'name=TestName' \
    data-urlencode 'age=32'
  ```

## Shutdown

To stop and remove docker-compose containers, network and volumes, go to a terminal and, inside `spring-kafka-jaeger` root folder, run the command below
```
docker-compose down -v
```