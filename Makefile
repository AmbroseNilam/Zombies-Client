all: main run

main: src/main/*.java src/input/*.java src/graphics/*.java src/exceptions/*.java src/entity/*.java src/constants/*.java
	javac -d bin -cp lib/*:src src/main/*.java src/input/*.java src/graphics/*.java src/exceptions/*.java src/entity/*.java src/constants/*.java


run:
	java -cp lib/*:bin main.Client
