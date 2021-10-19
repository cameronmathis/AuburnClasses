COMP 4320
=====================
Cameron Mathis & Spencer Downey<br/>
Project 1 <br/>
09/15/20

Overview: 
-------------

We simulationed both a TCP and UDP application. <br/>
In both applications, the client sends a string to the server. The server then removes all the vowels and sends the string
back to the client.

To run each application, look below.


TCP Application
-------------

In order to compile the TCP Server, open up the terminal navigate to this project and run:
	
	$ javac myFirstTCPServer.java

In order to run the TCP Server: 

	$ java myFirstTCPServer [port number]

In order to compile the TCP Client, open up the terminal navigate to this project and run:
	
	$ javac myFirstTCPClient.java

In order to run the TCP Client: 

	$ java myFirstTCPClient [IP Address of Server] ["String"] [port number]


UDP Application
-------------

In order to compile the UDP Server, open up the terminal navigate to this project and run:
	
	$ javac myFirstUDPServer.java

In order to run the UDP Server: 

	$ java myFirstUDPServer [port number]

In order to compile the UDP Client, open up the terminal navigate to this project and run:
	
	$ javac myFirstUDPClient.java

In order to run the UDP Client: 

	$ java myFirstUDPClient [IP Address of Server] ["String"] [port number]