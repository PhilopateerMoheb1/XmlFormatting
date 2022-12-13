
/**
 *
 * @author Ghaith
 */
public class StringBinaryConverter {

    private int i;
    private final String Binary;

    public StringBinaryConverter(String s) {
        Binary = convertToBinary(s);
        i = 0;
    }
    
    public StringBinaryConverter(BinaryStringConverter BSC) {
        this.Binary = BSC.getBinary();
        this.i = 0;
    }

    public static String convertToBinary(String line) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < line.length(); i++) {
            sb.append(String.format("%8s", Integer.toBinaryString(line.charAt(i))).replaceAll(" ", "0"));
        }
        return sb.toString();
    }

    public boolean hasNext() {
        return i < (Binary.length());
    }

    public boolean readBoolean() {
        return Binary.charAt(i++) == '1';
        
    }

    public char readChar() {
        i += 8;
        return (char) Integer.parseInt(Binary.substring(i - 8, i), 2);

    }

    public int readInt() {
        int x = 0;
        for (int j = 0; j < 4; j++) {
            char c = readChar();
            x <<= 8;
            x |= c;
        }
        return x;
//        i += 32;
//        return (char) Integer.parseInt(Binary.substring(i - 32, i), 2);
    }

    public String readString() {
        StringBuilder sb = new StringBuilder();
        while (hasNext()) {
            sb.append(readChar());
        }
        return sb.toString();
    }
    
    
    public String getBinary(){
        return Binary;
    }

}
