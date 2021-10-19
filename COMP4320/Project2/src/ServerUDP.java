import java.net.*;  // for DatagramSocket, DatagramPacket, and InetAddress
import java.io.*;   // for IOException
import java.math.BigInteger;
import java.util.Arrays;

public class ServerUDP {
  private static final int ECHOMAX = 9;  // maximum size of datagram packet

  public static void main(String[] args) throws IOException {
    if (args.length != 1)  // test for correct argument list
      throw new IllegalArgumentException("Parameter(s): <Port>");

    int serverPort = Integer.parseInt(args[0]);

    DatagramSocket socket = new DatagramSocket(serverPort);
    RequestDecoder decoder = new RequestDecoderBin();
    ResponseEncoderBin encoder = new ResponseEncoderBin();
    
    System.out.println("Server is running.");

    for (;;) {  // run forever, receiving and echoing datagrams
      DatagramPacket packet = new DatagramPacket(new byte[ECHOMAX], ECHOMAX);
      socket.receive(packet);     // receive packet from client

      Request request = decoder.decode(packet);
      byte error_code = 0;
      byte checksum = ChecksumRequestCalculator(request.tml, request.request_id, request.x, request.a3, request.a2, request.a1, request.a0);
      int packetLength  = packet.getLength();
      // check that the checksums match
      if((byte)request.checksum != checksum) {
        error_code = 63;
      }
      // check that byte length recieved is equal to object's TML value
      if(request.tml != packetLength) {
        error_code = 127;
      }

      int x = request.x;
      int a3 = request.a3;
      int a2 = request.a2;
      int a1 = request.a1;
      int a0 = request.a0;
      int opResult = 0;
      
      // perform calculation
      opResult = (a3)*(x*x*x) + (a2)*(x*x) + (a1)*(x) + (a0);

      byte tml = 9;
      checksum = ChecksumResponseCalculator(tml, request.request_id, error_code, opResult);
      Response response = new Response(tml, request.request_id, error_code, opResult, checksum);

      byte[] bin = encoder.encode(response);
      packet.setData(bin);
      socket.send(packet); // send the packet back to client
    }
    /* NOT REACHED */
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

  public static byte ChecksumResponseCalculator(byte tml, int request_id, byte error_code, int opResult) {
    int temp = request_id;
    BigInteger bigInt = BigInteger.valueOf(temp);
    byte[] temp_brequest_id = bigInt.toByteArray();
    byte[] brequest_id = {0, 0};
    int j = 1;
    for (int i = temp_brequest_id.length - 1; i >= 0; i--) {
       brequest_id[j--] = temp_brequest_id[i];
    }
    temp = opResult;
    BigInteger bigInt_opResult = BigInteger.valueOf(temp);
    byte[] temp_bopResult = bigInt_opResult.toByteArray();
    byte[] bopResult = {0, 0, 0, 0};
    j = 3;
    for (int i = temp_bopResult.length - 1; i >= 0; i--) {
      bopResult[j--] = temp_bopResult[i];
    }

    byte[] byteArray = {tml, brequest_id[0], brequest_id[1], error_code, bopResult[0], bopResult[1], bopResult[2], bopResult[3]};
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
}
