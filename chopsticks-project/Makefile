.PHONY: build run clean

build:
	docker build -t chopsticks .

run:
	docker run -it --rm chopsticks

clean:
	docker rmi chopsticks

all: build run