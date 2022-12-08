/*stack class implemented by Mina Mounir*/
package folder.Project;
import java.io.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Vector;

public class Stack<T> implements Serializable
{ 
    private ArrayList<T> values ;
   public Stack()
    {
       values =  new ArrayList<T>();
    }
    public int size() {
        return values.size();
    }

    @Override
    public String toString() {
        String s = "";
        for(var i : values )
            s+= i+"\n";
        return s;
    }
    @Override
    public boolean equals(Object o )
    { 
        if(this == o)
            return true ;
        if(!(o instanceof Stack))
            return false;
        Stack s =  (Stack) o ;
        if(size() != s.size())
            return false;
        for(int i =0 ; i < values.size()  ;i++)
        { 
            if(! values.get(i).equals(s.values.get(i)))
                return false;
        }
        return true ; 
    }
    public boolean isEmpty()
    { 
        return values.size() == 0 ;
    }
    
    public void push(T o)
    {
         
         values.add(o)   ;
    }
    public T pop()
    { 
        if(isEmpty())
            throw new StackEmptyException("can't pop to empty stack\n");
        return values.remove(values.size()-1);
    }
    public T peek()
    { 
        if(isEmpty())
            throw new StackEmptyException("can't pop to empty stack\n");
        return values.get(values.size()-1);
    }
    public void save(String filename ) throws Exception
    {
         ObjectOutputStream oos =  new ObjectOutputStream(new FileOutputStream(filename));
         for(int i = 0 ; i < values.size() ;i++ )
         { 
             if(! (values.get(i) instanceof Serializable))
                 throw new NotSerializableException();
             oos.writeObject(values.get(i));
             oos.close();
         }
    }
    public static Stack open(String filename) throws Exception
    {
         ObjectInputStream ois = new ObjectInputStream(new FileInputStream(filename));
         Stack s = (Stack) ois.readObject();
         return s;
    }
}
class StackEmptyException extends RuntimeException {

    public StackEmptyException(String s) {
        super(s);
    }

    public StackEmptyException() {
    }
}

