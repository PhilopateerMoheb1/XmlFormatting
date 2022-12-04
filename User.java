import java.util.ArrayList;

public class User implements Item{
private  static int numOfUsers=0;
private int ID;
private String name;
private ArrayList<Post> posts=new ArrayList<>();
private ArrayList<User>followers=new ArrayList<>();

    public User(String name) {
        numOfUsers++;
        this.ID=numOfUsers;
        this.name = name;
    }

    public int getID() {
        return ID;
    }

    public String getName() {
        return name;
    }

    public ArrayList<Post> getPosts() {
        return posts;
    }

    public ArrayList<User> getFollowing() {
        return followers;
    }
    public void addPost(Post post){
        posts.add(post);
    }
    public void addFollow(User user){
        followers.add(user);
    }

    public static int getNumOfUsers() {
        return numOfUsers;
    }

    @Override
    public String getType(){
        return "User";
    }
    @Override
    public String toString(){
        String s="ID: "+getID()+"\n"
                +"Name: "+getName()+"\n"
                +"Posts: "+getPosts().toString()+"\n";
        s+="Following: \n";
        for (int i=0;i<followers.size();i++){
            s+="Name: "+followers.get(i).getName()+"\n"+"ID: "+followers.get(i).getID()+"\n";
        }
        return s;
    }

    public static void main(String[] args) {
        User philo=new User("philo");
        User soza=new User("Suzy");
        User marina=new User("marina");
        philo.addFollow(soza);
        philo.addFollow(marina);
        Post p=new Post();
        p.setText("i love egypt");
        p.addTopic("i ");
        p.addTopic("Love");
        p.addTopic("ssssssssssssss");
        philo.addPost(p);
        System.out.println(philo);
    }
}
