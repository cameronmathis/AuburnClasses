COMP 4300
=====================
Cameron Mathis - clm0081
Project 3
11/29/20

Overview:
-------------

In this project, I changed my multi-cycle machine (from project 2) to a pipeline machine.

Pipe Line Machine
-------------

In order to compile the pipeline machine, navigate to the project folder and run the command:
	
	$ ./pipeline.sh compile

In order to run the pipeline machine, run the command: 

	$ ./pipeline.sh run

Once you are done simulating the pipeline machine run:
	
	$ ./pipeline.sh clean

This will clean up the project folder.

The project is currently set for lab3c.s, but if you would like to run a different partially assembled code (lab3a.s or lab3b.s) then you can change the partiallyAssembledCodeFile string variable on line 55 of the plMemory.cpp file.

Results:
-------------

lab3a:
> Instructions Executed (IC): 68 <br/>
> Cycles Spent in Execution (C): 263 <br/>
> No Operations (NOPs): 196 <br/>

lab3b:
> Please enter a word: racecar <br/>
> "The string is a palindrome." <br/>
> Instructions Executed (IC): 65 <br/>
> Cycles Spent in Execution (C): 124 <br/>
> No Operations (NOPs): 60 <br/>

<br/>

> Please enter a word: nascar <br/> 
> "The string is not a palindrome." <br/>
> Instructions Executed (IC): 43 <br/>
> Cycles Spent in Execution (C): 86 <br/>
> No Operations (NOPs): 44 <br/>

lab3c:
> Instructions Executed (IC): 12 <br/>
> Cycles Spent in Execution (C): 16 <br/>
> No Operations (NOPs): 5 <br/>

Notes
-------------

#### "lab3a.s, lab3b.s, and lab3c.s" Files ####

This file has two main sections; ".text" and ".data".

>".text"

This is where the binary for the code is.

>".data"

This is where the beginning information is provided for the computation. (only lab2b.s contains a ".data" section)


#### Thoughts and Issues ####
************************************

Thoughts: <br/>
The implementation of this project was not as hard as I first expected it to be (still not easy) since Dr. Xiao took numerous lectures to tell us how to set it all up.


Issues: <br/>
Firstly, I ran into the same issue I had in Project 1 when reading from a file, even thought I copied my memory file exactly from Project 2 (where it worked). I eventually figured it out.

Secondly, when I tried to use the exact layout that Dr. Xiao suggested, I struggled to pass all the variables I need for certain function. As a result, some variables were made global.
