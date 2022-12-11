import java.util.ArrayList;
import java.util.Stack;

//to print json
//philopateer Moheb
public class Tree {
    private TreeNode root;
    private XMLChecker xmlChecker;
    private int counter;
    private String[]Tags;
    private String[]words;
    private String Json_Text;
    private Stack<String> indent;

    private void setRoot(TreeNode root) {
        this.root = root;
    }

    public Tree(XMLChecker xmlChecker) {
        this.xmlChecker = xmlChecker;
        counter = 0;
        Json_Text=new String();
        indent=new Stack<>();
    }

    public TreeNode makeNode(String name,String data){
        TreeNode treeNode=new TreeNode();
        treeNode.setName(name);
        treeNode.setData(data);
        return treeNode;

    }

    //O(n*n) //n element ,n childs we make recursion at each element worst case
    public ArrayList<TreeNode> PutItInTree(int index,String[]Tags,String[]words,String name,String parent) {
        ArrayList<TreeNode>childerns=new ArrayList<>();

        for (int i = index; i < Tags.length - 1; i++) {
            if (Tags[i] != "Visited") {
                TreeNode node = new TreeNode();
                String closingtagname = Tags[i].substring(2, Tags[i].length() - 1);
                String nameTemp = name.substring(1, name.length() - 1);
                if (!parent.substring(1,parent.length()-1).equals(Tags[i].substring(2, Tags[i].length() - 1))||parent=="Root") {
                    if (!nameTemp.equals(closingtagname)) {
                        String p = Tags[i].substring(0, 2);
                        if (!p.equals("</")) {
                            String temp = Tags[i].substring(1, Tags[i].length() - 1);
                            String temp2 = Tags[i + 1].substring(2, Tags[i + 1].length() - 1);
                            if (temp.equals(temp2)) {//<id></id>?id==id? to check equality of tags
                                Tags[i] = "Visited";
                                childerns.add(makeNode(temp, words[counter]));
                                counter++;
                                name = xmlChecker.getTags()[index - 1];
                                parent = xmlChecker.getTags()[index - 2];
                            } else {
                                node.setName(Tags[i]);
                                Tags[i] = "Visited";
                                childerns.add(node);
                                node.setChilds(PutItInTree(i + 1, Tags, words, Tags[i + 1], Tags[i]));
                                if (i > 0) {
                                    parent = xmlChecker.getTags()[i - 1];
                                }
                                else{
                                    parent=xmlChecker.getTags()[0];
                                }
                            }

                        } else {
                            Tags[i] = "Visited";
                        }

                    } else {
                        Tags[i] = "Visited";
                        return childerns;
                    }
                }
                else {
                    return childerns;
                }
            }
            }
        return childerns;
        }


    public void TreeReady(){
        Tags = xmlChecker.getTags();
        words = new String[xmlChecker.getWords().length];
        int j = 0;
        for (int i = 0; i < xmlChecker.getWords().length; i++) {
            if (xmlChecker.getWords()[i] != "") {
                words[j++] = xmlChecker.getWords()[i];
            }
        }
        root =new TreeNode();
        root.setName("Root");
        root.setChilds(PutItInTree(0,Tags,words,Tags[0],"Root"));
    }

    public boolean isNumeric(String s){
        try {
            Integer num = Integer.parseInt(s);
        } catch (NumberFormatException e) {
            return false;
        }
        return true;
    }
    public String printToJson(TreeNode node){
        ArrayList<TreeNode>nodes=node.getChilds();
        for (int i=0;i<nodes.size();i++){
            if(nodes.get(i).getChilds().size()==0){
                if(isNumeric(nodes.get(i).getData())) {
                    Json_Text += indent.peek() + "\"" + nodes.get(i).getName() + "\": " + nodes.get(i).getData();
                }
                else{
                    Json_Text += indent.peek() + "\"" + nodes.get(i).getName() + "\": " + "\""+nodes.get(i).getData()+"\"";
                }
                if(nodes.size()>1&&i!=(nodes.size()-1)){
                    Json_Text+=",\n";
                }
            }
            else {
                if(indent.isEmpty()){
                    Json_Text+="{\n";
                    indent.push("    ");
                }
                Json_Text+=indent.peek()+"\""+nodes.get(i).getName().substring(1,nodes.get(i).getName().length()-1)+"\": [\n";
                String indention=indent.peek()+"    ";
                indent.push(indention);
                Json_Text=printToJson(nodes.get(i));
                indent.pop();
            }

        }
        Json_Text+="\n"+indent.peek()+"]\n";
        return Json_Text;
    }
    public void Print(){
        System.out.print(printToJson(root.getChilds().get(0)));
        Json_Text+="\n}";
    }

    boolean isEmpty(){
        return root==null;
    }

}
