
import java.util.ArrayList;


public  class PostSearch {
    private  ArrayList<Post> searchResults ;

    public PostSearch() {
        searchResults = new ArrayList<>() ;
    }

    
    public  void searchForPost(User[] users , String key )
    { 
        for(int i = 0 ; i < users.length ; i++)
        { 
            ArrayList<Post> postList = users[i].getPostsListCopy() ;
          
            for(int j = 0 ; j < postList.size() ; j++)
            { 
                ArrayList<String> topics = postList.get(j).getTopicsListCopy();
                if(topics.contains(key) || postList.get(j).getText().contains(key))
                { 
                     searchResults.add(postList.get(j));
                }
                                   
            }
        }
    }
    
    public  ArrayList<Post> getPosts()
    { 
        if(searchResults == null)
            throw new PostEmptyException("No search results\nThere are no posts with the matching keyword\n");
        return searchResults ;
    }
    
}
