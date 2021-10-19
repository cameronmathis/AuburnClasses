#1/bin/bash
#----------------
# COMP 4300
# Cameron Mathis
# Project 1
# 9/18/20
# Accumulator Machine
#----------------

case $1 in
	compile)
		g++ accumSim.cpp -std=c++11 -o accumSimulator.out
		g++ -c accumMem.cpp -std=c++11 -o accumMemory.out
		;;
	run)
		./accumSimulator.out
		;;
	clean)
		rm accumSimulator.out accumMemory.out
		;;
	*)
		echo command invalid or missing
		;;
esac
