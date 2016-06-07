package com.company.dijkstra;


import helperPackage.Graph;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

/**
 * Created by Partha on 6/2/2016 at 12 :54 (12:54 PM)
 */
public class Main {
    static  int numberOfVertex;
    static  int numberOfEdge;
    static Graph<Integer> graph;
    static  int source;


    static  void getInput()
    {
        File file=new File("inputDijkstra.txt");

        Scanner scn= null;
        try {
            scn = new Scanner(file);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        numberOfVertex=scn.nextInt();
        numberOfEdge=scn.nextInt();
        graph=new Graph<>(numberOfVertex,true,true);
        for(int i=0;i<numberOfEdge;i++)
        {

            graph.addEdge(scn.nextInt(),scn.nextInt(),scn.nextInt());


        }
        source=scn.nextInt();

    }
    public static void main(String[] args) {

        getInput();
        Dijkstra dijkstra=new Dijkstra(graph);
        dijkstra.findPath(source);
        dijkstra.getPath().printGraph();

    }
}
