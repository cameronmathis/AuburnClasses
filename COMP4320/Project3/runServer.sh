#!/bin/bash
# compile the program in Java
javac -d out src/*.java
# execute the program
java -cp ./out ServerTCP $1