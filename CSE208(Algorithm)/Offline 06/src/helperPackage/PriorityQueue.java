package helperPackage;

/**
 * Created by Partha on 5/12/2016 at 20 :00 (8:00 PM)
 */
public final class  PriorityQueue <T> {


    private T[] array;
    private int size;
    private int rear;
    private int memory=100;
    private boolean ismax=false;
    public  final int NOT_FOUND=-9999;

    public PriorityQueue() {
        array=(T[])new Object[memory];
        size=0;
        rear=1;
    }

    public void minAdd(T a)
    {
        if(size==memory)
        {
            copy();
        }
        if((ismax)&&(size!=0) ){
            minHeapify(1);
            ismax=false;
        }
        array[rear]=a;
        rear++;
        size++;
        int i=rear-1;
        Comparable one,two;
        one=(Comparable)array[i/2];
        two=(Comparable)array[i];
        for(;(i>1)&&(one.compareTo(two)>0);)
        {
            T temp;
            temp=array[i/2];
            array[i/2]=array[i];
            array[i]=temp;
            i=i/2;

        }
    }
    private void copy()
    {
        T []temp=(T[])new Object[memory*2];
        memory*=2;
        for(int j=0;j<rear;j++)
        {
            temp[j]=array[j];

        }
        array=temp;
    }
    public void maxAdd(T a)
    {
        if(size==memory)
        {
            copy();
        }
        if((!ismax)&&(size!=0)) {

            maxHeapify(1);
            ismax=true;
        }

        array[rear]=a;
        rear++;
        size++;
        int i=rear-1;
        Comparable one,two;
        one=(Comparable)array[i/2];
        two=(Comparable)array[i];
        for(;(i>1)&&(one.compareTo(two)<0);)
        {
            T temp;
            temp=array[i/2];
            array[i/2]=array[i];
            array[i]=temp;
            i=i/2;

        }
    }

    T top()
    {
        if(size==0)
        {
            return null;
        }
        else
        {


            return array[1];

        }
    }
    public T minPop()
    {
        if(size==0)
        {
            return null;
        }
        else
        {
            if(ismax) {
                minHeapify(1);
                ismax=false;
            }
            T temp;
            temp=array[1];
            array[1]=array[rear-1];
            rear--;
            size--;
            minHeapify(1);
            return temp;
        }
    }
    public T maxPop()
    {
        if(size==0)
        {
            return null;
        }
        else
        {
            if(!ismax) {
                maxHeapify(1);
                ismax=true;
            }
            T temp;
            temp=array[1];
            array[1]=array[rear-1];
            rear--;
            size--;
            maxHeapify(1);
            return temp;
        }
    }
    public  void reConstructQueue(int j)
    {


        for(int i=j;i>=1;i--)
        {
            minHeapify(i);
        }
    }

    public  void minHeapify(int target)
    {
        if(target==0)
        {
            return;
        }
        int l=target*2;

        int r=(target*2)+1;
        int smallest=target;
        Comparable L,R,Target,Smallest;
        L=(Comparable)array[l];
        Target=(Comparable)array[target];
        Smallest=(Comparable)array[smallest];
        R=(Comparable)array[r];
        if((l<=size)&&(L.compareTo(Target)<0))
        {
            smallest=l;
            Smallest=(Comparable)array[smallest];
        }
        if((r<=size)&&(R.compareTo(Smallest)<0))
        {
            smallest=r;
        }
        if(smallest!=target)
        {
            //System.out.println("smallest "+array[smallest].frequency);
            T temp=array[smallest];
            array[smallest]=array[target];
            array[target]=temp;
            minHeapify(smallest);
        }
    }

    public int getSize()
    {
        return  size;
    }
    public T[] getPriorityQueue()
    {

        return (T[])array;
    }
    public void maxHeapify(int target)
    {
        int l=target*2;

        int r=(target*2)+1;
        int largest=target;
        Comparable L,R,Target,Largest;
        L=(Comparable)array[l];
        Target=(Comparable)array[target];
        Largest=(Comparable)array[largest];
        R=(Comparable)array[r];
        if((l<=size)&&(L.compareTo(Target)>0))
        {
            largest=l;
            Largest=(Comparable)array[largest];
        }
        if((r<=size)&&(R.compareTo(Largest)>0))
        {
            largest=r;
        }
        if(largest!=target)
        {

            T temp=array[largest];
            array[l]=array[target];
            array[target]=temp;
            minHeapify(largest);
        }
    }

}


