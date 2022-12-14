
/**
 *
 * @author Ghaith
 */
public class BinaryStringConverter {

    private final StringBuilder sb;

    public BinaryStringConverter() {
        sb = new StringBuilder();
    }
    public BinaryStringConverter(StringBinaryConverter SBC) {
        sb = new StringBuilder();
        sb.append(SBC.getBinary());
    }

    public void write(boolean b) {
        sb.append(b ? '1' : '0');
    }

    public void write(int i) {
        
        write((char)((i >>> 24) & 0xff));
        write((char)((i >>> 16) & 0xff));
        write((char)((i >>> 8) & 0xff));
        write((char)((i) & 0xff));
    
//        Integer.toBinaryString(i);
    }

    public void write(char c) {
        sb.append(String.format("%8s", Integer.toBinaryString(c)).replaceAll(" ", "0"));
    }

    public void write(String s) {
        for (int i = 0; i < s.length(); i++) {
            write(s.charAt(i));
        }
    }
    @Override
    public String toString(){
        if(sb.length()%8>0){
            for(int i = 0 ; i <((sb.length()%8));i++){
                sb.append('0');
            }
        }
        return convertToString(sb.toString());
    }
    
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
