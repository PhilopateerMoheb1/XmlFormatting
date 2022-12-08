package folder.Project ;
import java.util.ArrayList;

public class XMLChecker {

    private StringBuilder XMLText; //contains XML text 
    private boolean correct; //set initially to false
     private boolean checked; //set initially to false
    private int errorCount; //set initially to zero
    private ArrayList<String> tags;
    private ArrayList<String> words ;
    private ArrayList<String> errors ;
    private StringBuilder correctXMLText;

    public XMLChecker(String XMLText) { //O(n)
        this.XMLText = new StringBuilder(XMLText);
        this.correct = false;
        this.checked = false;
        this.tags = new ArrayList<>();
        this.words = new ArrayList<>();
        this.errors = new ArrayList<>();
        ArrayList<Integer> tagStart = getIndices("<"), tagEnd = getIndices(">"); //O(n)
        if (tagStart.size() != tagEnd.size()) //if number of "<" doesn't match number of ">" then XML file is invalid. 
        {
            throw new IllegalArgumentException(" number of '<' doesn't matching number of '>'\n");
        }
        //   O(n) as size of subarray tag(i) is considered neglible compared to size of XML whole (n)
        for (int i = 0; i < tagStart.size(); i++) //add all tags to the ArrayList
        {
            tags.add(XMLText.substring(tagStart.get(i), tagEnd.get(i) + 1));
              
        }
        for (int i = 0; i < tagStart.size()-1; i++) //add all tags to the ArrayList
        {
            words.add(XMLText.substring(tagEnd.get(i)+1,tagStart.get(i+1)));
        }
    }
    
    //O(n)
    public String getCorrectXML() //returns correct xml as a string  
    {
        if (!checked) {
            throw new NullPointerException("you must check xml first\n");
        }
        return correctXMLText.toString();
    }
    
    //O(1)
    public int getErrorCount() //returns the number of errors to user
    {
        if (!checked) {
            throw new NullPointerException("you must check xml first\n");
        }
        return errorCount;
    }

    //O(1)
    public boolean isCorrect() { //returns whether xml is correct or not 
        return correct;
    }
    public String[] getWords()
    { 
       return words.toArray(new String[words.size()]);
    }
    public String[] getTags()
    { 
       return tags.toArray(new String[words.size()]);
    }
    public String[] getErrors()
    { 
        if(errors.isEmpty())
            errors.add("XML is error-free\n");
       return errors.toArray(new String[errorCount]);
    }
    /* helper function used to extract tags from the string 
        takes a char < or > and returns a vector of indices of occurences of <
     */
    //O(n^2)
    public ArrayList<Integer> getIndices(String c) //get indices of certain character
    {
        ArrayList<Integer> indices = new ArrayList<Integer>();
        int currentStartingIndex = 0, index;
        do { //<    tag>x1<     /tag><     tag>x2<     /tag>
            index = XMLText.indexOf(c, currentStartingIndex); //O(n)
            indices.add(index);  //O(n) 
            currentStartingIndex = index + 1;
        } while (currentStartingIndex != 0);
        int lastIndex = indices.size() - 1;
        indices.remove(lastIndex); //O(n)
        return indices;
    }

