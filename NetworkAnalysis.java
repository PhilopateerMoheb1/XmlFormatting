/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package phase2;

import Phase1.Post;
import Phase1.User;
import java.util.ArrayList;
import java.util.List;
/**
 *
 * @authorAhmed Adel Hassan
 */
public class NetworkAnalysis {
    private List<User> followers;

    public NetworkAnalysis() {
        this.followers = new ArrayList<>();
    }

    public List<User> getFollowers() {
        return followers;
    }

    public void setFollowers(List<User> followers) {
        this.followers = followers;
    }
    
    public static User getTopInfluencer(User[] users) {
    int maxFollowers = 0;
    User userWithMostFollowers = null;
    for (User user : users) {
        int numFollowers = user.getFollowersListCopy().size();
        if (numFollowers > maxFollowers) {
            maxFollowers = numFollowers;
            userWithMostFollowers = user;
        }
    }

    return userWithMostFollowers;
}
public User getTopActive(User[] users) {
    User mostRepeatedFollower = null;
    int maxCount = 0;
    
    for (User user : users) {
        List<User> followers = user.getFollowersListCopy();
        for (User follower : followers) {
            int count = 0;
            for (User otherUser : users) {
                if (otherUser.getFollowersListCopy().contains(follower)) {
                    count++;
                } 
            }

            if (count > maxCount) {  // found a new most repeated follower 
                maxCount = count;  // update the max count 
                mostRepeatedFollower = follower; // update the most repeated follower 
            }  

        }

    }

    return mostRepeatedFollower;  // return the most repeated follower 
}
        public   List<User> getSuggerstions(User user) {
        List<User> suggestedFollowers  = new ArrayList<User>();
    for (User follower : user.getFollowersListCopy()) {
        for (User followerFollower : follower.getFollowersListCopy()) {
            if (!user.getFollowersListCopy().contains(followerFollower) && !suggestedFollowers.contains(followerFollower)) {
                suggestedFollowers.add(followerFollower);
            }
        }
    }

    return suggestedFollowers;
}
            public List<User> getMutul(User user1, User user2) {
    List<User> mutualFollowers = new ArrayList<>();

    for (User follower1 : user1.getFollowersListCopy()) {
        for (User follower2 : user2.getFollowersListCopy()) {
            if (follower1.equals(follower2)) {
                mutualFollowers.add(follower1);
            }
        }
    }

    return mutualFollowers;
}
	public static void main(String[] args)
        {
        NetworkAnalysis c=new NetworkAnalysis();
        User philo = new User(1,"philo");
        User samy = new User(2,"samy");
        User sa = new User(3,"sa");
        User e = new User(3,"sa");
        User n = new User(3,"sa");
        User x = new User(3,"sa");
        philo.addFollower(samy);
        philo.addFollower(sa);
        samy.addFollower(e);
        samy.addFollower(n);
        Post p = new Post();
        p.setText("i love egypt");
        p.addTopic("i ");
        p.addTopic("Love");
        philo.getPostsListCopy();
        p.addTopic("ssssssssssssss");
        philo.addPost(p);
        ArrayList <User> a=new ArrayList<> ();
        a.add(philo);
        a.add(samy);
        System.out.println(philo);
        }
    
}
