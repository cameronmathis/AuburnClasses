import java.io.*;   // for IOException

public interface ResponseEncoder {
  byte[] encode(Response response) throws IOException;
}