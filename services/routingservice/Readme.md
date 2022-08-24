### Routing service for the cargo tracker applicaiton

#### Setup

Run the mvn package to generate the jar file. 

```shell
mvn package -DskipTests
```

to build the routingservice image

```shell
docker build -t routingservice .
```

finally

```shell
docker-compose up
```

Swagger-ui will be available at

http://localhost:8081/swagger-ui/
