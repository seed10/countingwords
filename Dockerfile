FROM openjdk:11
ARG JAR_FILE=target/*.jar
COPY ${JAR_FILE} wordapp.jar
ENTRYPOINT ["java","-jar","/wordapp.jar"]
