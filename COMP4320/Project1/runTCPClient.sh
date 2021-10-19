#!/bin/bash
# compile the program in Java
javac -d out src/myFirstTCPClient.java
# execute the program
java -cp ./out myFirstTCPClient $1 $2 $3