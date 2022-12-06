import java.util.ArrayList;

public class Post implements Item{
    private ArrayList<String> topics=new ArrayList<>();
    private String text;


    public ArrayList<String> getTopicsListCopy() {
        ArrayList<String> tempTopic=topics;
        return tempTopic;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }
    public void addTopic(String s){
        topics.add(s);
    }
    @Override
    public String getType(){
        return "Post";
    }
    @Override
    public String toString(){
        return getTopicsListCopy().toString()+"\n"+getText()+getType();
    }

    public static void main(String[] args) {
        Post p=new Post();
        ArrayList<String>s=new ArrayList<>();
        p.addTopic("Hello");
        p.addTopic("I");
        p.addTopic("am Philo");
        p.setText("My acc");
        p.addTopic("lllllllllll");

        System.out.println(p);
    }
}
