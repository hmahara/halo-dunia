# Use an official OpenJDK runtime as a parent image
FROM openjdk:17-jdk-alpine

RUN addgroup -g 1001 appuser && adduser \
    --disabled-password \
    --gecos "" \
    --home /app \
    --ingroup appuser \
    --no-create-home \
    --uid 1001 \
    appuser

USER 1001

WORKDIR /app
EXPOSE 2016

COPY target/halodunia-0.0.2.jar /app/
COPY /src/main/resources/application-docker-compose.properties /data/application.properties
# Set the command to run the Spring Boot application
#CMD ["java", "-jar", "xxx-0.0.1-SNAPSHOT.jar"]
CMD java $JAVA_OPTS -jar /app/halodunia-0.0.2.jar $SERVICE_OPTS
