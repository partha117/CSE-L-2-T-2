package com.company;


/**
 * Created by Partha on 5/20/2016 at 17 :57 (5:57 PM)
 */
public class GreedySolution {
      private int time[];
      private int processor;
      private int process;
      private int distr[][];
      private int sum[];
      private int to[];


    public GreedySolution(int [] time,int process,int processor)
    {
        this.time=new int[process];
        for(int i=0;i<process;i++)
        {
            this.time[i]=time[i];
        }
        this.process=process;
        this.processor=processor;
        distr=new int[processor][process];
        sum=new int[process];
        to=new int[process];
        sort();
    }

    private   void sort()
    {

        for(int i=process;i>=0;i--)
        {
            for(int j=0;j<process-1;j++)
            {

                if(time[j]<time[j+1])
                {
                    int temp;
                    temp=time[j];
                    time[j]=time[j+1];
                    time[j+1]=temp;

                }
            }
        }
        return;

    }
     private void func()
    {
        for(int i=0;i<process;i++)
        {
            int target=getMin(processor);
            distr[target][to[target]]=time[i];
            sum[target]=sum[target]+time[i];
            to[target]++;
        }
        return;
    }
     private int getMin(int n)
    {
        int min=Integer.MAX_VALUE;
        int tar=0 ;
        for(int i=0;i<n;i++)
        {
            if(min>sum[i])
            {
                min=sum[i];
                tar=i;

            }
        }
        return  tar;
    }
    private  int getMax()
    {
        int max=Integer.MIN_VALUE;

        for(int i=0;i<processor;i++)
        {
            if(max<sum[i])
            {
                max=sum[i];


            }
        }
        return  max;
    }

   int getSolution() {
        // write your code here

        func();
        return getMax();


    }
}

