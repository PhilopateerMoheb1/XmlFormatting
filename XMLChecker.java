import java.util.ArrayList;

public class XMLChecker {
    private StringBuilder XMLText ; //contains XML text 
    private boolean correct ; //set initially to true
    private int errorCount ; //set initially to zero
    
    public XMLChecker(String XMLText) { //O(1)
        this.XMLText = new StringBuilder(XMLText);
        correct = true ;
    }
    //O(n)
    public String getCorrectXML() //returns correct xml as a string  
    { 
        return XMLText.toString() ;
    }
    //O(1)
    public int getErrorCount() //returns the number of errors to user
    { 
        return errorCount;
    }
    //O(1)
    public boolean isCorrect() { //returns whether xml is correct or not 
        return correct;
    }
    /* helper function used to extract tags from the string 
        takes a char < or > and returns a vector of indices of occurences of <
    */
    //O(n^2)
    private ArrayList<Integer> getIndices(String c) //get indices of certain character
    { 
        ArrayList<Integer> indices = new ArrayList<Integer>() ;
        int currentStartingIndex  = 0 , index; 
        do 
        { //<    tag>x1<     /tag><     tag>x2<     /tag>
            index = XMLText.indexOf(c,currentStartingIndex); //O(n)
            indices.add(index);  //O(n) 
            currentStartingIndex = index + 1 ;
        }while( currentStartingIndex != 0 );
        int lastIndex = indices.size()-1 ;
        indices.remove(lastIndex); //O(n)
        return indices ;
    }
    //O(n^2) 
    public void Check()
    { 
        Stack stack = new Stack ();
        ArrayList<Integer> tagStart = getIndices("<") , tagEnd = getIndices(">"); O(n^2)
        ArrayList<StringBuilder> tags = new ArrayList<StringBuilder>();
        if(tagStart.size() != tagEnd.size()) //if number of "<" doesn't match number of ">" then XML file is invalid. 
        {   
            correct = false ;
        }
        else
        {       O(n^2)
             for(int i = 0 ; i < tagStart.size() ; i++) //add all tags to the ArrayList
            {                                                      
                tags.add(new StringBuilder(XMLText.substring(tagStart.get(i),tagEnd.get(i)+1)) );
            }
            for(StringBuilder i : tags)
            {     
                char c =i.charAt(1) ; //O(1)
                if(c == '?' || c == '!') //ignore comments and preprocessor tags
                {}
                else if(c == '/') //if closing tag check stack top, if it matches
                { 
                    try
                        { 
                            if((stack.peek().toString()).equals(i.deleteCharAt(1).toString()))
                                stack.pop();
                            else
                            { 
                                 correct = false ;
                                 return ;
                            }
                        }
                    catch(StackEmptyException e)
                        { 
                            correct = false ;
                           System.out.println("stack empty exception...XML is incosistent\n"+e);
                           throw e ;
                        }
                        
                }
                else
                { 
                   stack.push(i);
                }
            }
            correct = stack.isEmpty() ;
        }
    }
    
    
}
