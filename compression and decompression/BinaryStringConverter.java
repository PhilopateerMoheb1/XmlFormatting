
/**
 *
 * @author Ghaith
 */
public class BinaryStringConverter {

    private final StringBuilder sb;
    // create empty converter to write bit to O(1)
    public BinaryStringConverter() {
        sb = new StringBuilder();
    }
    // create converter using bit stream from StringBinaryConverter O(n)
    public BinaryStringConverter(StringBinaryConverter SBC) {
        sb = new StringBuilder();
        sb.append(SBC.getBinary());
    }
    // wirte 1 bit O(1)
    public void write(boolean b) {
        sb.append(b ? '1' : '0');
    }
    // write 32 bit O(1)
    public void write(int i) {
        
        write((char)((i >>> 24) & 0xff));
        write((char)((i >>> 16) & 0xff));
        write((char)((i >>> 8) & 0xff));
        write((char)((i) & 0xff));
    
//        Integer.toBinaryString(i);
    }
    //write 8 bits O(1)
    public void write(char c) {
        sb.append(String.format("%8s", Integer.toBinaryString(c)).replaceAll(" ", "0"));
    }
//  write string O(1)
    public void write(String s) {
        for (int i = 0; i < s.length(); i++) {
            write(s.charAt(i));
        }
    }
    
    
    // get the string of the steam of bits
    @Override
    public String toString(){
        if(sb.length()%8>0){
            for(int i = 0 ; i <((sb.length()%8));i++){
                sb.append('0');
            }
        }
        return convertToString(sb.toString());
    }
    // convert stream of bits into string
    public static String convertToString(String Binary){
        if(Binary.length()%8>0)throw new IllegalArgumentException("Binary length not divisible by 8");
        StringBuilder sb = new StringBuilder();
        for(int i = 0 ; i < Binary.length();i+=8){
            sb.append((char)Integer.parseInt(Binary.substring(i,i+8),2));
        }
        return sb.toString();
    }
    public String getBinary(){
        return sb.toString();
    }
}
