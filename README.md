# User API - Nisum Test

API diseñada para la creación de usuarios. Desarrollada en Java bajo arquitectura limpia.

## - Funcionalidades
* Servicio de creación de usuarios.
* Servicio de autenticación para manejar la seguridad en la aplicación.
* Servicio de consulta de usuarios
* Servicio de eliminación de usuarios

## - Tecnologías utilizadas
* Java
* Spring
* Clean architecture
* H2 Database
* Swagger
* JUnit
* Mockito
* Mapstruct
* Git

# Clean Architecture

Para explicar la arquitectura se comenzará por los componentes core de negocio (dominio), continuando con los componentes externos y por último el inicio y configuración de la aplicación.

## - Domain

Es el módulo más interno de la arquitectura, pertenece a la capa del dominio y encapsula la lógica y reglas del negocio mediante modelos y entidades del dominio.

## - Usecases

Este módulo gradle perteneciente a la capa del dominio, implementa los casos de uso del sistema, define lógica de aplicación y reacciona a las invocaciones desde el módulo de entry points, orquestando los flujos hacia el módulo de entities.

## - Infrastructure

### Driven Adapters

Los driven adapter representan implementaciones externas a nuestro sistema, como lo son conexiones a bases de datos o cualquier otro origen y/o fuente de datos con la que debamos interactuar.

### Entry Points

Los entry points representan los puntos de entrada de la aplicación o el inicio de los flujos de negocio.

## - Application

Este módulo es el más externo de la arquitectura, es el encargado de ensamblar los distintos módulos, resolver las dependencias y crear los beans de los casos de use (UseCases) de forma automática, inyectando en éstos instancias concretas de las dependencias declaradas. Además inicia la aplicación (es el único módulo del proyecto donde encontraremos la función “public static void main(String[] args)”.

# API Documentation
Se implementó Swagger para la documentación del API desarrollada.

# Testing:

* Los casos de uso cuentan con pruebas unitarias.

# Forma de utilización de la API

## Login
* Acceder a la url http://localhost:8080/swagger-ui/index.html

![image](https://user-images.githubusercontent.com/18177720/204534369-3c8495fe-4081-41c7-9824-d9d7382f880e.png)

* Cuando la aplicación se ejecuta, también lo hace el script data.sql que se encuentra dentro de los recursos del proyecto y es así como se crea un usuario admin por defecto. Se hace uso del método POST /auth/login y se envía el siguiente cuerpo de petición:
```json
{
	"email": "admin@nisum.com",
	"password": "admin"
}
```
![image](https://user-images.githubusercontent.com/18177720/204534819-fc9be416-37c6-46fe-89d3-bdb580391a72.png)

* De la respuesta del servicio se toma el token para poder acceder a los demás servicios. La respuesta luce de la siguiente manera:
```json
{
  "data": "Bearer eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJhZG1pbkBuaXN1bS5jb20iLCJleHAiOjE2Njk3MjkzMjJ9.10KqqMxu3f7u6qxj7zUHZMtLOchGekRDvPcfv2QPI4g",
  "message": "Login was successful."
}
```
* Se da click en Authorize y se agrega el token resultante.
  ![image](https://user-images.githubusercontent.com/18177720/204535050-283b94ae-ac5f-4f29-846f-10d47ee2a2e8.png)

## Creación de usuario
* Para crear un usuario se hace uso del método POST /users con el siguiente cuerpo de petición (La constraseña está validando que tenga un caracter especial, un número, una letra minúscula, una mayúscula y que sea mínimo de 8 caracteres):
```json
{
    "name": "Juan Rodriguez",
    "email": "juan@rodriguez.org",
    "password": "Hunter2.",
    "phones": [
        {
            "number": "1234567",
            "cityCode": "1",
            "countryCode": "57"
        }
    ]
}
```
![image](https://user-images.githubusercontent.com/18177720/204535516-c823d4e2-d3b7-4745-82f4-b3411082994d.png)

## Consulta de usuarios
* Para consultar los usuarios existentes se hace uso del método GET /users.
  ![image](https://user-images.githubusercontent.com/18177720/204535731-63872a90-a2bd-4b29-b2a0-0af5cb1d2cf2.png)

## Eliminación de usuario
* Para eliminar un usuario se hace uso del método DELETE /users/{id} (el id debe ser un UUID)
  ![image](https://user-images.githubusercontent.com/18177720/204535927-5e3b3452-a23a-4962-9be4-172cba163de7.png)

# Administración de la base de datos
* Para acceder a la consola de base de datos se accede a la url http://localhost:8080/h2 con las credeciales (root, root).

### Desarrollado por

Anderson Yahir Vega Castillo [LinkedIn](www.linkedin.com/in/ing-anderson-yahir-vega-castillo)
