FROM openjdk:17
EXPOSE 8080
ADD target/bank-account-service-0.0.1-SNAPSHOT.jar bank-account-service.jar
ENTRYPOINT ["java","-jar","/bank-account-service.jar"]