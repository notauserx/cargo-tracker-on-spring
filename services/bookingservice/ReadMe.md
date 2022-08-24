## Cargo tracker Application

A sample application build using domain driven design. Follows the book Practical Domain-Driven Design in Enterprise Java.

### Run
To run the app in Docker, use from the command prompt 

```shell
docker-compose up
```

### Swagger

http://localhost:8080/swagger-ui.html

### Mysql

Connect to mysql in mysql workbench using

localhost:3306
user: root
pass: 1234

Or
```shell
docker exec -it <container_id> bash 
mysql -u<username> -p 
```

### Rabbitmq

The management portal will be accessible at

http://localhost:15672/

user:guest
pass:guest