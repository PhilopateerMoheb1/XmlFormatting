

import java.util.PriorityQueue;

/**
 *
 * @author Ghaith
 */
public class Huffman {

    // alphabet size of extended ASCII
    private static final int R = 256;

    // Do not instantiate.
    private Huffman() { }

    // Huffman trie node
    private static class Node implements Comparable<Node> {
        private final char ch;
        private final int freq;
        private final Node left, right;

        Node(char ch, int freq, Node left, Node right) {
            this.ch    = ch;
            this.freq  = freq;
            this.left  = left;
            this.right = right;
        }

        // is the node a leaf node?
        private boolean isLeaf() {
            assert ((left == null) && (right == null)) || ((left != null) && (right != null));
            return (left == null) && (right == null);
        }

        // compare, based on frequency
        public int compareTo(Node that) {
            return this.freq - that.freq;
        }
    }

 
    public static void compress(StringBinaryConverter SBC,BinaryStringConverter BSC) {
        // read the input
        String s = SBC.readString();
        char[] input = new char[s.length()] ;//= s.toCharArray();
        for(int i = 0;i < s.length();i++){
            input[i] = s.charAt(i);
        }
        
        // tabulate frequency counts
        int[] freq = new int[R];
        for (int i = 0; i < input.length; i++)
            freq[input[i]]++;

        // build Huffman trie
        Node root = buildTrie(freq);

        // build code table
        String[] st = new String[R];
        buildCode(st, root, "");

        // print trie for decoder
        writeTrie(root,BSC);

        // print number of bytes in original uncompressed message
        BSC.write(input.length);

        // use Huffman code to encode input
        for (int i = 0; i < input.length; i++) {
            String code = st[input[i]];
            for (int j = 0; j < code.length(); j++) {
                if (code.charAt(j) == '0') {
                    BSC.write(false);
                }
                else if (code.charAt(j) == '1') {
                    BSC.write(true);
                }
                else throw new IllegalStateException("Illegal state");
            }
        }

        
    }

    // build the Huffman trie given frequencies
    private static Node buildTrie(int[] freq) {

        // initialize priority queue with singleton trees
        PriorityQueue<Node> pq = new PriorityQueue<Node>();
        for (char c = 0; c < R; c++)
            if (freq[c] > 0)
                pq.add(new Node(c, freq[c], null, null));

        // merge two smallest trees
        while (pq.size() > 1) {
            Node left  = pq.poll();
            Node right = pq.poll();
            Node parent = new Node('\0', left.freq + right.freq, left, right);
            pq.add(parent);
        }
        return pq.poll();
    }


    // write bitstring-encoded trie to standard output
    private static void writeTrie(Node x,BinaryStringConverter BSC) {
        if (x.isLeaf()) {
            BSC.write(true);
            BSC.write(x.ch);
            return;
        }
        BSC.write(false);
        writeTrie(x.left,BSC);
        writeTrie(x.right,BSC);
    }

    // make a lookup table from symbols and their encodings
    private static void buildCode(String[] st, Node x, String s) {
        if (!x.isLeaf()) {
            buildCode(st, x.left,  s + '0');
            buildCode(st, x.right, s + '1');
        }
        else {
            st[x.ch] = s;
        }
    }

    
    public static void expand(StringBinaryConverter SBC,BinaryStringConverter BSC) {

        // read in Huffman trie from input stream
        Node root = readTrie(SBC);

        // number of bytes to write
        int length = SBC.readInt();

        // decode using the Huffman trie
        for (int i = 0; i < length; i++) {
            Node x = root;
            while (!x.isLeaf()) {
                boolean bit = false;
                
                 bit = SBC.readBoolean();
                if (bit) x = x.right;
                else     x = x.left;
            }
            BSC.write(x.ch);
        }
    }


    private static Node readTrie(StringBinaryConverter SBC) {
        boolean isLeaf = SBC.readBoolean();
        if (isLeaf) {
            return new Node(SBC.readChar(), -1, null, null);
        }
        else {
            return new Node('\0', -1, readTrie(SBC), readTrie(SBC));
        }
    }

    
}