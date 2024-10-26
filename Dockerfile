#build stage
FROM maven AS build

WORKDIR /app

COPY src ./src

COPY pom.xml .

RUN mvn clean package -DskipTests

# app stage
FROM openjdk:21

WORKDIR /app

COPY --from=build /app/target/*.jar app.jar

RUN ls

EXPOSE 8080

ENTRYPOINT ["java", "-jar", "app.jar"]
