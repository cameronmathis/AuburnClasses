import java.io.*; // for IOExceptions

public interface RequestEncoder {
  byte[] encode(Request request) throws IOException;
}
