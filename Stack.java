import java.io.*;
import java.io.Serializable;
import java.util.Vector;

public class Stack implements Serializable
{ 
    private Vector<Object> values ;
    private final int CAPACITY ;
    private int pointer ;
    public Stack(int capacity)
    {
         CAPACITY = capacity ;
         values =  new Vector<Object>(CAPACITY);
    }

    public Stack()
    {
         CAPACITY = 0 ;
        values =  new Vector<Object>();
    }
    public int getPointer() {
        return pointer;
    }

    @Override
    public String toString() {
        String s = "";
        for(int i = 0 ; i< pointer;i++)
            s+= values.get(i)+"\n";
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
        if(CAPACITY !=s.CAPACITY)
            return false;
        if(pointer != s.pointer)
            return false;
        for(int i =0 ; i < pointer  ;i++)
        { 
            if(! values.get(i).equals(s.values.get(i)))
                return false;
        }
        return true ; 
    }
    public boolean isEmpty()
    { 
        return pointer == 0 ;
    }
    public boolean isFull()
    { 
        return pointer == CAPACITY ;
    }
    public void push(Object o)
    {
         if(isFull())
             throw new StackFullException("can't push to a full stack\n") ;
         values.set(pointer++, o) ;
    }
    public Object pop()
    { 
        if(isEmpty())
            throw new StackEmptyException("can't pop to empty stack\n");
        return values.get(pointer--);
    }
    public Object peek()
    { 
        if(isEmpty())
            throw new StackEmptyException("can't pop to empty stack\n");
        return values.get(pointer-1);
    }
    public void save(String filename ) throws Exception
    {
         ObjectOutputStream oos =  new ObjectOutputStream(new FileOutputStream(filename));
         for(int i = 0 ; i < pointer ;i++ )
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

class StackFullException extends RuntimeException {

    public StackFullException(String s) {
        super(s);
    }

    public StackFullException() {
    }
    
   
}
