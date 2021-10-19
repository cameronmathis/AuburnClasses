import java.net.*; // for Socket
import java.io.*; // for Input/OutputStream
import java.util.Scanner;
import java.util.Random;
import java.math.BigInteger;

public class ClientTCP {
   private static final int TIMEOUT = 3000; // resend timeout (milliseconds)
   private static final int MAX_TRIES = 5; // maximum retransmissions

   public static void main(String[] args) throws IOException {
      if (args.length != 2) // test for correct # of args
         throw new IllegalArgumentException("Parameter(s): <Server> <Port>");

      InetAddress serverAddress = InetAddress.getByName(args[0]); // server address
      int serverPort = Integer.parseInt(args[1]);

      ResponseDecoder decoder = new ResponseDecoderBin();
      RequestEncoder encoder = new RequestEncoderBin();

      Random random = new Random();
      int request_id = random.nextInt(32767);
      request_id = 24122;

      System.out.println(
            "\nThis program computes polynomials in the following format: P(x) = a3*x^3 + a2*x^2 + a1*x + a0\twith 0 <= ai <= 64 and 0 <= x <= 64 for all i 0 <= i <= 3.");
      int polyCounter = 1;
      Scanner scanner = new Scanner(System.in);

      for (;;) {
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
         byte checksum = Request.ChecksumCalculator(tml, request_id, x, a3, a2, a1, a0);

         Request request = new Request(tml, request_id, (byte) x, (byte) a3, (byte) a2, (byte) a1, (byte) a0,
               (byte) checksum);
         byte[] bytesToSend = encoder.encode(request);

         Socket socket;
         try {
            socket = new Socket(serverAddress, serverPort);
            socket.setSoTimeout(TIMEOUT); // maximum receive blocking time (milliseconds)

            int tries = 0; // packets may be lost, so we have to keep trying
            boolean receivedResponse = false;

            long sendTime = System.nanoTime();
            do {
               socket.getOutputStream().write(bytesToSend); // send the request
               try {
                  InputStream input = socket.getInputStream();
                  Response response = decoder.decode(input);
                  byte[] bytes = responseToBytes(response);
                  long recTime = System.nanoTime();
                  receivedResponse = true;

                  if (receivedResponse) {
                     System.out.println();
                     char[] hexChars = hexChars(bytesToSend, tml);
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
                     System.out.println("Round trip time: " + (recTime - sendTime) + " ns");
                  }
               } catch (SocketTimeoutException e) { // we did not get anything
                  tries += 1;
                  System.out.println("Timed out, " + (MAX_TRIES - tries) + " more tries...");
               }
            } while ((!receivedResponse) && (tries < MAX_TRIES));
            if (!receivedResponse) {
               System.out.println("No response -- giving up.");
            }
            socket.close();
            polyCounter++;
            shouldContinue(scanner);
         } catch (ConnectException e) {
            System.out.println("Connection to server refused.");
            shouldContinue(scanner);
         }
      }
   }

   private static char[] hexChars(byte[] bytes, int length_in) {
      char[] hexChars = new char[length_in * 2];
      char[] HEX_CHARS = { '0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'A', 'B', 'C', 'D', 'E', 'F' };
      for (int j = 0; j < length_in; j++) {
         int v = bytes[j] & 0xFF;
         hexChars[j * 2] = HEX_CHARS[v >>> 4];
         hexChars[j * 2 + 1] = HEX_CHARS[v & 0x0F];
      }
      return hexChars;
   }

   private static byte[] responseToBytes(Response resp) {
      BigInteger bigInt = BigInteger.valueOf(resp.request_id);
      byte[] temp_request_id = bigInt.toByteArray();
      byte[] request_id = { 0, 0 };
      int j = 1;
      for (int i = temp_request_id.length - 1; i >= 0; i--) {
         request_id[j--] = temp_request_id[i];
      }

      bigInt = BigInteger.valueOf(resp.result);
      byte[] temp_result = bigInt.toByteArray();
      byte[] result = { 0, 0, 0, 0 };
      j = 3;
      for (int i = temp_result.length - 1; i >= 0; i--) {
         result[j--] = temp_result[i];
      }

      byte[] returnResult = { resp.tml, request_id[0], request_id[1], resp.error, result[0], result[1], result[2],
            result[3], resp.checksum };

      return returnResult;
   }

   private static int shouldContinue(Scanner scnr) {
      for (;;) {
         System.out.print("\nType \"c\" to compute another polynomial. Type \"q\" to quit the program. ");
         String input = scnr.next();
         if (input.toLowerCase().equals("c")) {
            return 0;
         } else if (input.toLowerCase().equals("q")) {
            scnr.close();
            System.out.println("Goodbye.");
            System.exit(0);
         } else {
            System.out.println("Invalid response");
         }
      }
   }
}
