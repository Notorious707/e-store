FROM openjdk:17
EXPOSE 8080
ADD target/paypal-service-0.0.1-SNAPSHOT.jar paypal-service.jar
ENTRYPOINT ["java","-jar","/paypal-service.jar"]