import java.util.ArrayList;

public  class PostSearch {
    private  ArrayList<Post> searchResults ; //to hold the search results : the posts containing the given keyword  
    //class constructor O(1)
    public PostSearch() {
        searchResults = new ArrayList<>() ; //instantiation
    }
    //helper function to change all strings to lower case O(n*m) where n is the size of string and m is the size of topics array
    private static void  toLowerCase(ArrayList<String> topics)
    { 
        for(var i : topics)
            i = i.toLowerCase();
       
    }
    //given an array of users and a search key, gives the posts containing the given key in searchResults datafield
    //O(n^2) "worst case"
    public  void searchForPost(User[] users , String key )
    { 
        key = key.toLowerCase();
        for(int i = 0 ; i < users.length ; i++)
        { 
            ArrayList<Post> postList = users[i].getPostsListCopy() ;
          
            for(int j = 0 ; j < postList.size() ; j++)
            { 
                ArrayList<String> topics = postList.get(j).getTopicsListCopy();
                 toLowerCase(topics);
                if(topics.contains(key) || postList.get(j).getText().toLowerCase().contains(key))
                { 
                     searchResults.add(postList.get(j));
                }
                                   
            }
        }
    }
   // returns the searchResults to the user. Throws a PostEmptyException if there are no posts matching 
    //O(1)
    public  ArrayList<Post> getPosts()
    { 
        if(searchResults == null)
            throw new PostEmptyException("No search results\nThere are no posts with the matching keyword\n");
        return searchResults ;
    }
    
}
