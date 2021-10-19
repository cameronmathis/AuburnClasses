#!/bin/bash
# compile the program in Java
javac ./code/*.java
# execute the program
java -cp ./code Main $1 $2