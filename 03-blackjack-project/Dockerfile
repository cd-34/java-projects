FROM eclipse-temurin:17-jdk
WORKDIR /app

RUN apt-get update && apt-get install -y maven

COPY pom.xml .
COPY src/ ./src/

RUN mvn package -DskipTests

CMD ["java", "-jar", "target/blackjack-project-1.0-SNAPSHOT.jar"]