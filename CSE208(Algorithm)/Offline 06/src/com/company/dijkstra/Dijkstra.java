package com.company.dijkstra;

import helperPackage.Graph;

import helperPackage.PriorityQueue;




/**
 * Created by Partha on 5/23/2016 at 10 :23 (10:23 AM)
 */
public class Dijkstra
{
    private Graph graph;
    private int[] distance;
    public static final int INFINITY = Integer.MAX_VALUE;
    private PriorityQueue<Vertex> priorityQueue;
    private int [] parent;
    private  Graph path;

    public Dijkstra(Graph graph) {

        this.graph = graph;
        priorityQueue=new PriorityQueue<>();
        parent=new int[graph.getVertexNumber()];
        distance=new int[graph.getVertexNumber()];
        path=new Graph(graph.getVertexNumber(), graph.isDirected(),graph.isWeighted());

    }


    public  void findPath(int s)
    {
        distance[s]=0;
        Initialize(s);
        for(;priorityQueue.getSize()!=0;)
        {
            Vertex u=priorityQueue.minPop();
            for(int i=0;i<graph.getAdjacency(u.vertexNumber).length();i++)
            {
                int v=(int)graph.getAdjacency(u.vertexNumber).getElementAt(i);
                relax(u.vertexNumber,v);
            }


        }


    }
    private  void  Initialize(int s)
    {
        for(int i=0;i<graph.getVertexNumber();i++)
        {
            parent[i]=-1;
            if(i==s)
            {
                priorityQueue.minAdd(new Vertex(i,0));

                distance[i]=0;

            }
            else {
                priorityQueue.minAdd(new Vertex(i));
                distance[i]=Integer.MAX_VALUE;
            }


        }
    }
    private void relax(int u, int v) {
        if(distance[u]!=INFINITY) {

            if (distance[v] > (distance[u] + (Integer) graph.weightof(u, v))) {
                distance[v] = (distance[u] + (Integer) graph.weightof(u, v));
                if (parent[v] != -1) {
                    path.removeEdge(parent[v], v);
                }
                path.addEdge(u, v, graph.weightof(u, v));
                parent[v] = u;
                updateKey(v);

            }
        }


    }
    public  Graph getPath()
    {
        return  path;
    }
    private  void updateKey(int v)
    {

        Object[] temp1=priorityQueue.getPriorityQueue();
        for(int j=1;j<=priorityQueue.getSize();j++)
        {
            Vertex temp=(Vertex)temp1[j];
            if(temp.vertexNumber==v)
            {
                temp.setKey(distance[v]);
                priorityQueue.reConstructQueue(j);
                break;
            }
        }
    }


}