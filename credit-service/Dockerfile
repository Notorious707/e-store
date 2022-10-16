FROM openjdk:17
EXPOSE 8080
ADD target/credit-service-0.0.1-SNAPSHOT.jar credit-service.jar
ENTRYPOINT ["java","-jar","/credit-service.jar"]