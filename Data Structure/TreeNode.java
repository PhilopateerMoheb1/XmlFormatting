import java.util.ArrayList;

public class TreeNode {
    private String name;
    private ArrayList<TreeNode>childs;
    private String data;
    private boolean parent=true;
    private boolean lastChild=false;

    public boolean isLastChild() {
        return lastChild;
    }

    public void setLastChild(boolean lastChild) {
        this.lastChild = lastChild;
    }

    public void setIsparent(boolean isparent) {
        this.parent = isparent;
    }

    public boolean Isparent() {
        return parent;
    }

    public TreeNode() {
        childs=new ArrayList<>();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    public void addChilds(TreeNode node) {
        childs.add(node);
    }

    public ArrayList<TreeNode> getChilds() {
        return childs;
    }

    public void setChilds(ArrayList<TreeNode> childs) {
        this.childs = childs;
    }
}
