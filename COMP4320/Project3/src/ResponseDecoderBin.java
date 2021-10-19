import java.io.*; // for ByteArrayInputStream
import java.net.*; // for DatagramPacket

public class ResponseDecoderBin implements ResponseDecoder {
    public Response decode(InputStream wire) throws IOException {
        DataInputStream src = new DataInputStream(wire);
        byte tml = src.readByte();
        short request_id = src.readShort();
        byte error_code = src.readByte();
        int result = src.readInt();
        byte checksum = src.readByte();

        return new Response(tml, request_id, error_code, result, checksum);
    }

    public Response decode(DatagramPacket p) throws IOException {
        ByteArrayInputStream payload = new ByteArrayInputStream(p.getData(), p.getOffset(), p.getLength());
        return decode(payload);
    }
}
