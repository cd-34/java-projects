FROM eclipse-temurin:17-jdk

WORKDIR /app

COPY chopsticks/ ./chopsticks/

RUN javac chopsticks/*.java

CMD ["java", "chopsticks.Main"]