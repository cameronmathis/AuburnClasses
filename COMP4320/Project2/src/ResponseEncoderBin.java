import java.io.*;  // for ByteArrayOutputStream and DataOutputStream

public class ResponseEncoderBin implements ResponseEncoder { 
    public byte[] encode(Response response) throws IOException {
        ByteArrayOutputStream buf = new ByteArrayOutputStream();
        DataOutputStream out = new DataOutputStream(buf);
        out.writeByte(response.tml);
        out.writeShort(response.request_id);
        out.writeByte(response.error);
        out.writeInt(response.result);
        out.writeByte(response.checksum);
    
        out.flush();
        return buf.toByteArray();
    }
}
