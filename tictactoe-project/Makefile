.PHONY: build run clean

build:
	docker build -t tictactoe .

run:
	docker run -it --rm tictactoe

clean:
	docker rmi tictactoe

all: build run