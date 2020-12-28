FROM openjdk:8
ADD target/todoapp.jar container/todoapp.jar
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "container/todoapp.jar"]