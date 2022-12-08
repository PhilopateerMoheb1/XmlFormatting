import java.util.ArrayList;

public class XMLChecker {

    private StringBuilder XMLText; //contains XML text 
    private boolean correct; //set initially to false
     private boolean checked; //set initially to false
    private int errorCount; //set initially to zero
    private ArrayList<String> tags;
    private StringBuilder correctXMLText;

    public XMLChecker(String XMLText) { //O(1)
        this.XMLText = new StringBuilder(XMLText);
        this.correct = false;
        this.checked = false;
        this.tags = new ArrayList<>();
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
        Stack<String> stack = new Stack<>();
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
        for (var i : tags) {
            char c = i.charAt(1); //O(1)
            if (isPreprocessorTag(i) || isCommentTag(i)) //ignore comments and preprocessor tags
            {
            } else if (isClosingTag(i)) //if closing tag check stack top, if it matches
            {
                try {
                    if (stack.peek().equals(new StringBuilder(i).deleteCharAt(1).toString())) {
                        stack.pop();
                    }
                    
                } catch (StackEmptyException e) {
                    System.out.println("stack empty exception...XML is incosistent\n" + e);
                    throw e;
                }
            } else {
                stack.push(i);
            }
        }
        correct = stack.isEmpty();

    }
     //O(1)
    private  boolean isClosingTag(String tag)
    { 
        return tag.charAt(0) =='<' && tag.charAt(1) =='/' && tag.charAt(tag.length()-1) == '>';
    }
    //O(1)
    private  boolean isOpeningTag(String tag)
    { 
        return tag.charAt(0) =='<' && tag.charAt(1) !='/' && tag.charAt(tag.length()-1) == '>';
    }
    //O(1)
    private  boolean isCommentTag(String tag)
    { 
        return tag.charAt(0) =='<' && tag.charAt(1) =='!' && tag.charAt(tag.length()-1) == '>';
    }
    //O(1)
    private  boolean isPreprocessorTag(String tag)
    { 
        return tag.charAt(0) =='<' && tag.charAt(1) =='?' && tag.charAt(tag.length()-1) == '>';
    }
}
