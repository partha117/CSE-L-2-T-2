package helperPackage;

import helperPackage.Graph;
import helperPackage.LinkedList;

/**
 * Created by Partha on 5/23/2016 at 08 :00 (8:00 AM)
 */
public class TopologicalSort
{
    private Graph graph;
    private LinkedList<Integer> linkedList;
    private  int [] color;
    public  final  int Gray=1;
    public  final  int Black=2;
    public  final  int White=0;


    public TopologicalSort(Graph graph) {
        this.graph=graph;
        linkedList=new LinkedList<>();
        color=new int[graph.getVertexNumber()];

    }
    public  LinkedList sort()
    {
        for(int i=0;i<graph.getVertexNumber();i++)
        {
            if(color[i]==White)
            {
                depthFirstSearch(i);
            }
        }
        return linkedList;
    }
    private  void depthFirstSearch(int u)
    {
        color[u]=Gray;
        for(int i=0;i<graph.getAdjacency(u).length();i++)
        {
            int v=(int) graph.getAdjacency(u).getElementAt(i);
            if(color[v]==White)
            {
                depthFirstSearch(v);
            }
        }
        color[u]=Black;
        linkedList.addFirst(u);
        return;
    }

}
