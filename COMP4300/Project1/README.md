COMP 4300
=====================
Cameron Mathis <br/>
Project 1 <br/>
09/18/20

Overview: 
-------------

There are two simulators, one is a stack Machine and one is an Accumulator. <br/>
They both read in code from their respective "stackCode.txt" and "accumCode.txt" files.

Each simulator has two parts. The memory simulation and the processor/instruction simulation.

The simulators are trying to evaluate this equation: 

>A*X**2 + B*X + C
>> - x = 3
>> - A = 7
>> - B = 5
>> - C = 4
>
> Answer: 82

To run each machine, look below.



Stack Machine
-------------

In order to compile the stack machine, open up the terminal navigate to this project and run:
	
	$ ./stack.sh compile

You should see this:

>g++ -c stackMem.cpp -o stackMem.out <br/>
>g++ stackSim.cpp -o stackSimulator.out

Run: 

	$ ./stack.sh run

You should see this:

>82 was popped from the top of the sack. <br/>

Once you are done run:
	
	$ ./stack.sh clean

This will clean up the folder by removing the stackSimulator.out and stackMemory.out files.


Accumulator
-------------

In order to compile the stack machine, open up the terminal navigate to this project and run:
	
	$ ./accum.sh compile

You should see this:

>g++ -c accumMem.cpp -o accumMem.out <br/>
>g++ accumSim.cpp -o accumSimulator.out

Run:

	$ ./accum.sh run

You should see this:

>82 is in the internal register. <br/>

Once you are done run:

	$ ./accum.sh clean

This will clean up the folder by removing the accumSimulator.out and accumMemory.out files.

Notes
-------------

#### "stackCode.txt" and "accumCode.txt" Files ####

If you take a look in these, they will have two main sections; ".data" and ".text".

>".text"

This is where the binary for the code is.

>".data"

This is where the beginning information is provided for the computation.


#### Thoughts ####
************************************

###### Op Code Size ######

> With the requirment of supporting 140 operations, I was forced to have an 8 bit operation code. <br/>
> This was a little frustrating because all the examples in class had a 6 bit operation code. 

###### Memory Address Length ######

> I ended up going with the instruction being 32 bits because that is what we covered in class. <br/>
> Since I was forced to have an 8 bit operation code, I was only left 24 bits for the memory address.


#### Issues ####
************************************

> The main issue I had when developing my simulators was that my code would compile and run correctly in VSCode, but not even compile on the TUX machines. <br/>
> I ended up fixing the compile issue, and then my code was running differently on the TUX machines than it did on in VSCode. <br/>
> This was because of the way the TUX machines were reading in lines from the stackCode and accumCode files. For some reason, if I remove the last character of the line read in then it ran fine. I think it was reading it a whitespace or return or something.
