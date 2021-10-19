#1/bin/bash
#---------------------------------
# COMP 4300
# Cameron Mathis
# Project 3
# 11/29/20
# General Purpose Register Machine
#---------------------------------
case $1 in
	compile)
		g++ pipeSim.cpp -std=c++11 -o plSimulator.out
		g++ -c plMemory.cpp -std=c++11 -o plMemory.out
		g++ -c plRegister.cpp -std=c++11 -o plRegister.out
		;;
	run)
		./plSimulator.out
		;;
	clean)
		rm plSimulator.out plMemory.out plRegister.out
		;;
	*)
		echo command invalid or missing
		;;
esac