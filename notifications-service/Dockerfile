FROM maven

ADD . /app
WORKDIR /app

RUN mvn clean install -DskipTests

FROM openjdk

ARG JAR_FILE=/app/target/*.jar
COPY --from=0 ${JAR_FILE} /application.jar
ENTRYPOINT ["java", "-jar", "application.jar"]