# **RestFull приложение по добавлению пользователей**

## Welcome!


       
## Используемые технологии:

Java 17

Spring Boot (v2.7.3)

Thymeleaf

Bootstrap

Apache Tomcat/9.0.65

Maven 4.0.0

JDBC

Hibernate ORM core version 5.6.11.Final

Liquibase 

PostgresSQL 

H2 

Mockito 

Junit 

Lombok

## Требования к окружению

Maven 3.8.1

OpenJDK 18.0.1

Postgresql 14 


## Запуск приложения

### 1. Запуск приложения с maven. 
Перейдите в корень проекта через командную строку и выполните команды:

```
mvn clean install
```

```
mvn spring-boot:run
```

### 2. Запуск приложения  через Docker.
Создайте через консоль папку auth, войдите в нёё:

```
mkdir auth
cd auth 
```

Скачайте проект
```
git clone https://github.com/Arcady555/job4j_auth
```
Соберите контейнер
```
docker-compose build
```
Запускайте!
```
docker-compose up -d
```

Теперь, отправив запрос на http://localhost:8080/persons :
```
{
    "login" : "arcady1",
    "password" : "123"
}
```
, Вы получите регистрацию на сервере.

Чтобы получить BEER токен для авторизованной работы, запросите на http://localhost:8080/login :
```
{
    "login" : "arcady1",
    "password" : "123"
}
```

## Have a good job!
