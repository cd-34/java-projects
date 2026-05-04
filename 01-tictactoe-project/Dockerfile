FROM eclipse-temurin:17-jdk

WORKDIR /app

COPY tictactoe/ ./tictactoe/

RUN javac tictactoe/*.java

CMD ["java", "tictactoe.Main"]