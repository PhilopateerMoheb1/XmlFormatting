
/**
 *
 * @author Ghaith
 */
public class Compressor {

    public static String compress(String s) {
        s = Deformatter.deformate(s);
        StringBinaryConverter SBC = new StringBinaryConverter(s);
        BinaryStringConverter BSC = new BinaryStringConverter();
        BurrowsWheeler.transform(SBC, BSC);
        SBC = new StringBinaryConverter(BSC);
        BSC = new BinaryStringConverter();
        MoveToFront.encode(SBC, BSC);
        SBC = new StringBinaryConverter(BSC);
        BSC = new BinaryStringConverter();
        Huffman.compress(SBC, BSC);
        return BSC.toString();
    }

    public static String expand(String s) {
        StringBinaryConverter SBC = new StringBinaryConverter(s);
        BinaryStringConverter BSC = new BinaryStringConverter();
        Huffman.expand(SBC, BSC);
        SBC = new StringBinaryConverter(BSC);
        BSC = new BinaryStringConverter();
        MoveToFront.decode(SBC, BSC);
        SBC = new StringBinaryConverter(BSC);
        BSC = new BinaryStringConverter();
        BurrowsWheeler.inverseTransform(SBC, BSC);
        return Formatter.format(BSC.toString());
    }

    public static void main(String[] args) {

        String xml = """
                   <users>
                       <user>
                           <id>1</id>
                           <name>Ahmed Ali</name>
                           <posts>
                               <post>
                                   <body>
                                       Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat.
                                   </body>
                                   <topics>
                                       <topic>
                                           economy
                                       </topic>
                                       <topic>
                                           finance
                                       </topic>
                                   </topics>
                               </post>
                               <post>
                                   <body>
                                       Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat.
                                   </body>
                                   <topics>
                                       <topic>
                                           solar_energy
                                       </topic>
                                   </topics>
                               </post>
                           </posts>
                           <followers>
                               <follower>
                                   <id>2</id>
                               </follower>
                               <follower>
                                   <id>3</id>
                               </follower>
                           </followers>
                       </user>
                       <user>
                           <id>2</id>
                           <name>Yasser Ahmed</name>
                           <posts>
                               <post>
                                   <body>
                                       Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat.
                                   </body>
                                   <topics>
                                       <topic>
                                           education
                                       </topic>
                                   </topics>
                               </post>
                           </posts>
                           <followers>
                               <follower>
                                   <id>1</id>
                               </follower>
                           </followers>
                       </user>
                       <user>
                           <id>3</id>
                           <name>Mohamed Sherif</name>
                           <posts>
                               <post>
                                   <body>
                                       Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat.
                                   </body>
                                   <topics>
                                       <topic>
                                           sports
                                       </topic>
                                   </topics>
                               </post>
                           </posts>
                           <followers>
                               <follower>
                                   <id>1</id>
                               </follower>
                           </followers>
                       </user>
                   </users>""";
        System.out.println("input size: " + xml.length());

        String com = compress(xml);
        System.out.println("compressed File size:" +com.length());

        String exp = expand(com);
        System.out.println("expanded File size:" + exp.length());
        System.out.println("expanded File: \n--------------------------------------------------------------------\n-------------------------------------------------------");
        System.out.println(exp);

    }

}
