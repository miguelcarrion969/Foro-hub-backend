# Foro Hub

## Índice
1. [Descripción](#descripción)
2. [Tecnologías utilizadas](#tecnologías-utilizadas)
3. [Estructura del Proyecto](#estructura-del-proyecto)
4. [Endpoints](#endpoints)
   - [Autenticación](#autenticación)
   - [Gestión de Tópicos](#gestión-de-tópicos)
   - [Gestión de Respuestas](#gestión-de-respuestas)
   - [Gestión de Usuarios](#gestión-de-usuarios)
5. [Diagrama de la Base de Datos](#diagrama-de-la-base-de-datos)
6. [Configuración del Proyecto](#configuración-del-proyecto)
   - [Configuración de la Base de Datos](#configuración-de-la-base-de-datos)
   - [Ejecutar el Proyecto](#ejecutar-el-proyecto)
7. [Test del proyecto]
8. [Sobre el Desarrollador](#sobre-el-desarrollador)

## Descripción
Este proyecto es un desafío de Alura implementado con Spring Boot, que permite gestionar un foro de discusión. Los usuarios pueden interactuar con temas, responder y gestionar sus perfiles, y los administradores pueden realizar acciones como agregar cursos y gestionar usuarios. El proyecto emplea autenticación de usuarios y está respaldado por una base de datos MySQL.

## Tecnologías utilizadas
- **Spring Boot**: Framework principal para el desarrollo del backend.
- **Spring Security**: Para la autenticación y autorización de usuarios.
- **Spring Data JPA**: Para interactuar con la base de datos de forma sencilla y eficiente.
- **MySQL**: Sistema de gestión de bases de datos relacional.
- **Flyway**: Herramienta para la migración de bases de datos.
- **Lombok**: Para reducir la cantidad de código repetitivo.
- **JWT (JSON Web Tokens)**: Para la autenticación basada en tokens.
- **Springdoc OpenAPI**: Para generar documentación interactiva de la API y pruebas usando Swagger UI.

## Estructura del Proyecto
El proyecto está organizado en varios paquetes, cada uno responsable de una parte del sistema:

- **Controller**: Contiene las clases que gestionan las solicitudes HTTP y la lógica del negocio.
- **Service**: Contiene la lógica del negocio.
- **Repository**: Contiene las interfaces que interactúan con la base de datos.
- **Model**: Contiene las clases que representan las entidades del sistema.
- **Security**: Contiene la configuración de seguridad para manejar la autenticación y autorización de usuarios.

## Endpoints

### Autenticación
- **POST /login**: Autenticación de usuarios.
  - **Request body**: 
    ```json
    {
       "email":"alexoo.mar@gmail.com",
       "contrasenha":"123456"
    }
    ```
  - **Response**: Retorna un JWT válido para el usuario autenticado.

### Gestión de Tópicos
- **GET /topico**: Obtener todos los tópicos.
- **GET /topico/{id}**: Obtener un tópico por su ID.
- **POST /topico**: Crear un nuevo tópico.
  - **Request body**:
    ```json
    {
       "titulo": "Introducción a Machine Learning",
       "mensaje": "Explora los conceptos básicos de Machine Learning y cómo aplicar técnicas fundamentales para resolver problemas prácticos.",
       "estado": "ABIERTO",
       "curso_id": 5
    }

    ```
- **PUT /topico/{id}**: Actualizar un tópico.
  - **Request body**:
    ```json
    {
       "id": 2,
       "mensaje": "Este es el mensaje actualizado del sexo.",
       "estado": "ABIERTO"
    }
    ```
- **DELETE /topico/{id}**: Eliminar un tópico.

### Gestión de Respuestas
- **GET /respuesta**: Obtener todas las respuestas.
- **GET /respuesta/{id}**: Obtener una respuesta por su ID.
- **POST /respuesta**: Crear una nueva respuesta.
  - **Request body**:
    ```json
    {
	   "mensaje":"Hola, Interesante respuesta",
	   "topico_id":2,
	   "respuestaPadre_id":5
    }
    ```
- **PUT /respuesta/{id}**: Actualizar una respuesta.
  - **Request body**:
    ```json
    {
      "mensaje": "Respuesta modificada",
      "solucion": true
    }
    ```
- **DELETE /respuesta/{id}**: Eliminar una respuesta.


## Diagrama de la Base de Datos
A continuación se muestra el diagrama de la base de datos para el proyecto:

<img src="/assets/Diagrama DB.png" alt="Diagrama de Base de Datos" style="width: 700px; height: auto;">


## Configuración del Proyecto

### Configuración de la Base de Datos
Para configurar la base de datos en MySQL, asegúrate de tener MySQL instalado y crea una base de datos con el nombre que prefieras. Luego, incluye las siguientes variables de entorno en el archivo `application.properties` o `application.yml`:

```properties
spring.application.name=foro-hub
spring.datasource.url=jdbc:mysql://localhost:3306/${DB_Name}
spring.datasource.username=${DB_UserName}
spring.datasource.password=${DB_Password}
api.security.secret=${JWT_SECRET}
spring.jpa.show-sql=true
spring.jpa.properties.hibernate.format_sql=true
```
Además, asegúrate de que las variables de entorno DB_Name, DB_UserName, DB_Password y JWT_SECRET estén configuradas correctamente en tu entorno.

### Ejecutar el proyecto
1. Clona el repositorio:
git clone https://github.com/tu_usuario/foro-hub.git
2. Accede al directorio del proyecto:
cd foro-hub
3. Ejecuta el proyecto
El servidor se iniciará en http://localhost:8080.

## Test del Proyecto

Para facilitar las pruebas, se ha integrado Swagger UI. Accede a la interfaz interactiva en http://localhost:8080/swagger-ui.html una vez que el proyecto esté en funcionamiento.

## Sobre el Desarrollador
Mi nombre es Alexander Jesús Centeno Cerna y soy estudiante de Ingeniería de Sistemas en la Universidad Nacional Mayor de San Marcos (UNMSM). Tengo una gran pasión por la tecnología, especialmente en el desarrollo de software, análisis de datos y gestión de procesos. Me interesa trabajar con herramientas modernas y aprender constantemente para poder afrontar desafíos en el ámbito profesional, contribuyendo al crecimiento de proyectos innovadores como este.

<img src="https://github.com/user-attachments/assets/05880886-01b3-49ec-bad3-5553be56523d" alt="Imagen del Desarrollador" style="width: 450px; height: auto;">
