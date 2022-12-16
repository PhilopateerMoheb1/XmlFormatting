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
    private StringBuilder Json_Text;
    private Stack<String> indent;

    private void setRoot(TreeNode root) {
        this.root = root;
    }

    public Tree(XMLChecker xmlChecker) {
        this.xmlChecker = xmlChecker;
        counter = 0;
        Json_Text=new StringBuilder();
        indent=new Stack<>();
    }

    public TreeNode makeNode(String name,String data){
        TreeNode treeNode=new TreeNode();
        treeNode.setName(name);
        treeNode.setData(data);
        return treeNode;

    }


    //O(n*n) //n element ,n childs we make recursion at each element worst case
    public ArrayList<TreeNode> PutItInTree(TreeNode node,int index) {
        ArrayList<TreeNode>childerns=new ArrayList<>();
        for (int i=index;i<Tags.length;i++){
            if (Tags[i] != "Visited"){
                String closingtagname = Tags[i].substring(2, Tags[i].length() - 1);
                String nameTemp = node.getName().substring(1, node.getName().length() - 1);
                TreeNode n= new TreeNode();
                String p = Tags[i].substring(0, 2);
                if(!nameTemp.equals(closingtagname)){
                    if (!p.equals("</")) {
                        String temp = Tags[i].substring(1, Tags[i].length() - 1);
                        String temp2 = Tags[i + 1].substring(2, Tags[i + 1].length() - 1);

                        if (temp.equals(temp2)) {//<id></id>?id==id? to check equality of tags
                            Tags[i] = "Visited";
                            childerns.add(makeNode(temp, words[counter]));
                            counter++;
                        } else {
                            n.setName(Tags[i]);
                            Tags[i] = "Visited";
                            n.setChilds(PutItInTree(n,index+1));
                            childerns.add(n);
                            node.setChilds(childerns);
                        }
                    }
                    else {
                        Tags[i]="Visited";
                    }
                }
                else{
                    Tags[i]="Visited";
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
        root.setName(Tags[2]);
        root.setChilds(PutItInTree(root,3));
    }

    public boolean isNumeric(String s){
        try {
            Integer num = Integer.parseInt(s);
        } catch (NumberFormatException e) {
            return false;
        }
        return true;
    }
    public StringBuilder printToJson(TreeNode node){
        ArrayList<TreeNode>nodes=node.getChilds();
        for (int i=0;i<nodes.size();i++){
            if(nodes.get(i).getChilds().size()==0){
                if(isNumeric(nodes.get(i).getData())) {
                    Json_Text.append(indent.peek() + "\"" + nodes.get(i).getName() + "\": " + nodes.get(i).getData());
                }
                else{
                    Json_Text.append(indent.peek() + "\"" + nodes.get(i).getName() + "\": " + "\""+nodes.get(i).getData()+"\"");
                }
                if(nodes.size()>1&&i!=(nodes.size()-1)){
                    Json_Text.append(",\n");
                }
            }
            else {
                if(indent.isEmpty()){
                    Json_Text.append("{\n");
                    indent.push("    ");

                }
                Json_Text.append(indent.peek()+"\""+nodes.get(i).getName().substring(1,nodes.get(i).getName().length()-1)+"\": [\n");
                String indention=indent.peek()+"    ";
                indent.push(indention);
                Json_Text=printToJson(nodes.get(i));
                indent.pop();
            }

        }
        Json_Text.append("\n"+indent.peek()+"]\n");
        return Json_Text;
    }
    public StringBuilder Print(){

        printToJson(root);
        Json_Text.append("\n}\n");
        return Json_Text;

    }

    boolean isEmpty(){
        return root==null;
    }

}
