FROM openjdk:17
EXPOSE 8080
ADD target/shipping-service-0.0.1-SNAPSHOT.jar shipping-service.jar
ENTRYPOINT ["java","-jar","/shipping-service.jar"]