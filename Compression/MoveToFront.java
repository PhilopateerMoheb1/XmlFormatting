

/**
 *
 * @author Ghaith
 */
public class MoveToFront {

    private static final int R = 256;
    private static Node frontNode;

    private static class Node {

        char val;
        Node next;

        public Node(char val, Node next) {
            this.val = val;
            this.next = next;
        }

    }

    // apply move-to-front encoding, reading from standard input and writing to standard output
    public static void encode(StringBinaryConverter SBC,BinaryStringConverter BSC) {
        intitialize();
        while (SBC.hasNext()) {
            char c = SBC.readChar();
            Node node = frontNode;
            Node prev = null;
            int i = 0;
            while (node.val != c) {
                prev = node;
                node = node.next;
                i++;
            }
            BSC.write((char) (i & 0xff));
            if (prev != null) {

                prev.next = node.next;
                node.next = frontNode;
                frontNode = node;
            }
        }
    }

    // apply move-to-front decoding, reading from standard input and writing to standard output
    public static void decode(StringBinaryConverter SBC,BinaryStringConverter BSC) {
        intitialize();
        while (SBC.hasNext()) {
            int in = (int) SBC.readChar();
            Node node = frontNode;
            Node prev = null;
            for (int i = 0; i < in; i++) {
                prev = node;
                node = node.next;

            }
            BSC.write(node.val);
            if (prev != null) {

                prev.next = node.next;
                node.next = frontNode;
                frontNode = node;
            }

        }
    }

    private static void intitialize() {
        frontNode = new Node((char) 0, null);
        Node tempNode = frontNode;
        for (int i = 1; i < R; i++) {
            tempNode.next = new Node((char) i, null);
            tempNode = tempNode.next;
        }
    }

}
