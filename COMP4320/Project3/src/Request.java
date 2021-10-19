import java.math.BigInteger;

public class Request {
  public byte tml;
  public int request_id;
  public byte x;
  public byte a3;
  public byte a2;
  public byte a1;
  public byte a0;
  public byte checksum;

  public Request(byte tml, int request_id, byte x, byte a3, byte a2, byte a1, byte a0, byte checksum) {
    this.tml = tml;
    this.request_id = request_id;
    this.x = x;
    this.a3 = a3;
    this.a2 = a2;
    this.a1 = a1;
    this.a0 = a0;
    this.checksum = checksum;
  }

  public String toString() {
    final String EOLN = java.lang.System.getProperty("line.separator");
    String value = "TML        = " + tml + EOLN + "request id = " + request_id + EOLN + "x          = " + x + EOLN
        + "a3         = " + a3 + EOLN + "a2         = " + a2 + EOLN + "a1         = " + a1 + EOLN + "a0         = " + a0
        + EOLN + "checksum   = " + checksum + EOLN;
    return value;
  }

  public int getLength() {
    return 9;
  }

  public static byte ChecksumCalculator(byte tml, int request_id, int x, int a3, int a2, int a1, int a0) {
    int temp = request_id;
    BigInteger bigInt = BigInteger.valueOf(temp);
    byte[] temp_brequest_id = bigInt.toByteArray();
    byte[] brequest_id = { 0, 0 };
    int j = 1;
    for (int i = temp_brequest_id.length - 1; i >= 0; i--) {
       brequest_id[j--] = temp_brequest_id[i];
    }
    byte bx = (byte) x;
    byte ba3 = (byte) a3;
    byte ba2 = (byte) a2;
    byte ba1 = (byte) a1;
    byte ba0 = (byte) a0;

    byte[] byteArray = { tml, brequest_id[0], brequest_id[1], bx, ba3, ba2, ba1, ba0 };
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
