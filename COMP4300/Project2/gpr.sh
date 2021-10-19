#1/bin/bash
#---------------------------------
# COMP 4300
# Cameron Mathis
# Project 2
# 10/20/20
# General Purpose Register Machine
#---------------------------------
case $1 in
	compile)
		g++ gprSim.cpp -std=c++11 -o gprSimulator.out
		g++ -c gprMem.cpp -std=c++11 -o gprMemory.out
		g++ -c gprReg.cpp -std=c++11 -o gprRegister.out
		;;
	run)
		./gprSimulator.out
		;;
	clean)
		rm gprSimulator.out gprMemory.out gprRegister.out
		;;
	*)
		echo command invalid or missing
		;;
esac