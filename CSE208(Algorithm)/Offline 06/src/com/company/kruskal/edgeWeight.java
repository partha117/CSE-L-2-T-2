package com.company.kruskal;

/**
 * Created by Partha on 5/11/2016 at 23 :57 (11:57 PM)
 */
public class edgeWeight implements Comparable{
    int u;
    int v;
    int weight;

    public edgeWeight(int u, int v, int weight) {
        this.u = u;
        this.v = v;
        this.weight = weight;
    }

    @Override
    public int compareTo(Object o) {

        if(this.weight==((edgeWeight) o).weight)
        {
            return 0;
        }
        else if(this.weight<((edgeWeight) o).weight)
        {
            return -1;
        }
        return 1;
    }
}
