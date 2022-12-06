import java.util.ArrayList;
import java.util.Scanner;

public class JSON_Converter {
    private String JSON_text=new String();

   public static ArrayList<String> toArrayList(XMLChecker xml){
       ArrayList<String>arrayOfxml=new ArrayList<>();
       String s=new String();
       int index=0;
       //time :O(n) space: O(n)
       while(index<xml.getCorrectXML().length()){
          s+=xml.getCorrectXML().charAt(index++);
          if(s.charAt((s.length()-1))=='>') {
              switch (s) {
                  case "<users>":
                      arrayOfxml.add(s);
                      s = "";
                      break;
                  case "</users>":
                      arrayOfxml.add(s);
                      s = "";
                      break;
                  case "<user>":
                      arrayOfxml.add(s);
                      s = "";
                      break;
                  case "</user>":
                      arrayOfxml.add(s);
                      s = "";
                      break;
                  case "<id>":
                      arrayOfxml.add(s);
                      s = "";
                      break;
                  case "</id>":
                      arrayOfxml.add(s);
                      s = "";
                      break;
                  case "<name>":
                      arrayOfxml.add(s);
                      s = "";
                      break;
                  case "</name>":
                      arrayOfxml.add(s);
                      s = "";
                      break;
                  case "<topics>":
                      arrayOfxml.add(s);
                      s = "";
                      break;
                  case "</topics>":
                      arrayOfxml.add(s);
                      s = "";
                      break;
                  case "<posts>":
                      arrayOfxml.add(s);
                      s = "";
                      break;
                  case "</posts>":
                      arrayOfxml.add(s);
                      s = "";
                      break;
                  case "<post>":
                      arrayOfxml.add(s);
                      s = "";
                      break;
                  case "</post>":
                      arrayOfxml.add(s);
                      s = "";
                      break;
                  case "<body>":
                      arrayOfxml.add(s);
                      s = "";
                      break;
                  case "</body>":
                      arrayOfxml.add(s);
                      s = "";
                      break;
                  case "<topic>":
                      arrayOfxml.add(s);
                      s = "";
                      break;
                  case "</topic>":
                      arrayOfxml.add(s);
                      s = "";
                      break;
                  case "<followers>":
                      arrayOfxml.add(s);
                      s = "";
                      break;
                  case "</followers>":
                      arrayOfxml.add(s);
                      s = "";
                      break;
                  case "<follower>":
                      arrayOfxml.add(s);
                      s = "";
                      break;
                  case "</follower>":
                      arrayOfxml.add(s);
                      s = "";
                      break;
                  default:
                      arrayOfxml.add(s);
                      s = "";
                      break;
              }
          }
       }
       return arrayOfxml;
   }
    public static void converter(XMLChecker xml){
        if(!xml.isCorrect()){
            throw new IllegalArgumentException();
        }
        else{
         ArrayList<String>xmlarraylist=toArrayList(xml);
            for (int i=0;i<xmlarraylist.size();i++){
                System.out.print("{");
                switch (xmlarraylist.get(i)) {
                    case "<users>":
                        System.out.print("\"users\": [\n");
                        break;
                    case "</users>":
                        System.out.print("]");
                        break;
                    case "<user>":
                        System.out.print("\t\"user\":[ \n");
                        break;
                    case "</user>":
                        System.out.print("\t]\n");
                        break;
                    case "<id>":
                        System.out.print("\t\t\"id\":"+"\"\""+xmlarraylist.get(i+1)+"\"\",\n");
                        break;
                    case "<name>":
                        System.out.print("\t\t\"name\":"+"\"\""+xmlarraylist.get(i+1)+"\"\",\n");
                        break;
                    case "<topics>":
                        System.out.print("\t\t\t\"topics\":"+"\"\""+xmlarraylist.get(i+1)+"\"\",\n");
                        break;
                    default:
                        break;
                }
            }
            System.out.print("}");

        }

    }

    public static void main(String[] args) {
        Scanner scanner=new Scanner(System.in);
        String s=scanner.next();
        XMLChecker xmlChecker=new XMLChecker(s);
        converter(xmlChecker);

    }
}
