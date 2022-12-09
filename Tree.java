import java.util.ArrayList;

//to print json
//philopateer Moheb
public class Tree {
    private TreeNode root;
    private XMLChecker xmlChecker;
    private int counter;
    private String[]Tags;
    private String[]words;

    private void setRoot(TreeNode root) {
        this.root = root;
    }

    public Tree(XMLChecker xmlChecker) {
        this.xmlChecker = xmlChecker;
        counter = 0;
    }

    public TreeNode makeNode(String name,String data){
        TreeNode treeNode=new TreeNode();
        treeNode.setName(name);
        treeNode.setData(data);
        return treeNode;

    }

    //O(n*n) //n element ,n childs we make recursion at each element worst case
    public ArrayList<TreeNode> PutItInTree(int index,String[]Tags,String[]words,String name) {
        ArrayList<TreeNode>childerns=new ArrayList<>();


        for (int i = index; i < Tags.length - 1; i++) {
            TreeNode node = new TreeNode();
            if (Tags[i] != "Visited") {
                String closingtagname = Tags[i].substring(2, Tags[i].length() - 1);
                String nameTemp = name.substring(1, name.length() - 1);
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
                        } else {
                            node.setName(Tags[i]);
                            Tags[i] = "Visited";
                            childerns.add(node);
                            node.setChilds(PutItInTree(i + 1, Tags, words, Tags[i + 1]));
                        }

                    } else {
                        Tags[i] = "Visited";
                    }

                }
                else {
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
        root.setName("Root");
        root.setChilds(PutItInTree(0,Tags,words,Tags[0]));
    }


    boolean isEmpty(){
        return root==null;
    }
}
