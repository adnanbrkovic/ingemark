# Bild jara
FROM maven:3.9.6-eclipse-temurin-21 AS build
WORKDIR /app

COPY pom.xml .
COPY src ./src

#Ako se ne koristi mockani repo, testovi se pokrecu prije nego što je postgres kontejner spreman pa treba staviti -DskipTests
#RUN mvn clean package -DskipTests
RUN mvn clean package

# Pokretanje
FROM openjdk:21-slim
WORKDIR /app

#netcat zbog race conditiona flywaya i spremnosti postgres kontejnera
RUN apt-get update && apt-get install -y netcat-openbsd
COPY --from=build /app/target/app.jar app.jar

#ENTRYPOINT ["java", "-jar", "app.jar"]
#cekati da postgres bude spreman prije pokretanja jar-a
ENTRYPOINT sh -c "until nc -z postgres 5432; do echo 'Waiting for postgres...'; sleep 2; done; java -jar app.jar"