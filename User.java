import java.util.ArrayList;

public class User implements Item {

    private int ID;
    private String name;
    private ArrayList<Post> posts = new ArrayList<>();
    private ArrayList<User> followers = new ArrayList<>();

    public User(int ID, String name) {

        this.ID = ID;
        this.name = name;
    }

    public int getID() {
        return ID;
    }

    public String getName() {
        return name;
    }

    public ArrayList<Post> getPostsListCopy() {
        ArrayList<Post> postsTemp = posts;
        return postsTemp;
    }

    public ArrayList<User> getFollowersListCopy() {
        ArrayList<User> followersTemp = followers;
        return followersTemp;
    }

    public void addPost(Post post) {
        posts.add(post);
    }

    public void addFollower(User user) {
        followers.add(user);
    }


    @Override
    public String getType() {
        return "User";
    }

    @Override
    public String toString() {
        String s = "ID: " + getID() + "\n"
                + "Name: " + getName() + "\n"
                + "Posts: " + getPostsListCopy().toString() + "\n";
        s += "Following: \n";
        for (int i = 0; i < followers.size(); i++) {
            s += "Name: " + followers.get(i).getName() + "\n" + "ID: " + followers.get(i).getID() + "\n";
        }
        return s;
    }

    public static void main(String[] args) {
        User philo = new User(1,"philo");
        User samy = new User(2,"samia");
        User sa = new User(3,"sa");
        philo.addFollower(samy);
        philo.addFollower(sa);
        Post p = new Post();
        p.setText("i love egypt");
        p.addTopic("i ");
        p.addTopic("Love");
        philo.getPostsListCopy();
        p.addTopic("ssssssssssssss");
        philo.addPost(p);
        System.out.println(philo);
    }
}
