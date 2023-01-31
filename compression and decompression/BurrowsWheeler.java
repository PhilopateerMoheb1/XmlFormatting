import java.util.ArrayList;
import java.util.Arrays;

/**
 *
 * @author Ghaith
 */
public class BurrowsWheeler {

    private static class element implements Comparable<Object> {

        char val;
        int index;

        @Override
        public int compareTo(Object o) {
            if (val > ((element) o).val) {
                return 1;
            }
            if (val < ((element) o).val) {
                return -1;
            }
            return 0;
        }

    }

    // apply Burrows-Wheeler transform
    public static void transform(StringBinaryConverter SBC,BinaryStringConverter BSC) {
        while (SBC.hasNext()) {
            int first = 0;
            String s = SBC.readString();
            CircularSuffixArray csa = new CircularSuffixArray(s);
            for (int i = 0; i < csa.length(); i++) {
                if (csa.index(i) == 0) {
                    first = i;
                }
            }
            BSC.write(first);
            for (int i = 0; i < csa.length(); i++) {
                if (csa.index(i) == 0) {
                    BSC.write(s.charAt(csa.length() - 1));
                } else {
                    BSC.write(s.charAt(csa.index(i) - 1));
                }
            }
        }
    }

    // apply Burrows-Wheeler inverse transform
    public static void inverseTransform(StringBinaryConverter SBC,BinaryStringConverter BSC) {
        int first = SBC.readInt();

        int il = 1;
        ArrayList<element> elements = new ArrayList<>();
        while (SBC.hasNext()) {

            elements.add(new element());
            elements.get(il - 1).index = il - 1;
            elements.get(il - 1).val = SBC.readChar();
            il++;
        }
        Object[] t = elements.toArray();
        Arrays.sort(t);
        int i = first;
        int j = 0;
        char[] str = new char[t.length];
        do {
            str[j] = ((element) t[i]).val;
            i = ((element) t[i]).index;
            j++;
        } while (i != first);
        BSC.write(new String(str));

    }

}
