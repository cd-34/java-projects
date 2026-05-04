FROM eclipse-temurin:17-jdk

WORKDIR /app

COPY blackjack/ ./blackjack/

RUN javac blackjack/*.java

CMD ["java", "blackjack.Main"]