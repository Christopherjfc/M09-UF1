package iticbcn.xifratge;

/* 
 * Clase TextXifrat 
 */

 public class TextXifrat {
    private byte[] bytes;

    public TextXifrat(byte[] bytes) {
        this.bytes = bytes;
    }

    public byte[] getBytes() {
        return bytes;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (byte b : bytes) {
            sb.append(String.format("%02x", b));
        }
        return sb.toString();
    }
}

