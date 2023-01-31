import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Scanner;

public class classesGenerator {
    private int defaultNameNumber;
     public ArrayList<User> generate(XMLChecker xmlChecker){
        ArrayList<User>users=new ArrayList<>();
        Tree tree=new Tree(xmlChecker);
        tree.TreeReady();
        TreeNode treeNode=tree.getRoot();
         ArrayList<Integer>IDs=new ArrayList<Integer>();
        ArrayList<TreeNode>treeNodes=treeNode.getChilds().get(0).getChilds();
        for (int i=0;i<treeNodes.size();i++){
            if(treeNodes.get(i).getName().equals("<user>")){
                String defaultName="Social Network"+defaultNameNumber;
                defaultNameNumber++;
              User user=new User(0,defaultName);//null and 0 need to be initialize
                ArrayList<TreeNode>treeNodesChilds=treeNodes.get(i).getChilds();
                for (int j=0;j<treeNodesChilds.size();j++){
                    if(treeNodesChilds.get(j).getName().equals("id")){
                        Integer id=Integer.parseInt(treeNodesChilds.get(j).getData());
                        user.setID(id);
                        IDs.add(id);
                    }
                    else if (treeNodesChilds.get(j).getName().equals("name")){
                        user.setName(treeNodesChilds.get(j).getData());
                    }
                    else if(treeNodesChilds.get(j).getName().equals("<posts>")){
                        user.setPosts(generatePosts(treeNodesChilds.get(j),new ArrayList<Post>(),new Post()));
                    }
                    else if(treeNodesChilds.get(j).getName().equals("<followers>")){
                        user.setFollowers(generatefollowers(treeNodesChilds.get(j),new ArrayList<User>()));
                    }
                    else{
                        throw new IllegalArgumentException("Invalid XML");
                    }
                }
                users.add(user);
                continue;
            }
            else{
                throw new IllegalArgumentException("Invalid XML");
            }
        }
        for (int i=0;i<users.size();i++){
            ArrayList<User>followers=users.get(i).getFollowersListCopy();
            ArrayList<User>NewFollowers=new ArrayList<User>();
            for (int j=0;j<followers.size();j++){
                if(IDs.contains(followers.get(j).getID())){
                    NewFollowers.add(users.get(IDs.indexOf(followers.get(j).getID())));
                }
            }
            users.get(i).setFollowers(NewFollowers);
        }

        return users;
    }
    ArrayList<Post> generatePosts(TreeNode node,ArrayList<Post>posts,Post post){
        if(node.getName().equals("<post>")){
            for (int j=0;j<node.getChilds().size();j++){
                if(node.getChilds().get(j).getName().equals("body"))
                {
                    post.setText(node.getChilds().get(j).getData());
                }
                else if(node.getChilds().get(j).getName().equals("<topics>")){
                    for (int k=0;k<node.getChilds().get(j).getChilds().size();k++){
                        post.addTopic(node.getChilds().get(j).getChilds().get(k).getData());
                    }
                }
            }
            return posts;
        }
        for (int i=0;i<node.getChilds().size();i++){
            Post post1=new Post();
            posts=generatePosts(node.getChilds().get(i),posts,post1);
            posts.add(post1);
        }
        return posts;
    }

    ArrayList<User> generatefollowers(TreeNode node,ArrayList<User>users) {
        if (node.getName().equals("<follower>")) {
            for (int i = 0; i < node.getChilds().size(); i++) {
                String defaultName = "Social Network" + defaultNameNumber;
                defaultNameNumber++;
                User user = new User(0, defaultName);//null and 0 need to be initialize
                if ((i + 1) < node.getChilds().size()) {
                    if (node.getChilds().get(i).getName().equals("id") && node.getChilds().get(i + 1).getName().equals("name")) {
                        user.setID(Integer.parseInt(node.getChilds().get(i).getData()));
                        user.setName(node.getChilds().get(i + 1).getData());
                        users.add(user);
                        continue;
                    } else if (node.getChilds().get(i).getName().equals("name") && node.getChilds().get(i + 1).getName().equals("id")) {
                        user.setName(node.getChilds().get(i).getData());
                        user.setID(Integer.parseInt(node.getChilds().get(i + 1).getData()));
                        users.add(user);
                        continue;
                    }
                } else {
                    if (node.getChilds().get(i).getName().equals("id")&&node.getChilds().size()==1) {
                        user.setID(Integer.parseInt(node.getChilds().get(i).getData()));
                        users.add(user);
                        continue;
                    }
                }
            }
            return users;
        }
        for (int i=0;i<node.getChilds().size();i++){
            users=generatefollowers(node.getChilds().get(i),users);
        }
        return users;
    }

    public static void main(String[] args) {
        Scanner scanner=new Scanner(System.in);
        String s=scanner.nextLine();
        XMLChecker xmlChecker=new XMLChecker(s);
        classesGenerator classesGenerator=new classesGenerator();
       ArrayList<User>users= classesGenerator.generate(xmlChecker);
    }
}
