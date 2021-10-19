#!/bin/bash
# compile the program in Java
javac -d out src/myFirstUDPServer.java
# execute the program
java -cp ./out myFirstUDPServer $1