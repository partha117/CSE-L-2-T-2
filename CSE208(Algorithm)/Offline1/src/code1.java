/**
 * Created by Partha on 3/9/2016.
 */
import java.io.*;

import java.util.StringTokenizer;


class node
{
    String  alphabet=null;
    int frequency;
    node leftChild=null;
    node rightChild=null;
    boolean isLeaf=false;
    int level=0;


    public node(String alphabet, int frequency) {
        this.alphabet = alphabet;
        this.frequency = frequency;
        isLeaf=true;
    }

    public node(String alphabet,int frequency, node leftChild, node rightChild) {
        this.alphabet=alphabet;
        this.frequency = frequency;
        this.leftChild = leftChild;
        this.rightChild = rightChild;

    }

    public void setLevel(int level) {
        this.level = level;
    }

    public node(int frequency) {
        this.frequency = frequency;
    }
}








class heap
{

     node array[];
     private int size;
     private int rear;
    private int memory=100;

    public heap() {
        array=new node[memory];
        size=0;
        rear=1;
    }

    void minAdd(node a)
    {
        if(size==memory)
        {
            node temp[]=new node[memory*2];
            memory*=2;
            temp=array;
            array=temp;
        }
        array[rear]=a;
        rear++;
        size++;
        int i=rear-1;
        for(;(i>1)&&(array[i/2].frequency>array[i].frequency);)
        {
            node temp;
            temp=array[i/2];
            array[i/2]=array[i];
            array[i]=temp;
            i=i/2;

        }
    }

    node top()
    {
        if(size==0)
        {
            return new node(-1);
        }
        else
        {


            return array[1];

        }
    }
     node minPop()
     {
         if(size==0)
         {
             return new node(-1);
         }
         else
         {
             node temp;
             temp=array[1];
             array[1]=array[rear-1];
             rear--;
             size--;
             minHeapify(1);
             return temp;
         }
     }



    private  void minHeapify(int target)
    {
        int l=target*2;

        int r=(target*2)+1;
        int smallest=target;
        if((l<=size)&&(array[l].frequency<array[target].frequency))
        {
            smallest=l;
        }
        if((r<=size)&&(array[r].frequency<array[smallest].frequency))
        {
            smallest=r;
        }
        if(smallest!=target)
        {
            //System.out.println("smallest "+array[smallest].frequency);
            node temp=array[smallest];
            array[smallest]=array[target];
            array[target]=temp;
            minHeapify(smallest);
        }
    }

    int getSize()
    {
        return  size;
    }
    void buildmaxheap(node node1)
    {
        node arr[]=new node[1000];
        int size=0;
        int rear=2;
        int c=1;
        int last=1;

        if(node1!=null)
        {
            arr[1]=node1;
            arr[1].setLevel(1);
        }

        size++;
        int maxlevel= code1.height(arr[1]);

        for(int i=1;i<=maxlevel;i++)
        {
            for(int j=last;j<(last+(int)Math.pow(2,i-1));j++)
            {
                if((arr[c].leftChild!=null)&&(arr[c].rightChild!=null))
                {
                    arr[c].leftChild.setLevel(i+1);
                    arr[c].rightChild.setLevel(i+1);
                    if(arr[c].leftChild.frequency>arr[c].rightChild.frequency)
                    {


                        arr[rear]=arr[c].rightChild;
                        rear++;
                        arr[rear]=arr[c].leftChild;
                        rear++;
                    }
                    else
                    {
                        arr[rear]=arr[c].leftChild;
                        rear++;
                        arr[rear]=arr[c].rightChild;
                        rear++;
                    }
                    size+=2;
                }
                else {
                    if (arr[c].leftChild == null) {
                        node temp = new node(null, 0);
                        temp.setLevel(i+1);
                        arr[rear] = temp;
                        rear++;
                        size++;
                    } else {
                        arr[c].leftChild.setLevel(i+1);

                        arr[rear] = arr[c].leftChild;
                        rear++;
                        size++;
                    }
                    if (arr[c].rightChild == null) {
                        node temp = new node(null, 0);
                        temp.setLevel(i+1);
                        arr[rear] = temp;
                        rear++;
                        size++;
                    } else {

                        arr[c].rightChild.setLevel(i+1);
                        arr[rear] = arr[c].rightChild;
                        rear++;
                        size++;
                    }
                }
                c++;

            }
            last=rear-1;




        }
        this.size=size;
        this.array=arr;
        this.rear=rear;



    }

}












