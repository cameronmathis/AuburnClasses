import java.net.*;  // for Socket, ServerSocket, and InetAddress
import java.io.*;   // for IOException and Input/OutputStream

public class myFirstTCPServer {

  private static final int BUFSIZE = 32;   // Size of receive buffer

  public static void main(String[] args) throws IOException {

    if (args.length != 1)  // Test for correct # of args
      throw new IllegalArgumentException("Parameter(s): <Port>");

    int servPort = Integer.parseInt(args[0]);

    // Create a server socket to accept client connection requests
    ServerSocket servSock = new ServerSocket(servPort);

    int recvMsgSize;   // Size of received message
    byte[] byteBuffer = new byte[BUFSIZE];  // Receive buffer

    for (;;) { // Run forever, accepting and servicing connections
      Socket clntSock = servSock.accept();     // Get client connection

      System.out.println("Handling client at " +
        clntSock.getInetAddress().getHostAddress() + " on port " +
             clntSock.getPort());

      InputStream in = clntSock.getInputStream();
      OutputStream out = clntSock.getOutputStream();

      // Receive until client closes connection, indicated by -1 return
      if ((recvMsgSize = in.read(byteBuffer)) != -1) {
        String original = new String(byteBuffer);
        System.out.println("Server Received: " + original);
        StringBuilder sb = new StringBuilder(original);
        String devowelized = "";

        // Start from the front of the received data, and traverse added every character that is not a vowel
        for (int i = 0; i < recvMsgSize; i++) {
          if (sb.charAt(i) != 'A' && sb.charAt(i) != 'E' && sb.charAt(i) != 'I' && sb.charAt(i) != 'O' && sb.charAt(i) != 'U'
            && sb.charAt(i) != 'a' && sb.charAt(i) != 'e' && sb.charAt(i) != 'i' && sb.charAt(i) != 'o' && sb.charAt(i) != 'u') {
            devowelized = devowelized + sb.charAt(i);
          }
        }
        byte[] sendMsg = devowelized.getBytes();
        int sendMsgSize = devowelized.getBytes().length;
        out.write(sendMsg, 0, sendMsgSize);
        System.out.println("Server Sent: " + devowelized);
      }

      clntSock.close();  // Close the socket.  We are done with this client!
    }
    /* NOT REACHED */
  }
}
