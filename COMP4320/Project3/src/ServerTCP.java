import java.net.*; // for Socket, ServerSocket, and InetAddress
import java.io.*; // for IOException and Input/OutputStream

public class ServerTCP {
  private static final int ECHOMAX = 9; // maximum size of datagram packet

  public static void main(String[] args) throws IOException {
    if (args.length != 1) // test for correct argument list
      throw new IllegalArgumentException("Parameter(s): <Port>");

    int serverPort = Integer.parseInt(args[0]);

    ServerSocket serverSocket = new ServerSocket(serverPort);
    RequestDecoder decoder = new RequestDecoderBin();
    ResponseEncoderBin encoder = new ResponseEncoderBin();

    System.out.println("Server is running.");

    for (;;) { // run forever, accepting and servicing connections
      Socket clientSocket = serverSocket.accept();

      Request request = decoder.decode(clientSocket.getInputStream());
      byte error_code = 0;
      byte checksum = Request.ChecksumCalculator(request.tml, request.request_id, request.x, request.a3, request.a2,
          request.a1, request.a0);
      int packetLength = request.getLength();
      // check that the checksums match
      if ((byte) request.checksum != checksum) {
        error_code = 63;
      }
      // check that byte length received is equal to object's TML value
      if (request.tml != packetLength) {
        error_code = 127;
      }

      int x = request.x;
      int a3 = request.a3;
      int a2 = request.a2;
      int a1 = request.a1;
      int a0 = request.a0;
      int opResult = 0;

      // perform calculation
      opResult = (a3) * (x * x * x) + (a2) * (x * x) + (a1) * (x) + (a0);

      byte tml = 9;
      checksum = Response.ChecksumCalculator(tml, request.request_id, error_code, opResult);
      Response response = new Response(tml, request.request_id, error_code, opResult, checksum);

      clientSocket.getOutputStream().write(encoder.encode(response));
    }
    /* NOT REACHED */
  }
}
