# Super Caffe with Spring Boot + MongoDB + Docker
![Java CI with Maven](https://github.com/drubioa/demo-mongo-springboot/workflows/Java%20CI%20with%20Maven/badge.svg)
[![License: MIT](https://img.shields.io/badge/License-MIT-yellow.svg)](https://opensource.org/licenses/MIT)

# 🏃 Starting 🔥
In this demonstration we generate a project with spring boot, which has 4 controllers that belong to our Category, 
Product, Role and User models.

# 😟 Requirements 📋
Have some code editor to look at and to test it you should get install Docker and Docker-compose.

# 🔧 Installation 🔨
For this project we use docker-compose.yml to generate the containers. Use the following command:
```
    docker-compose up -d
            or
    docker-compose up
```
Once the application will be running, you should get the next containers:

````
    - supper_coffe     # our app
    - mongodb          # our database
    - mongo-express    # is a graphical MongoDB web client management tool
```` 

To test our application we can access the following route in our browser to see how it should be used:

```
    'http://localhost:8080/swagger-ui/index.html' # to display the documentation with swagger
    'http://localhost:8081/' # to access mongo-express
```