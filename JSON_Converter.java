import java.util.ArrayList;
import java.util.Scanner;

public class JSON_Converter {

    private static String setText(XMLChecker xml,int index){
        String temp=new String();
        do{
            temp+= xml.getCorrectXML().charAt(index);
            index++;
        }while(xml.getCorrectXML().charAt(index)!='<');
        return temp;
    }
    private static ArrayList<String>CheckId(XMLChecker xml,int index){
        ArrayList<String>arrayID=new ArrayList<>();
        String s=new String();
        while(xml.getCorrectXML().charAt(index)!='f'){//follower
            s+=xml.getCorrectXML().charAt(index);
            if(s.charAt((s.length()-1))=='>'){
                arrayID.add(s);
                arrayID.add(setText(xml,index));//to add the id
                s="";
            }
        }
        return arrayID;

    }
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
                      String temp =setText(xml,index);
                      arrayOfxml.add(temp);
                      s = "";
                      break;
                  case "</id>":
                      arrayOfxml.add(s);
                      s = "";
                      break;
                  case "<name>":
                      arrayOfxml.add(s);
                      temp = setText(xml, index);
                      arrayOfxml.add(temp);
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
                      temp =setText(xml,index);
                      arrayOfxml.add(temp);
                      s = "";
                      break;
                  case "</body>":
                      arrayOfxml.add(s);
                      s = "";
                      break;
                  case "<topic>":
                      arrayOfxml.add(s);
                      temp =setText(xml,index);
                      arrayOfxml.add(temp);
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
                      ArrayList<String>tempArray=CheckId(xml,index);
                      for (int i=0;i<tempArray.size();i++){
                          arrayOfxml.add(tempArray.get(i));
                      }
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
            System.out.print("{\n");
            for (int i=0;i<xmlarraylist.size();i++){
                switch (xmlarraylist.get(i)) {
                    case "<users>":
                        System.out.print("\"users\": [\n");
                        break;
                    case "</users>":
                        System.out.print("\n]");
                        break;
                    case "<user>":
                        System.out.print("\t\"user\":[ \n");
                        break;
                    case "</user>":
                        System.out.print("\t]");
                        break;
                    case "<id>":
                        System.out.print("\t\t\"id\":"+"\""+xmlarraylist.get(i+1)+"\",\n");
                        break;
                    case "<name>":
                        System.out.print("\t\t\"name\":"+"\""+xmlarraylist.get(i+1)+"\",\n");
                        break;
                    case "<topics>":
                        System.out.print("\t\t\t\"topics\":[\n");
                        break;
                    case "</topics>":
                        System.out.print("\t\t\t]\n");
                        break;
                    case "<topic>":
                        System.out.print("\t\t\t\t\"topic\":\""+xmlarraylist.get(i+1)+"\"\n");
                        break;
                    case "<posts>":
                        System.out.print("\t\t\"posts\":[ \n");
                        break;
                    case "</posts>":
                        System.out.print("\t\t]\n");
                        break;
                    case "<post>":
                        System.out.print("\t\t\t\"post\":[ \n");
                        break;
                    case "</post>":
                        System.out.print("\t\t\t]\n");
                        break;
                    case "<body>":
                        System.out.print("\t\t\t\t\"body\": ["+"\""+ xmlarraylist.get(i+1)+"\n");
                        break;

                    case "</body>":
                        System.out.print("\t\t\t\t\n]");
                        break;
                    case "<followers>":
                        System.out.print("\t\t \"followers\": [");
                        break;
                    case "<follower>":
                        System.out.print("\t\t\t \"follower\":[");
                        break;
                    case "</follower>":
                        System.out.print("\t\t\t]");
                        break;

                    case "</followers>":
                        System.out.print("\t\t]");
                        break;
                    default:
                        break;
                }
            }
            System.out.print("\n}");

        }

    }

    public static void main(String[] args) {
        Scanner scanner=new Scanner(System.in);
        String s=scanner.next();
        XMLChecker xmlChecker=new XMLChecker(s);
        converter(xmlChecker);

    }
}
