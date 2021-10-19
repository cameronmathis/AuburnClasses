import java.net.*;  // for Socket
import java.io.*;   // for IOException and Input/OutputStream

public class myFirstTCPClient {

  public static void main(String[] args) throws IOException {

    if ((args.length < 2) || (args.length > 3)) { // Test for correct # of args
      throw new IllegalArgumentException("Parameter(s): <Server> <Word> <Port>");
    }

    String server = args[0];       // Server name or IP address
    // Convert input String to bytes using the default character encoding
    byte[] byteBuffer = args[1].getBytes();
    // Make sure string is less than 255 characters
    if (new String(byteBuffer).length() > 255) {
      System.out.println("Error, string too long!\r\nProgram Ending");
    }

    int servPort = (args.length == 3) ? Integer.parseInt(args[2]) : 7;

    // Create socket that is connected to server on specified port
    Socket socket = new Socket(server, servPort);
    System.out.println("Connected to server...sending echo string");

    InputStream in = socket.getInputStream();
    OutputStream out = socket.getOutputStream();

    System.out.println("Client Sent: " + new String(byteBuffer));
    long sendTime = System.nanoTime();
    out.write(byteBuffer);  // Send the encoded string to the server

    // Receive the devowelized string back from the server
    int totalBytesRcvd = 0;  // Total bytes received so far
    int bytesRcvd = -1;           // Bytes received in last read
    for (int i = 0; i <byteBuffer.length; i++) {
      byteBuffer[i] = 0;
    }
    while (totalBytesRcvd > bytesRcvd) {
      if ((bytesRcvd = in.read(byteBuffer, totalBytesRcvd, byteBuffer.length - totalBytesRcvd)) == -1) {
        throw new SocketException("Connection close prematurely");
      }
      if (bytesRcvd == -1) {
        bytesRcvd = 0;
      }
      totalBytesRcvd += bytesRcvd;
    }
    long recTime = System.nanoTime();

    System.out.println("Client Received: " + new String(byteBuffer));
    System.out.println("Round Trip Time: " + (recTime - sendTime) + " ns");

    socket.close();  // Close the socket and its streams
  }
}
