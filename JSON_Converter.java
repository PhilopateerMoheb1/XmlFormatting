import java.util.ArrayList;
import java.util.Scanner;


public class JSON_Converter {


    public static void converter(XMLChecker xml){
        if(xml.isCorrect()) {
            Tree tree = new Tree(xml);
            tree.TreeReady();
        }
    }



    public static void main(String[] args) {
        Scanner scanner=new Scanner(System.in);
        String s=scanner.nextLine();
        XMLChecker xmlChecker=new XMLChecker(s);
        converter(xmlChecker);

    }
}
