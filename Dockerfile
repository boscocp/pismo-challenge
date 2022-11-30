FROM maven:3.6.3-jdk-11-openj9
COPY ./ ./
RUN mvn clean package
COPY target/account-0.1.0-SNAPSHOT.jar application.jar
ENTRYPOINT ["java","-jar","/application.jar"]