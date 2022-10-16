FROM openjdk:17
EXPOSE 8080
ADD target/account-service-0.0.1-SNAPSHOT.jar account-service.jar
ENTRYPOINT ["java","-jar","/account-service.jar"]