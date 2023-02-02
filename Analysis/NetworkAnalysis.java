/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package phase2;

import Phase1.Post;
import Phase1.User;
import java.util.ArrayList;
import java.util.List;
import java.util.HashMap;
import java.util.Map;
/**
 *
 * @authorAhmed Adel Hassan
 */
public class NetworkAnalysis {

    private final User[] users;

    //O(1)
    public NetworkAnalysis(User[] users) {
        this.users = users;
    }

    // get user with most followes O(n)
    public User getTopinfluencer() {
        User top = null;
        for (User user : users) {
            if (top == null) {
                top = user;
                continue;
            }
            if (user.getFollowersCount() > top.getFollowersCount()) {
                top = user;
            }
        }
        return top;
    }

    // get user with most following O(n^2) 
    public User getTopActive() {
        HashMap<User, Integer> count = new HashMap<>();
        for (User user : users) {
            for (User follower : user.getFollowersListCopy()) {
                if (count.get(follower) == null) {
                    count.put(follower, 1);
                    continue;
                }
                count.put(follower, count.get(follower) + 1);
            }
        }

        Map.Entry<User, Integer> maxEntry = null;

        for (Map.Entry<User, Integer> entry : count.entrySet()) {
            if (maxEntry == null || entry.getValue().compareTo(maxEntry.getValue()) > 0) {
                maxEntry = entry;
            }
        }

        return maxEntry.getKey();

    }

    //get mutual friends between two users O(n^2)
    public User[] getMutual(User user1, User user2) {
        ArrayList<User> mutuals = new ArrayList<>();
        for (User follower1 : user1.getFollowersListCopy()) {
            for (User follower2 : user2.getFollowersListCopy()) {
                if (follower1 == follower2) {
                    mutuals.add(follower1);
                }
            }
        }
        return (User[]) mutuals.toArray(User[]::new);
    }

    // get followrs of the followers O(n^3)
    public User[] getSuggerstions(User user) {
        ArrayList<User> suggestions = new ArrayList<>();
        ArrayList<User> followers = user.getFollowersListCopy();
        for (User follower1 : followers) {
            for (User follower2 : follower1.getFollowersListCopy()) {
                if (user == follower2) {
                    continue;
                }
                boolean dub = false;
                for (User sug : suggestions) {
                    if (sug == follower2){
                        dub = true;
                        break;
                    }
                }
                if(dub) continue;
                for (User sug : followers) {
                    if (sug == follower2){
                        dub = true;
                        break;
                    }
                }
                if(dub) continue;
                suggestions.add(follower2);

            }
        }
        return (User[]) suggestions.toArray(User[]::new);
    }
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
