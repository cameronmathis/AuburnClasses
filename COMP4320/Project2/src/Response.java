public class Response {
    public byte tml;
    public int request_id;
    public byte error;
    public int result;
    public byte checksum;
  
    public Response(byte tml, int request_id, byte error, int result, byte checksum)  {
      this.tml = tml;
      this.request_id = request_id;
      this.error = error;
      this.result = result;
      this.checksum = checksum;
    }
  
    public String toString() {
      final String EOLN = java.lang.System.getProperty("line.separator");
      String value = "TML        = " + tml + EOLN +
                     "request id = " + request_id + EOLN +
                     "error      = " + error + EOLN +
                     "result     = " + result + EOLN +
                     "checksum   = " + checksum + EOLN;
      return value;
    }
}
