# TeleShows-Backend
This is TeleShows backend application.

The goal of this springboot application is to create endpoints to Get, Post, Update, Delete (CRUD) for TeleShows.

Validations:

    1.The Name must be not be null and between 3 to 30 characters.
    2.The Description must not be a null value
    3.The Duration must be not be null value.
    4.The Genre must be not be null value.

# To run application via Docker

    1.docker pull samyukthakirankumar/practice:teleservice-frontend
    2.docker pull samyukthakirankumar/practice:teleservice-backend
    3.docker run -d -p 9229:9229 --name=teleservice-backend samyukthakirankumar/practice:teleservice-backend
    4.docker run -d -p 8080:80 --name=teleservice-frontend samyukthakirankumar/practice:teleservice-frontend
    5.Open the application at http://localhost:8080
