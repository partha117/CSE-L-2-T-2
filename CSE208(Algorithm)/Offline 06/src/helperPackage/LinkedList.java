package helperPackage;

/**
 * Created by Partha on 5/17/2016 at 22 :00 (10:00 PM)
 */
public class LinkedList<T> {


    private Node head;
    private Node tail;
    private  int size;
    static public  final  int NOT_FOUND=-9999;

    public LinkedList() {
        size=0;
        head=null;
        tail=null;
    }
    public  void addFirst(T n)
    {
        Node temp=new Node(n);
        if(size==0)
        {
            head=tail=temp;
            size++;
        }
        else
        {
            temp.setNext(head);
            head.setPrev(temp);
            head=temp;
            size++;

        }

    }
    public void add(T n)
    {
        Node temp=new Node(n);
        if(size==0)
        {
            head=tail=temp;
            size++;
        }
        else
        {
            tail.setNext(temp);
            temp.setPrev(tail);
            tail=temp;
            size++;
        }
    }
    public  boolean isEmpty()
    {
        if(size==0)
        {
            return  true;
        }
        return  false;
    }
    public  void add(T n,int index)
    {

        if(index==0)
        {
            addFirst(n);
        }
        else if(index==size)
        {
           add(n);
        }
        else if(index<size)
        {
            Node temp=head;
            Node temp1=new Node(n);
            for (int i = 0; i<size;i++)
            {
                if(i==index)
                {
                    temp1.setNext(temp);
                    temp1.setPrev((Node)temp.getPrev());
                    ((Node)temp1.getPrev()).setNext(temp1);
                    ((Node)temp1.getNext()).setPrev(temp1);
                    size++;
                    break;
                }
                temp=(Node)temp.getNext();
            }
        }
    }
    public  T remove(int index)
    {
        if(index<size)
        {
            if(size==1)
            {
                Node temp=(Node)head;
                head=tail=null;
                size--;
                return  (T)temp.getElement();
            }

            if(index==0)
            {
                Node temp=(Node)head;
                head=(Node)temp.getNext();
                head.setPrev(null);
                size--;
                return  (T)temp.getElement();
            }
            Node temp=head;
            for(int i=0;i<size;i++)
            {

                if(i==index)
                {
                    ((Node)temp.getPrev()).setNext(temp.getNext());
                    size--;
                    return  (T)temp.getElement();
                }
                temp=(Node)temp.getNext();
            }

        }
        return null;
    }
    public  int length()
    {
        return  size;
    }
    public  T getElementAt(int index)
    {
        if(index<size)
        {
            Node temp=head;
            for(int i=0;i<size;i++)
            {
                if(i==index)
                {

                    return  (T)temp.getElement();
                }
                temp=(Node)temp.getNext();
            }

        }
        return null;
    }
    private  Node getNodeElement(int index)
    {
        if(index<size)
        {
            Node temp=head;
            for(int i=0;i<size;i++)
            {
                if(i==index)
                {

                    return  temp;
                }
                temp=(Node)temp.getNext();
            }

        }
        return null;
    }
    public  int searchItem(T n)
    {
        Comparable listElement=(Comparable)(head.getElement());
        Comparable nElement=(Comparable)n;
        Node temp=head;
        for(int i=0;i<size;i++)
        {

            if(listElement.compareTo(nElement)==0)
            {
                return  i;
            }
            temp=(Node)temp.getNext();
            if(temp!=null)
            {
                listElement = (Comparable) (temp.getElement());
            }


        }
        return NOT_FOUND;
    }

    public  void sort()
    {
        mergeSort(0,length()-1);
    }
    private void mergeSort(int start,int end)
    {
        if(end-start<=1)
        {
            Comparable st = (Comparable)(getElementAt(start));
            Comparable en=(Comparable)(getElementAt(end));
            if(st.compareTo(en)>0)
            {
                T temp=((T)getElementAt(start));

                ((Node)getNodeElement(start)).setElement((T)getElementAt(end));
                ((Node)getNodeElement(end)).setElement((T)temp);

            }
            return;

        }
        int middle=(start+end)/2;
        mergeSort(start,middle);
        mergeSort(middle+1,end);
        merge(start,middle,end);


    }
    private void merge(int start,int middle,int end)
    {
        int n1=middle-start+1;
        int n2=end-middle;
        T[] L=(T[])new Object[n1];
        T[] R=(T[])new Object[n2];
        int j=0;
        for(int i=start;i<=middle;i++,j++)
        {
            L[j]=(T)getElementAt(i);
        }
        j=0;
        for(int i=middle+1;i<=end;i++,j++)
        {
            R[j]=((T)getElementAt(i));
        }
        j=0;
        int i=0;
        int k;
        Comparable left;
        Comparable right;
        for(k=start;(i<n1)&&(j<n2);k++)
        {
            left = (Comparable) L[i];
            right=(Comparable)R[j];
            if(left.compareTo(right)<=0)
            {
                getNodeElement(k).setElement(L[i]);

                i++;

            }
            else
            {
                getNodeElement(k).setElement(R[j]);
                j++;


            }
        }

        for(;i<n1;i++,k++)
        {
            getNodeElement(k).setElement(L[i]);


        }



        for(;j<n2;j++,k++)
        {
            getNodeElement(k).setElement(R[j]);


        }


        return;
    }
    public int appears(T n)
    {
        int count=0;
        Node temp=head;
        Comparable arrayElement=(Comparable)(temp.getElement());
        Comparable nElement=(Comparable)n;
        for(int i=0;i<size;i++)
        {

            if(arrayElement.compareTo(nElement)==0)
            {
                count++;
            }
            temp=(Node)temp.getNext();
            arrayElement=(Comparable)(temp.getElement());


        }
        return count;


    }
}
class  Node<T>
{

    private T element;
    private T next;
    private T prev;

    public Node(T element, T next, T prev) {
        this.element = element;
        this.next = next;
        this.prev = prev;
    }

    public Node(T element) {
        this.element = element;
        next=null;
        prev=null;
    }

    public void setNext(T next) {
        this.next = next;
    }

    public void setPrev(T prev) {
        this.prev = prev;
    }
    public void setNextPrev(T next,T prev)
    {
        this.next=next;
        this.prev=prev;
    }

    public T getNext() {
        return next;
    }

    public T getPrev() {
        return prev;
    }

    public T getElement() {
        return element;
    }

    public void setElement(T element) {
        this.element = element;
    }
}
