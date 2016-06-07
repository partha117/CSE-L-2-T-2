package com.company.kruskal;

import helperPackage.ArrayList;
import helperPackage.DisjointSet;
import helperPackage.Graph;



/**
 * Created by Partha on 5/11/2016 at 23 :55 (11:55 PM)
 */
public class Kruskal {
    private int numberOfEdge;
    private   int numberOfVertex;
    private ArrayList<edgeWeight> graphweight=new ArrayList<>();
    private DisjointSet set;
    private Graph<edgeWeight> graph;

    public Kruskal(ArrayList<edgeWeight> graphweight, int numberOfVertex, int numberOfEdge) {
        this.graphweight = graphweight;
        this.numberOfVertex = numberOfVertex;
        this.numberOfEdge = numberOfEdge;
        graph=new Graph<>(numberOfVertex,false,true);
        set=new DisjointSet(numberOfVertex);
    }

     public void findMST()
    {
        graphweight.sort();
        int a,b;
        for(int i=0;i<graphweight.length();i++)
        {
            a=set.findSet(graphweight.getElementAt(i).u);
            b=set.findSet(graphweight.getElementAt(i).v);
            if(a!=b)
            {
                set.union(a,b);
                graph.addEdge(graphweight.getElementAt(i).u,graphweight.getElementAt(i).v,graphweight.getElementAt(i));
            }
        }


    }

    public Graph<edgeWeight> getGraph() {
        return graph;
    }
}
