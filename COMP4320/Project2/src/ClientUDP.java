import java.net.*;  // for DatagramSocket, DatagramPacket, and InetAddress
import java.io.*;   // for IOException
import java.util.Scanner;
import java.util.Random; 
import java.math.BigInteger;

public class ClientUDP {
   private static final int TIMEOUT = 3000;   // resend timeout (milliseconds)
   private static final int MAXTRIES = 5;     // maximum retransmissions

   public static void main(String[] args) throws IOException {
      if (args.length != 2)  // test for correct # of args
         throw new IllegalArgumentException("Parameter(s): <Server> <Port>");
   
      InetAddress serverAddress = InetAddress.getByName(args[0]);  // server address
   
      int servPort = Integer.parseInt(args[1]);

      Random random = new Random(); 
      int request_id = random.nextInt(32767);

      ResponseDecoder decoder = new ResponseDecoderBin();
      RequestEncoder encoder = new RequestEncoderBin();

      System.out.println("\nThis program computes polynomials in the following format: P(x) = a3*x^3 + a2*x^2 + a1*x + a0\twith 0 <= ai <= 64 and 0 <= x <= 64 for all i 0 <= i <= 3.");
      int polyCounter = 1;
      Scanner scanner = new Scanner(System.in);

      for(;;) {
         System.out.println("\nPolynomial " + polyCounter + ":");
         System.out.print("Enter x: ");
         int x = scanner.nextByte();
         if (x < 0 || x > 64) {
            System.out.println("Error, x input invalid!");
            continue;
         }
         System.out.print("Enter a3: ");
         int a3 = scanner.nextByte();
         if (a3 < 0 || a3 > 64) {
            System.out.println("Error, a3 input invalid!");
            continue;
         }
         System.out.print("Enter a2: ");
         int a2 = scanner.nextByte();
         if (a2 < 0 || a2 > 64) {
            System.out.println("Error, a2 input invalid!");
            continue;
         }
         System.out.print("Enter a1: ");
         int a1 = scanner.nextByte();
         if (a1 < 0 || a1 > 64) {
            System.out.println("Error, a1 input invalid!");
            continue;
         }
         System.out.print("Enter a0: ");
         int a0 = scanner.nextByte();
         if (a0 < 0 || a0 > 64) {
            System.out.println("Error, a0 input invalid!");
            continue;
         }

         byte tml = 9;
         request_id = (request_id + 1) % 32767;
         byte checksum = ChecksumRequestCalculator(tml, request_id, x, a3, a2, a1, a0);

         Request request = new Request(tml, request_id, (byte)x, (byte)a3, (byte)a2, (byte)a1, (byte)a0, (byte)checksum);

         byte[] bytesToSend = encoder.encode(request);
         DatagramSocket socket = new DatagramSocket();
      
         socket.setSoTimeout(TIMEOUT);  // maximum receive blocking time (milliseconds)
         long sendTime = System.nanoTime();
         DatagramPacket sendPacket = new DatagramPacket(bytesToSend, bytesToSend.length, serverAddress, servPort); // sending packet
      
         DatagramPacket receivePacket = new DatagramPacket(new byte[bytesToSend.length], bytesToSend.length); // recieving packet
      
         int tries = 0;      // packets may be lost, so we have to keep trying
         boolean receivedResponse = false;
         do {
            socket.send(sendPacket);          // send the echo string
            try {
               socket.receive(receivePacket);  // attempt echo reply reception
            
               if (!receivePacket.getAddress().equals(serverAddress))  // check source
                  throw new IOException("Received packet from an unknown source");
            
               receivedResponse = true;
            } catch (InterruptedIOException e) {  // we did not get anything
               tries += 1;
               System.out.println("Timed out, " + (MAXTRIES-tries) + " more tries...");
            }
         } while ((!receivedResponse) && (tries < MAXTRIES));
         long recTime = System.nanoTime();
      
         if (receivedResponse) {
            Response response = decoder.decode(receivePacket);
            
            byte[] bytes = receivePacket.getData();
            
            System.out.println();
            char [] hexChars = hexChars(bytesToSend, tml);
            System.out.print("Sent Packet         : " + hexChars[0] + hexChars[1]);
            System.out.print(" " + hexChars[2] + hexChars[3]);
            System.out.print(" " + hexChars[4] + hexChars[5]);
            System.out.print(" " + hexChars[6] + hexChars[7]);
            System.out.print(" " + hexChars[8] + hexChars[9]);
            System.out.print(" " + hexChars[10] + hexChars[11]);
            System.out.print(" " + hexChars[12] + hexChars[13]);
            System.out.print(" " + hexChars[14] + hexChars[15]);
            System.out.println(" " + hexChars[16] + hexChars[17]);
            hexChars = hexChars(bytes, response.tml);
            System.out.print("Received Packet     : " + hexChars[0] + hexChars[1]);
            System.out.print(" " + hexChars[2] + hexChars[3]);
            System.out.print(" " + hexChars[4] + hexChars[5]);
            System.out.print(" " + hexChars[6] + hexChars[7]);
            System.out.print(" " + hexChars[8] + hexChars[9]);
            System.out.print(" " + hexChars[10] + hexChars[11]);
            System.out.print(" " + hexChars[12] + hexChars[13]);
            System.out.print(" " + hexChars[14] + hexChars[15]);
            System.out.println(" " + hexChars[16] + hexChars[17]);
            System.out.println("Original Polynomial : " + a3 + "*x^3 + " + a2 + "*x^2 + " + a1 + "*x + " + a0);
            System.out.println("X Value             : " + x);
            System.out.println("The result is       : " + response.result);
         } else {
            System.out.println("No response -- giving up.");
         }
         System.out.println("Round trip time: " + (recTime - sendTime) + " ns");
         socket.close();
         polyCounter++;

         for (;;) {
            System.out.print("\nType \"c\" to compute another polynomial. Type \"q\" to quit the program. ");
            String input = scanner.next();
            if (input.toLowerCase().equals("c")) {
               break;
            } else if (input.toLowerCase().equals("q")) {
               scanner.close();
               System.out.println("Goodbye.");
               System.exit(0);
            } else {
               System.out.println("Invalid response");
            }
         }
      }
   }

