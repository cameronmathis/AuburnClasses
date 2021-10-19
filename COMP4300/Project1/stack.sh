#1/bin/bash
#----------------
# COMP 4300
# Cameron Mathis
# Project 1
# 9/18/20
# Stack Machine
#----------------

case $1 in
	compile)
		g++ stackSim.cpp -std=c++11 -o stackSimulator.out
		g++ -c stackMem.cpp -std=c++11 -o stackMemory.out
		;;
	run)
		./stackSimulator.out
		;;
	clean)
		rm stackSimulator.out stackMemory.out
		;;
	*)
		echo command invalid or missing
		;;
esac
