import java.io.*; // for ByteArrayOutputStream and DataOutputStream

public class RequestEncoderBin implements RequestEncoder {
  public byte[] encode(Request request) throws IOException {
    ByteArrayOutputStream buf = new ByteArrayOutputStream();
    DataOutputStream out = new DataOutputStream(buf);
    out.writeByte(request.tml);
    out.writeShort(request.request_id);
    out.writeByte(request.x);
    out.writeByte(request.a3);
    out.writeByte(request.a2);
    out.writeByte(request.a1);
    out.writeByte(request.a0);
    out.writeByte(request.checksum);

    out.flush();
    return buf.toByteArray();
  }
}