    //O(n) 
    public void Check() {
        checked = true ;
        boolean flag = true ;
        Stack<String> stack = new Stack<>();
        ArrayList<Integer> tagStart = getIndices("<"), tagEnd = getIndices(">"); //O(n)
        for (var i : tags) {
            if (isPreprocessorTag(i) || isCommentTag(i)) //ignore comments and preprocessor tags
            {
            } else if (isClosingTag(i)) //if closing tag check stack top, if it matches
            {
                try {
                    if (stack.peek().equals(new StringBuilder(i).deleteCharAt(1).toString())) {
                        stack.pop();
                    }
                    else 
                        flag = false;
                } catch (StackEmptyException e) {
                    System.out.println("stack empty exception...XML is incosistent\n" + e);
                    throw e;
                }
            } else {
                stack.push(i);
            }
        }
        correct = stack.isEmpty() && flag;

    }
    //user must invoke check method first
    public void correct()
    { 
        if(!checked)
            throw new NullPointerException("you must check xml first\n");
        if(correct)
        { 
            correctXMLText = XMLText;
             return ;
        }
           
        Stack<String> stack = new Stack<>();
        StringBuilder temp = null;
        correctXMLText = new StringBuilder();
        int tagIndex = 0, wordIndex = 0 ;
        boolean visited = false ;
        //if first tag is a closing tag then change to opening tag
       if(isClosingTag(tags.get(tagIndex)))
       { 
           temp = new StringBuilder(tags.get(tagIndex));
           correctXMLText.append(temp.deleteCharAt(1));
           stack.push(temp.toString());
           errorCount++;
           errors.add("error at tag number " + tagIndex+"\nclosing tag"+tags.get(tagIndex)+" at start of file\n");
       }
       else if(isOpeningTag(tags.get(tagIndex)))
       { 
           stack.push(tags.get(tagIndex));
           correctXMLText.append(tags.get(tagIndex));
       }
           
       else if(isPreprocessorTag(tags.get(tagIndex)) || isCommentTag(tags.get(tagIndex)))
       { 
           correctXMLText.append(tags.get(tagIndex));
           visited = true ; 
       }
       correctXMLText.append(words.get(wordIndex++));
       //loop on the rest of the tags
       for( tagIndex = 1  ; tagIndex < tags.size() && (!stack.isEmpty()|| visited) ; tagIndex++ ) //if stack is empty then document has finished you must 
       { 
           if(isOpeningTag(tags.get(tagIndex)))
           { 
               stack.push(tags.get(tagIndex));
               correctXMLText.append(tags.get(tagIndex));
           }
           else if(isClosingTag(tags.get(tagIndex))&& !stack.isEmpty())
           { 
               temp = new StringBuilder(tags.get(tagIndex));
               if( temp.deleteCharAt(1).toString().equals(stack.peek()))
               { 
                  stack.pop();
                   correctXMLText.append(tags.get(tagIndex));
               } 
               
               else
               { 
                   temp = new StringBuilder(stack.pop());
                   errorCount++;
                   errors.add("error at  tag number " + tagIndex+"\nmismatch of tags "+temp +" and " + tags.get(tagIndex));
                   correctXMLText.append(temp.insert(1, "/"));
               }
           }
           else if(isClosingTag(tags.get(tagIndex))&& stack.isEmpty())
           { 
               temp= new StringBuilder(tags.get(tagIndex)).deleteCharAt(1);
               correctXMLText.append(temp+"\n"+tags.get(tagIndex));
               errorCount++;
               errors.add("error at closing tag number " + tagIndex+"\nThere is no matching opening tag for " + tags.get(tagIndex));
           }
           else if (isPreprocessorTag(tags.get(tagIndex)) || isCommentTag(tags.get(tagIndex)))//comments and preprocessor tags
           { 
               correctXMLText.append(tags.get(tagIndex));//correctXMLText.append();
              
           }
           //normal words
           if(wordIndex < words.size())
                correctXMLText.append(words.get(wordIndex++));
        }
        while(!stack.isEmpty())
        { 
            temp = new StringBuilder(stack.pop());
            correctXMLText.append("\n"+ temp.insert(1, "/"));
            errorCount++;
            errors.add("error at end of file\nmissing closing tag: " +  temp +" is not closed\n");
        }
     }
     //O(1)
    private  boolean isClosingTag(String tag)
    { 
        return tag.charAt(0) =='<' && tag.charAt(1) =='/' && tag.charAt(tag.length()-1) == '>';
    }
    //O(1)
    private  boolean isOpeningTag(String tag)
    { 
        return tag.charAt(0) =='<' && tag.charAt(1) !='/' && tag.charAt(1) !='?'&&  tag.charAt(1) !='!'&& tag.charAt(tag.length()-1) == '>';
    }
    private  boolean isCommentTag(String tag)
    { 
        return tag.charAt(0) =='<' && tag.charAt(1) =='!' && tag.charAt(tag.length()-1) == '>';
    }
    private  boolean isPreprocessorTag(String tag)
    { 
        return tag.charAt(0) =='<' && tag.charAt(1) =='?' && tag.charAt(tag.length()-1) == '>';
    }
    
    
    
}
