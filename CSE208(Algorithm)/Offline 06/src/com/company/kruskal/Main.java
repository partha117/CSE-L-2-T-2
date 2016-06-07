package com.company.kruskal;

import helperPackage.ArrayList;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

/**
 * Created by Partha on 6/2/2016 at 12 :55 (12:55 PM)
 */
public class Main {

    static ArrayList<edgeWeight> graphweight=new ArrayList<>();
    static  int numberOfEdge;
    static  int numberOfVertex;
    static  void getInput()
    {
        File file=new File("inputKruskal.txt");

        Scanner scn= null;
        try {
            scn = new Scanner(file);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        numberOfVertex=scn.nextInt();
        numberOfEdge=scn.nextInt();
        for(int i=0;i<numberOfEdge;i++)
        {
            graphweight.add(new edgeWeight(scn.nextInt(),scn.nextInt(),scn.nextInt()));


        }

    }
    public static void main(String[] args) {
        getInput();
        Kruskal kruskal=new Kruskal(graphweight,numberOfVertex,numberOfEdge);
        kruskal.findMST();
        kruskal.getGraph().printGraph();
    }
}
