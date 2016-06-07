package helperPackage;

/**
 * Created by Partha on 5/11/2016 at 21 :55 (9:55 PM)
 */
final public class Graph<T> {

    private  LinkedList<LinkedList<Integer>> adjacencyList=new LinkedList<>();
    private  LinkedList<LinkedList<T>> weightList=new LinkedList<>();
    private int vertexNumber;
    private  boolean directed;
    private boolean weighted;
    public  static  final  int NOT_FOUND=-9999;


    public Graph(int vertexNumber,boolean directed,boolean weighted)
    {
        this.vertexNumber=vertexNumber;
        for(int i=0;i<vertexNumber;i++)
        {
            adjacencyList.add(new LinkedList<>());
            if(weighted)
            {
                weightList.add(new LinkedList<T>());
            }
        }
        this.directed=directed;
        this.weighted=weighted;


    }

    public int getVertexNumber() {
        return vertexNumber;
    }

    public boolean isDirected() {
        return directed;
    }

    public boolean isWeighted() {
        return weighted;
    }

    /*
      * Applicable for weighted graph
     */

    public  void addEdge(int u,int v,T weight)
    {
        adjacencyList.getElementAt(u).add(v);
        if(weighted) {
            weightList.getElementAt(u).add(weight);
        }
        if(!directed)
        {
            adjacencyList.getElementAt(v).add(u);
            if(weighted) {
                weightList.getElementAt(v).add(weight);
            }
        }

    }
    /*
      * Applicable for unWeighted graph
     */
    public void addEdge(int u,int v)
    {
        if(weighted)
        {
            return;
        }
        adjacencyList.getElementAt(u).add(v);
        if(!directed)
        {
            adjacencyList.getElementAt(v).add(u);
        }
    }
    public LinkedList<Integer> getAdjacency(int u)
    {
        return adjacencyList.getElementAt(u);
    }
    public void removeEdge(int u,int v)
    {
        int temp=adjacencyList.getElementAt(u).searchItem(v);
        adjacencyList.getElementAt(u).remove(temp);
        if(weighted)
        {
            weightList.getElementAt(u).remove(temp);
        }
        if(!directed)
        {
            int temp1=adjacencyList.getElementAt(u).searchItem(u);
            adjacencyList.getElementAt(v).remove(temp1);
            if(weighted)
            {
                weightList.getElementAt(v).remove(temp1);
            }
        }
    }
    public  boolean isEdge(int u,int v)
    {
        int a=adjacencyList.getElementAt(u).searchItem(v);
        if(a==LinkedList.NOT_FOUND)
        {
            return false;
        }
        return true;
    }
    /*
      * Applicable for  directed graph
     */
    public  int outDegree(int u )
    {
        if(!directed)
        {
            return NOT_FOUND;
        }
        return adjacencyList.getElementAt(u).length();
    }
    /*
      * Applicable for  directed graph
     */
    public  int inDegree(int u)
    {
        if(!directed)
        {
            return NOT_FOUND;
        }
        int count=0;
        for(int i=0;i<vertexNumber;i++)
        {
            count += adjacencyList.getElementAt(i).appears(u);
        }
        return count;
    }
    /*
      * Applicable for  undirected graph
     */
    public  int Degree(int u)
    {
        if(directed)
        {
            return NOT_FOUND;
        }
        return adjacencyList.getElementAt(u).length();
    }
    public T weightof(int u,int v)
    {
        if(!weighted)
        {
            return null;
        }
        int at=adjacencyList.getElementAt(u).searchItem(v);
        if(at==LinkedList.NOT_FOUND)
        {
            return  null;
        }
        return weightList.getElementAt(u).getElementAt(at);
    }
    public void printGraph()
    {
        for(int i=0;i<vertexNumber;i++)
        {
            System.out.print(i+" ->  ");
            if(adjacencyList.getElementAt(i).length()!=0)
            {
                for(int j=0;j<adjacencyList.getElementAt(i).length();j++)
                {
                    System.out.print(adjacencyList.getElementAt(i).getElementAt(j)+"   ");
                }

            }
            System.out.println("");
        }
    }
    public void transpose()
    {
        if(directed)
        {
            LinkedList<LinkedList<Integer>>  temp=new LinkedList<>();
            LinkedList<LinkedList<T>>  weightTemp=new LinkedList<>();
            for(int i=0;i<vertexNumber;i++)
            {
                temp.add(new LinkedList<>());
                weightTemp.add(new LinkedList<T>());
            }
            for(int i=0;i<vertexNumber;i++)
            {
                for(int j=0;j<adjacencyList.getElementAt(i).length();j++)
                {
                    temp.getElementAt(adjacencyList.getElementAt(i).getElementAt(j)).add(i);
                    if(weighted)
                    {
                        weightTemp.getElementAt(adjacencyList.getElementAt(i).getElementAt(j)).add(weightList.getElementAt(i).getElementAt(j));
                    }
                }
            }
            adjacencyList=temp;
            weightList=weightTemp;
        }
    }

}