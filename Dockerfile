FROM openjdk:25-ea-4-jdk-oraclelinux9

WORKDIR /app

COPY target/Task_4.jar Task_4.jar

EXPOSE 8080

ENTRYPOINT ["java", "-jar","Task_4.jar"]