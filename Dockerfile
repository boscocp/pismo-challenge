FROM adoptopenjdk:11-jre-hotspot
ARG JAR_FILE=account*.jar
EXPOSE 4200
COPY target/${JAR_FILE} application.jar
ENTRYPOINT ["java", "-jar", "application.jar"]