#!/bin/bash
# compile the program in Java
javac -d out src/*.java
# execute the program
java -cp ./out ClientTCP $1 $2