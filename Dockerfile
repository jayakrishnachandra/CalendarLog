# Stage 1: Build the application
FROM maven:3.8.5-openjdk-17 AS build
COPY . .
RUN mvn clean package -DskipTests

# Stage 2: Run the application
FROM openjdk:17.0.1-jdk-slim
COPY --from=build /target/CalendarLog-0.0.1-SNAPSHOT.jar /CalendarLog.jar
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "CalendarLog.jar"]