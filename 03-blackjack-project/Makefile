.PHONY: build run clean all

build:
	docker build -t blackjack .

run:
	docker run -it --rm blackjack

clean:
	docker rmi blackjack

all: build run