   public static byte ChecksumRequestCalculator(byte tml, int request_id, int x, int a3, int a2, int a1, int a0) {
      int temp = request_id;
      BigInteger bigInt = BigInteger.valueOf(temp);
      byte[] temp_brequest_id = bigInt.toByteArray();
      byte[] brequest_id = {0, 0};
      int j = 1;
      for (int i = temp_brequest_id.length - 1; i >= 0; i--) {
         brequest_id[j--] = temp_brequest_id[i];
      }
      byte bx = (byte)x;
      byte ba3 = (byte)a3;
      byte ba2 = (byte)a2;
      byte ba1 = (byte)a1;
      byte ba0 = (byte)a0;

      byte[] byteArray = {tml, brequest_id[0], brequest_id[1], bx, ba3, ba2, ba1, ba0};
      byte S = byteArray[0];
      for (byte i=1; i < 8; i++) {
         boolean carry = willAdditionOverflow(S, byteArray[i]);
         S = (byte) (S + byteArray[i]);
         if (carry == true) {
            S = (byte) (S + 1);
         }
      }
      return (byte) ~S;
   }

   public static boolean willAdditionOverflow(byte left, byte right) {
      try {
          Math.addExact(left, right);
          return false;
      } catch (ArithmeticException e) {
          return true;
      }
   }

   private static char[] hexChars(byte[] bytes, int length_in) {
      char [] hexChars = new char[length_in * 2];
      char[] HEX_CHARS = {'0','1','2','3','4','5','6','7','8','9','A','B','C','D','E','F'};
      for (int j = 0; j < length_in; j++) {
            int v = bytes[j] & 0xFF;
            hexChars[j * 2] = HEX_CHARS[v >>> 4];
            hexChars[j * 2 + 1] = HEX_CHARS[v & 0x0F];
      }
      return hexChars;
   }
}
