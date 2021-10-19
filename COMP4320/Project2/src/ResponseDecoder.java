import java.io.*;   // for InputStream and IOException
import java.net.*;  // for DatagramPacket

public interface ResponseDecoder {
  Response decode(InputStream source) throws IOException;
  Response decode(DatagramPacket packet) throws IOException;
}
