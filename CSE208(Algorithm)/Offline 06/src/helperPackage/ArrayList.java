package helperPackage;



/**
 * Created by Partha on 5/11/2016 at 18 :20 (6:20 PM)
 */
public class ArrayList<T>{
    private  int maxSize;
    private  int currentSize;
    public static  final int  NOT_FOUND=-9999;


    private    T array[];

    public  ArrayList()
    {
        maxSize=2;
        currentSize=0;


        array=(T[])new Object[maxSize];
    }


    public boolean isEmpty()
    {
        if(currentSize==0)
        {
            return  true;
        }
        return  false;
    }
    private boolean isFull()
    {
        if(maxSize==currentSize)
        {
            return true;
        }
        return false;
    }
    public void add(T n)
    {
        if(!isFull())
        {
            array[currentSize]=n;

        }
        else
        {
            maxSize=maxSize*2;
            T temp[]=(T[])new Object[maxSize];
            for(int i=0;i<currentSize;i++)
            {
                temp[i]=array[i];
            }
            array=temp;
            array[currentSize]=n;

        }
        currentSize++;
        return;
    }
    public  void add(T n,int index)
    {
        if(!isFull())
        {
            if(index<currentSize)
            {
                for(int i=currentSize-1;i>=index;i--)
                {
                    array[i+1]=array[i];
                }
                array[index]=n;
            }
        }
        else
        {
            if(index<currentSize)
            {
                maxSize=maxSize*2;
                T temp[]=(T[])new Object[maxSize];
                for(int i=0;i<currentSize;i++)
                {
                    temp[i]=array[i];
                }
                array=temp;

                for(int i=currentSize-1;i>=index;i--)
                {
                    array[i+1]=array[i];
                }
                array[index]=n;

            }
        }
        currentSize++;
        return;
    }
    public T remove(int index)
    {
        if(index<currentSize)
        {
            T temp;
            temp=(T)array[index];
            for (int i = index + 1; i < currentSize; i++) {
                array[i - 1] = array[i];
            }
            currentSize--;

            if(currentSize<(maxSize/2))
            {
                maxSize=maxSize/2;
                T temp1[]=(T[])new Object[maxSize];
                for(int i=0;i<currentSize;i++)
                {
                    temp1[i]=array[i];
                }
                array=temp1;
            }
            return  temp;
        }
        return null;
    }
    public  int length()
    {
        return currentSize;
    }
    public T getElementAt(int index)
    {
        if(index<currentSize)
        {
            return array[index];
        }
        return  null;
    }

    public  int searchItem(T n)
    {
        Comparable arrayElement;
        Comparable nElement=(Comparable)n;
        for(int i=0;i<currentSize;i++)
        {
            arrayElement=(Comparable)array[i];
            if(arrayElement.compareTo(nElement)==0)
            {
                return  i;
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
            Comparable st = (Comparable) array[start];
            Comparable en=(Comparable)array[end];
            if(st.compareTo(en)>0)
            {
                T temp=array[start];
                array[start]=array[end];
                array[end]=temp;

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
            L[j]=array[i];
        }
        j=0;
        for(int i=middle+1;i<=end;i++,j++)
        {
            R[j]=array[i];
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
                array[k]=L[i];

                i++;

            }
            else
            {
                array[k]=R[j];
                j++;


            }
        }

        for(;i<n1;i++,k++)
        {
            array[k]=L[i];


        }



        for(;j<n2;j++,k++)
        {
            array[k]=R[j];


        }


        return;
    }
    public int appears(T n)
    {
        int count=0;

        Comparable arrayElement;
        Comparable nElement=(Comparable)n;
        for(int i=0;i<currentSize;i++)
        {
            arrayElement=(Comparable)array[i];
            if(arrayElement.compareTo(nElement)==0)
            {
                count++;
            }


        }
        return count;


    }





}