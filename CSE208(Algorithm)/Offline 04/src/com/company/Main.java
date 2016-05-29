package com.company;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Main {
    static int [] time;
    static  boolean []isTaken;
    static int[] resultArray;
    static int[] to;
    static  int numberOfProcessor;
    static  int numberOfProcess;

    static int [][]condition;
    static int [][]solution;
    static  int bestTiming;
    static  int maxValue;
    static  int minValue;


    static  void getInput()
    {
        File file=new File("input.txt");
        Scanner scn= null;
        try {
            scn = new Scanner(file);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        numberOfProcessor=scn.nextInt();
        numberOfProcess=scn.nextInt();
        time=new int[numberOfProcess];
        for(int i=0;i<numberOfProcess;i++)
        {
            time[i]=scn.nextInt();
        }
        condition=new int[numberOfProcessor][numberOfProcess];
        solution=new int[numberOfProcessor][numberOfProcess];
        isTaken=new boolean[numberOfProcess];
        resultArray=new int[numberOfProcess];
        to=new int[numberOfProcessor];


        for(int i=0;i<numberOfProcessor;i++)
        {
            for(int j=0;j<numberOfProcess;j++)
            {
                condition[i][j]=-1;
            }

        }

        return;
    }
    static int isComplete()
    {
        for(int i=0;i<numberOfProcess;i++)
        {
            if(!isTaken[i])
            {
                return i;
            }
        }
        return -1;
    }

    static boolean isOptimal(int processor,int process)
    {
        int sum=0;
        resultArray[processor]+=time[process];
       for(int i=0;i<numberOfProcess;i++)
        {
            if(!isTaken[i])
            {
                sum+=time[i];
            }
        }
        sum-=time[process];
        getMin_Max();
        int temp=minValue+(sum/numberOfProcessor);

        if((temp>bestTiming)||(maxValue>bestTiming)) {
            resultArray[processor]-=time[process];
           return false;
        }
        else
        {
            resultArray[processor]-=time[process];
           return  true;
       }
    }
    static void  getMin_Max()
    {
        int temp=Integer.MAX_VALUE;
        int max=Integer.MIN_VALUE;

        for(int i=0;i<numberOfProcessor;i++)
        {

            if((temp>resultArray[i]))
            {
                temp=resultArray[i];
            }
            if(max<resultArray[i])
            {
                max=resultArray[i];


            }

        }
        minValue=temp;
        maxValue=max;
        return  ;
    }

    static  void getCopy()
    {
        for(int i=0;i<numberOfProcessor;i++)
        {
            for(int j=0;j<numberOfProcess;j++)
            {
                solution[i][j]=condition[i][j];
            }

        }
    }

    static boolean optimalSolution()
    {
        int i=isComplete();
        if(i==-1)
        {
            getMin_Max();
            if(bestTiming>=maxValue)
            {
                bestTiming=maxValue;
                getCopy();
            }
            return false;
        }

            for(int j=0;(j<numberOfProcessor);j++)
            {


                if(isOptimal(j,i))
                {

                    condition[j][to[j]]=i;
                    to[j]++;
                    isTaken[i]=true;
                    resultArray[j]+=time[i];


                        if(optimalSolution())
                        {
                            return  true;
                        }
                        else
                        {
                            resultArray[j] -= time[i];
                            isTaken[i] = false;
                            to[j]--;
                            condition[j][to[j]] = -1;

                        }
                     }



        }
        return  false;
    }
    public static void main(String[] args) {

        getInput();

        GreedySolution greedySolution=new GreedySolution(time,numberOfProcess,numberOfProcessor);
        bestTiming=greedySolution.getSolution();
        optimalSolution();

        for(int i=0;i<numberOfProcessor;i++)
        {
            System.out.print("Processor "+(i+1)+" : ");
            int sum=0;
            for(int j=0;j<numberOfProcess;j++)
            {

                if(solution[i][j]!=-1)
                {
                    System.out.print(time[solution[i][j]]+"   ");
                    sum+=time[solution[i][j]];
                }
            }
            System.out.println(" = "+sum);
        }
    }
}
