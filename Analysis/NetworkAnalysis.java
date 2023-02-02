/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package phase2;

import Phase1.User;
import java.util.ArrayList;

import java.util.HashMap;

import java.util.Map;

public class NetworkAnalysis {

    private final User[] users;

    //O(1)
    public NetworkAnalysis(User[] users) {
        this.users = users;
    }

    // get user with most followes O(n)
    public User[] getTopinfluencer() {
        ArrayList<User> top = new ArrayList<>();
        int maxFollowers = -1;
        for (User user : users) {
            if (top.isEmpty() || user.getFollowersCount() == maxFollowers) {
                top.add(user);
                maxFollowers = user.getFollowersCount();
                continue;
            }
            if (user.getFollowersCount() > maxFollowers) {
                top.clear();
                top.add(user);
                maxFollowers = user.getFollowersCount();
            }
        }
        return top.toArray(User[]::new);
    }

    // get user with most following O(n^2) 
    public User[] getTopActive() {
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

        ArrayList<User> maxEntries = new ArrayList<>();
        int maxval = -1;

        for (Map.Entry<User, Integer> entry : count.entrySet()) {
            if (maxEntries.isEmpty() || entry.getValue().compareTo(maxval) == 0) {
                maxEntries.add(entry.getKey());
                maxval = entry.getValue();
                continue;
            }
            if (entry.getValue().compareTo(maxval) > 0) {
                maxEntries.clear();
                maxEntries.add(entry.getKey());
                maxval = entry.getValue();
            }

        }

        return maxEntries.toArray(User[]::new);

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
                    if (sug == follower2) {
                        dub = true;
                        break;
                    }
                }
                if (dub) {
                    continue;
                }
                for (User sug : followers) {
                    if (sug == follower2) {
                        dub = true;
                        break;
                    }
                }
                if (dub) {
                    continue;
                }
                suggestions.add(follower2);

            }
        }
        return (User[]) suggestions.toArray(User[]::new);
    }
}

