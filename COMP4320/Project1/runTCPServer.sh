#!/bin/bash
# compile the program in Java
javac -d out src/myFirstTCPServer.java
# execute the program
java -cp ./out myFirstTCPServer $1