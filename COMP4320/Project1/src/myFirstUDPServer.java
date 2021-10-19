import java.net.*;  // for DatagramSocket, DatagramPacket, and InetAddress
import java.io.*;   // for IOException

public class myFirstUDPServer {

  private static final int ECHOMAX = 255;  // Maximum size of echo datagram

  public static void main(String[] args) throws IOException {

    if (args.length != 1)  // Test for correct argument list
      throw new IllegalArgumentException("Parameter(s): <Port>");

    int servPort = Integer.parseInt(args[0]);

    DatagramSocket socket = new DatagramSocket(servPort);
    DatagramPacket packet = new DatagramPacket(new byte[ECHOMAX], ECHOMAX);

    for (;;) {  // Run forever, receiving and echoing datagrams
      socket.receive(packet);     // Receive packet from client
      byte[] receivedPacket = packet.getData();
      System.out.println("Server Sent: " + new String(receivedPacket));
      int packLength  = packet.getLength();
      int j = 0;
      byte[] devowelized = new byte [ECHOMAX];
      for (int i=0; i < packLength; i++) {
        if (receivedPacket[i] != 'A' && receivedPacket[i]  != 'E' && receivedPacket[i]  != 'I' && receivedPacket[i]  != 'O' && receivedPacket[i]  != 'U'
          && receivedPacket[i] != 'a' && receivedPacket[i]  != 'e' && receivedPacket[i]  != 'i' && receivedPacket[i]  != 'o' && receivedPacket[i]  != 'u') {
          devowelized[j] = receivedPacket[i];
          j++;
        }
      }
      packet.setData(devowelized);
      System.out.println("Handling client at " + packet.getAddress().getHostAddress() + " on port " + packet.getPort());
      socket.send(packet);       // Send the same packet back to client
      System.out.println("Server Sent: " + new String(devowelized));
      packet.setLength(ECHOMAX); // Reset length to avoid shrinking buffer
    }
    /* NOT REACHED */
  }
}
