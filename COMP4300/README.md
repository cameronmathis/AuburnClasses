COMP 4300
=====================
Computer Architecture

Overview:
-------------

This is the repository for my COMP 4300 projects.

Project 1
-------------

We have two simulators, one is a stack Machine and one is an Accumulator.
They both read in code from their respective "..type..Code.txt" files.

Each simulator has two parts. Memory simulation and instruction simulation.

We are trying to evaluate this equation:

>A*X**2 + B*X + C
>> - x = 3
>> - A = 7
>> - B = 5
>> - C = 4
>
> Answer: 82

To run each machine, look at the readme file in project 1 folder!

Project 2
-------------

I extended my accumulator-based machine into a General Purpose Register (GPR) machine that runs different instructions in different numbers of cycles (i.e. a multi-cycle machine).

The written code evaluates if an entered word is a palindrome or not. The machine uses a hex interpreter to load code.

To run this machine, look at the readme file in project 2 folder!

Project 3
-------------

I turned my fake MIPS machine into a functional 5-stage pipe line machine. Still all instructions of the MIPS ISA are not implemented but I added a few.

I added a forwarding unit. By default the machine produces some statistics about the program it just ran.

When in doubt: add NOPs.

To run this machine, look at the readme file in project 3 folder!