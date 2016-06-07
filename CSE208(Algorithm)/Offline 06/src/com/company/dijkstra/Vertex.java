package com.company.dijkstra;


public class Vertex implements Comparable {

    int vertexNumber;
    int key=Integer.MAX_VALUE;
    int parent;

    public Vertex(int vertexNumber) {
        this.vertexNumber = vertexNumber;
    }

    public Vertex(int vertexNumber, int key) {
        this.vertexNumber = vertexNumber;
        this.key = key;
    }

    public void setKey(int key) {
        this.key = key;
    }

    public void setParent(int parent) {
        this.parent = parent;
    }

    @Override
    public int compareTo(Object o) {
        if(this.key==((Vertex)o).key)
        {
            return 0;
        }
        else if(this.key>((Vertex)o).key)
        {
            return 1;
        }
        return -1;
    }
}

