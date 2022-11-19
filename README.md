# Getting Started

## Notes
This application is built using `Java 11`


## Building the application
The following maven commands can be used to build the application:

`mvn clean install`

`mvn clean install -DskipTests=true`

### Docker
once the jar has been built from the steps above, we can run the following command from the root directory:

`docker build  -t ordina/word-api . `

This will build the docker image for the application.

Next, we need to run the docker container by executing:

`docker run -p 8080:8080 -t ordina/word-api`

The API will be exposed on port `8080`



## Documentation
The API documentation can be found under api-docs.yaml

Once the application is started, the API can be viewed and consumed on the following end point:
[Swagger UI](http://localhost:8080/swagger-ui/index.html#/)

## Samples