public class code1 {
    public static void main(String[] args) throws  Exception{
        String ar[]=new String[100];
        int ar1[]=new int[100];
        FileReader fileReader = new FileReader("input.txt");
        BufferedReader bufferedReader = new BufferedReader(fileReader);
        String temp1;
        int length;
        for(length=0;(temp1=bufferedReader.readLine())!=null;length++)
        {

            StringTokenizer st=new StringTokenizer(temp1," ");
            ar[length]=st.nextToken();

            ar1[length]= Integer.parseInt(st.nextToken());

        }
        bufferedReader.close();
        fileReader.close();
        heap myHeap=new heap();
        long startTime=System.nanoTime();
        for(int i=0;i<length;i++)
        {
            myHeap.minAdd(new node(ar[i], ar1[i]));
        }


        for(int i=0;myHeap.getSize()>1;i++)
        {
            node a=myHeap.minPop();
            node b=myHeap.minPop();

            node c;
            if(a.frequency>b.frequency)
            {
                c=new node(b.alphabet+a.alphabet,a.frequency+b.frequency,b,a);
            }
            else
            {
                c=new node(a.alphabet+b.alphabet,a.frequency+b.frequency,a,b);
            }

            myHeap.minAdd(c);
            

        }



        getCode(myHeap.top(), "");
        long endTime=System.nanoTime();
        FileWriter fileWriter = new FileWriter("output.txt",true);
        BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
        bufferedWriter.write("Running time : "+((double)(endTime-startTime))/ 1000000000+"s or "+(endTime-startTime)+" nanosecond");
        bufferedWriter.newLine();
        bufferedWriter.close();
        fileWriter.close();


        heap myHeap2=new heap();

        myHeap2.buildmaxheap(myHeap.top());

        draw(myHeap2);




    }














    static  void draw(heap heap)
    {
        node array[]=heap.array;
        int start=1;
        int max_level=height(array[1])+1;
        space((int)(Math.pow(2,max_level-1)-1));
        System.out.print(array[1].alphabet+"("+array[1].frequency+")");
        for(int i=1;i<=max_level;i++)
        {
            System.out.println("");
            int j;
            for( j=start;j<=(start+Math.pow(2,i-1)-1);j++)
            {
                space(frontspace(max_level,array[j]));
                if(array[j].level!=i)
                {
                    start=j;
                    break;
                }
                if(array[j].leftChild!=null)
                {
                    System.out.print(array[j].leftChild.alphabet+"("+array[j].leftChild.frequency+")");
                }
                space(middlespace(max_level,array[j]));
                if(array[j].rightChild!=null)
                {
                    System.out.print(array[j].rightChild.alphabet+"("+array[j].rightChild.frequency+")");
                }
            }
            start=j;
        }

    }
    static void  space(int n)
    {
        for(int i=0;i<n;i++)
        {
            System.out.print(" ");
        }
    }
    static  int frontspace(int max_level,node node)
    {

        int a=max_level-node.level-1;
        a=(int)Math.pow(2,a);
        a--;
        return  a;

    }
    static  int middlespace(int max_level,node node)
    {
        int a=node.level;
        a=max_level-a-2;
        a=(int)Math.pow(2,a);
        a--;
        return  a;

    }
    static void getCode(node node,String code) throws IOException {
        if(node==null)
        {
            return;
        }
        else
        {

            getCode(node.leftChild, code + "0");

            if(node.isLeaf==true)
            {
                FileWriter fileWriter = new FileWriter("output.txt",true);
                BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
                System.out.println(node.alphabet + "    " + code);
                bufferedWriter.write(node.alphabet+"   "+code);
                bufferedWriter.newLine();
                bufferedWriter.close();
                fileWriter.close();
            }

            getCode(node.rightChild, code + "1");



        }
    }


    static int height(node node)
    {
        if (node==null)
            return 0;
        else
        {
        /* compute the height of each subtree*/
            int lheight = height(node.leftChild);
            int rheight = height(node.rightChild);

        /* use the larger one*/
            if (lheight > rheight)
                return(lheight+1);
            else return(rheight+1);
        }
    }


}

