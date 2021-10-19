COMP 4320
=====================
Cameron Mathis & Spencer Downey<br/>
Project 1 <br/>
09/15/20

Overview: 
-------------

We simulated both a TCP and UDP application. <br/>
In both applications, the client sends a string to the server. The server then removes all the vowels and sends the string
back to the client.

To run each application, look below.


TCP Application
-------------

In order to compile and run the TCP Server: 

	$ ./runTCPServer [port number]

In order to compile run the TCP Client: 

	$ ./runTCPClient [IP Address of Server] ["String"] [port number]


UDP Application
-------------

In order to compile and run the UDP Server: 

	$ ./runUDPServer [port number]

In order to compile and run the UDP Client: 

	$ j./runUDPClient [IP Address of Server] ["String"] [port number]