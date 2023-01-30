
import org.graphstream.graph.*;
import org.graphstream.graph.implementations.*;

/**
 *
 * @author Ghaith
 */
public class Vizualizer {

    /**
     * @param args the command line arguments
     */
    private static final int sizeMin = 30;
    private static final int degreeScale = 5;
    private static final int TxtSizeMin = 10;
    private static final int TxtDegreeScale = 3;
    protected static String styleSheet = """
    graph {
            fill-mode:gradient-vertical;                      
            fill-color: rgb(55,70,80),rgb(46,56,66);
        }
                                        
    node {
            fill-mode:gradient-vertical; 
            size: 50px, 50px;
            shape: circle;
            fill-color: #85EEF2,rgb(100,206,170);
            text-style:bold;
            text-size:20px;
                                                                                                                  
                                                                                            
        }
                                        
                                    
    edge {
        shape: line;
        fill-color: #85EEF2;
        arrow-size: 9px, 3px;
        }
    """;

    private final Graph graph;

    public Vizualizer(User[] users) {
        System.setProperty("org.graphstream.ui", "swing");

        graph = new SingleGraph("users");

        graph.setAttribute("ui.stylesheet", styleSheet);

        for (User user : users) {
            graph.addNode(String.valueOf(user.getID()));
            graph.getNode(String.valueOf(user.getID())).setAttribute("ui.label", user.getName());
        }
        for (User user : users) {
            for (User follower : user.getFollowersListCopy()) {
                String userid = String.valueOf(user.getID());
                String followeid = String.valueOf(follower.getID());
                graph.addEdge(followeid + userid, followeid, userid, true);
            }
        }

        graph.nodes().forEach(n -> {
            n.setAttribute("ui.style", "size: " + (sizeMin + degreeScale * n.getDegree()) + "px, " + (sizeMin + degreeScale * n.getDegree()) + "px;"
                    + "text-size: " + (TxtSizeMin + TxtDegreeScale * n.getDegree()) + "px;");

        });
        
    }

    public void show() {
        graph.display();
    }

    public static void main(String[] args) {

        User[] users = new User[26];

        for (char i = 0; i <= 25; i++) {
            users[i] = new User(i, String.valueOf((char) ((int) 'A' + i)));
        }
        for (char i = 0; i < 6; i++) {
            users[i].addFollower(users[25]);
        }
        for (char i = 6; i < 8; i++) {
            users[i].addFollower(users[0]);
        }
        for (char i = 8; i < 19; i++) {
            users[i].addFollower(users[5]);
        }
        for (char i = 19; i < 25; i++) {
            users[i].addFollower(users[8]);
        }
        Vizualizer v = new Vizualizer(users);
        v.show();
    }

}
