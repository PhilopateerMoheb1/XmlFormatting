
/**
 *
 * @author Ghaith
 */
public class StringBinaryConverter {

    private int i;
    private final String Binary;
    // constructor takes string and converts it to binary and intialize the cursor to work on bit stream O(n);n number of characters
    public StringBinaryConverter(String s) {
        Binary = convertToBinary(s);
        i = 0;
    }
     // constructor takes BinaryStringConverter get binary steam from it and intialize the cursor to work on bit stream O(n);n number of characters
    public StringBinaryConverter(BinaryStringConverter BSC) {
        this.Binary = BSC.getBinary();
        this.i = 0;
    }
    // convert string to bit stream O(n) as n number of chars in string
    public static String convertToBinary(String line) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < line.length(); i++) {
            sb.append(String.format("%8s", Integer.toBinaryString(line.charAt(i))).replaceAll(" ", "0"));
        }
        return sb.toString();
    }
    // check if binary steam has next bit O(1)
    public boolean hasNext() {
        return i < (Binary.length());
    }
    //return next bit  O(1)
    public boolean readBoolean() {
        return Binary.charAt(i++) == '1';
        
    }
    //return next byte  O(1)
    public char readChar() {
        i += 8;
        return (char) Integer.parseInt(Binary.substring(i - 8, i), 2);

    }
    // return next 32 bit  O(1)
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
    // return all remaining bits  O(n)
    public String readString() {
        StringBuilder sb = new StringBuilder();
        while (hasNext()) {
            sb.append(readChar());
        }
        return sb.toString();
    }
    
    // return the saved bit stream
    public String getBinary(){
        return Binary;
    }

}
