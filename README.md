# UDPIntro

/////////////////// INSTRUCTIONS

1. Download the two java files "UDPClient.java" and "UDPServer.java" and the commons-validator-1.7.jar file and place them all in the same folder.

2. Open a command prompt at this directory with the three files.

3. In the command prompt compile the Server file by entering:

	javac UDPServer.java
	
4. In the command prompt run the compiled Server file by entering:

	java UDPServer
	
5. Open a new command prompt at the same directory with the three files.

6. In the command prompt compile the Client file by entering:

	javac -cp ".;commons-validator-1.7.jar;" UDPClient.java
	
7. In the command prompt run the compiled Client file by entering:

	java -cp ".;commons-validator-1.7.jar;" UDPClient
	
8. In the command prompt running the Client file follow the instructions given using your ip address for the network address, 6789 for the port number, then provide any message you'd like.
