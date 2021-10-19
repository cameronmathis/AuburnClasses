#!/bin/bash
# compile the program in Java
javac -d out src/myFirstUDPClient.java
# execute the program
java -cp ./out myFirstUDPClient $1$2 $3