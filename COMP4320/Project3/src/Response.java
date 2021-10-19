import java.math.BigInteger;

public class Response {
  public byte tml;
  public int request_id;
  public byte error;
  public int result;
  public byte checksum;

  public Response(byte tml, int request_id, byte error, int result, byte checksum) {
    this.tml = tml;
    this.request_id = request_id;
    this.error = error;
    this.result = result;
    this.checksum = checksum;
  }

  public String toString() {
    final String EOLN = java.lang.System.getProperty("line.separator");
    String value = "TML        = " + tml + EOLN + "request id = " + request_id + EOLN + "error      = " + error + EOLN
        + "result     = " + result + EOLN + "checksum   = " + checksum + EOLN;
    return value;
  }

  public int getLength() {
    return 9;
  }

  public static byte ChecksumCalculator(byte tml, int request_id, byte error_code, int opResult) {
    int temp = request_id;
    BigInteger bigInt = BigInteger.valueOf(temp);
    byte[] temp_brequest_id = bigInt.toByteArray();
    byte[] brequest_id = { 0, 0 };
    int j = 1;
    for (int i = temp_brequest_id.length - 1; i >= 0; i--) {
      brequest_id[j--] = temp_brequest_id[i];
    }
    temp = opResult;
    BigInteger bigInt_opResult = BigInteger.valueOf(temp);
    byte[] temp_bopResult = bigInt_opResult.toByteArray();
    byte[] bopResult = { 0, 0, 0, 0 };
    j = 3;
    for (int i = temp_bopResult.length - 1; i >= 0; i--) {
      bopResult[j--] = temp_bopResult[i];
    }

    byte[] byteArray = { tml, brequest_id[0], brequest_id[1], error_code, bopResult[0], bopResult[1], bopResult[2],
        bopResult[3] };
    byte S = byteArray[0];
    for (byte i = 1; i < 8; i++) {
      boolean carry = willAdditionOverflow(S, byteArray[i]);
      S = (byte) (S + byteArray[i]);
      if (carry == true) {
        //S = (byte) (S + 1);
      }
    }
    return (byte) ~S;
  }

  private static boolean willAdditionOverflow(byte left, byte right) {
    try {
      Math.addExact(left, right);
      return false;
    } catch (ArithmeticException e) {
      return true;
    }
  }
}
