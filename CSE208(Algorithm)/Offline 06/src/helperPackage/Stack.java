package helperPackage;

/**
 * Created by Partha on 5/11/2016 at 18 :22 (6:22 PM)
 */
final public class Stack<T> extends LinkedList<T>{



    public Stack() {
        super();
    }
    public void  push(T n)
    {
        super.add(n);
    }
    public T pop()
    {

        return  super.remove(super.length()-1);
    }
    public boolean hasmoreElements()
    {
        if(super.length()!=0)
        {
            return true;
        }
        return  false;
    }
    public  int Size()
    {
        return  super.length();
    }
}
