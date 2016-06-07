package helperPackage;

/**
 * Created by Partha on 5/11/2016 at 18 :24 (6:24 PM)
 */
public final class DisjointSet {
    private int parent[];

    private int vertexNumber;
    public static  final int NOT_FOUND=-9999;

    public DisjointSet(int vertexNumber)
    {
        this.vertexNumber=vertexNumber;
        parent=new int[vertexNumber];

        for(int i=0;i<vertexNumber;i++)
        {
            parent[i]=i;
        }
    }
    public int findSet(int u)
    {
        if((u<0)||(u>=vertexNumber))
        {
            return NOT_FOUND;
        }
        if(parent[u]==u)
        {
            return u;
        }
        parent[u]=findSet(parent[u]);
        return  parent[u];

    }
    public void union(int u,int v)
    {
        int at=findSet(u);
        if(at==NOT_FOUND)
        {
            return;
        }
        parent[at]=v;
        return;
    }
    public  int memberInThisUnion(int u)
    {
        if((u<0)||(u>=vertexNumber))
        {
            return NOT_FOUND;
        }
        int temp=findSet(u);
        for(int i=0;i<vertexNumber;i++)
        {
            findSet(i);
        }
        int count=0;
        for(int i=0;i<vertexNumber;i++)
        {
            if(parent[i]==temp)
            {
                count++;
            }
        }
        return  count;

    }
}