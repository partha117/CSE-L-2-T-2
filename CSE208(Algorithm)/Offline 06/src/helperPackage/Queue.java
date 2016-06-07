package helperPackage;



/**
 * Created by Partha on 5/11/2016 at 18 :22 (6:22 PM)
 */
final public  class Queue<T> extends LinkedList<T> {




    public Queue()
    {

        super();

    }

    public void push(T n)
    {
        super.add(n);
    }
    public T pop()
    {
        if(super.length()!=0)
        {

            return  super.remove(0);
        }
        return null;

    }
    public  boolean hasmoreElements()
    {
        if(super.length()!=0)
        {
            return true;
        }
        return  false;
    }
    public int Size()
    {
        return super.length();
    }
}
