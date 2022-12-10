import java.util.ArrayList;
import java.util.Scanner;


public class JSON_Converter {


    public static void converter(XMLChecker xml){

            Tree tree = new Tree(xml);
            tree.TreeReady();
            tree.Print();

    }



    public static void main(String[] args) {
        Scanner scanner=new Scanner(System.in);
        String s=scanner.nextLine();
        XMLChecker xmlChecker=new XMLChecker(s);
        converter(xmlChecker);

    }
}
