# Jednoduchá databáze uchazečů

## Project description:

SpringBoot aplikace na téma jednoduché databáze uchazečů.
Aplikace obsahuje API a databázi do které se ukládaji údaje o uchazeči. V databázi jsou
uloženy i technologie, které se vážou k uchazeči. Každá vazba mezi uchazečem a technologií
obsahuje úroveň (1-10) a poznámku

## Technologie:

Spring Boot
Java
PostgreSQL


## How to build :

mvn clean install

## Examples of usage

GET http://localhost:7777/candidates

POST http://localhost:7777/candidates

{
    "name": "name",
    "surname": "surname",
    "email": "mail@gmail.com",
    "phoneNumber": "12312334234234"
}

DELETE http://localhost:7777/candidates/{id}

PUT http://localhost:7777/candidates/{id}

{
    "name": "name",
    "surname": "surname",
    "email": "mail@gmail.com",
    "phoneNumber": "12312334234234"
}

GET http://localhost:7777/technologies 

POST http://localhost:7777/technologies 

{
    "name": "Spring",
    "description": "Framework"
}

PUT http://localhost:7777/technologies 

{
    "name": "Spring",
    "description": "Framework"
}

DELETE http://localhost:7777/technologies/{id}


POST http://localhost:7777/candidates/{id}/technologies

{
    "technologyId": 1,
    "levelOfTechnology": 1,
    "note": "Bad"
}


GET http://localhost:7777/candidates/{id}/technologies

GET http://localhost:7777//technologies/{id}/candidates


