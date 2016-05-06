package com.company;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.StringTokenizer;

public class Main {
    static  int stages;
    static  double prob[]=new double[100];
    static  int cost[]=new int [100];
    static  int  budget;
    static  double dp[][]=new double[100][100];
    static  int result[][]=new int[100][100];




    static  void getInput()throws Exception
    {

        FileReader fileReader = new FileReader("input.txt");
        BufferedReader bufferedReader = new BufferedReader(fileReader);
        String temp1;

        temp1=bufferedReader.readLine();
        StringTokenizer st=new StringTokenizer(temp1," ");
        stages=Integer.parseInt(st.nextToken());
        budget=Integer.parseInt(st.nextToken());


        temp1=bufferedReader.readLine();
        temp1=bufferedReader.readLine();
         st=new StringTokenizer(temp1," ");
        for(int i=1;st.hasMoreTokens();i++)
        {
            prob[i]=Double.parseDouble(st.nextToken());
        }
        temp1=bufferedReader.readLine();
        temp1=bufferedReader.readLine();
        st=new StringTokenizer(temp1," ");
        for(int i=1;st.hasMoreTokens();i++)
        {
            cost[i]=Integer.parseInt(st.nextToken());
        }


        bufferedReader.close();
        fileReader.close();



    }
    static  double findProbability(int fstages,int fbudget)
    {

        for(int i=0;i<=fbudget;i++)
        {
            dp[0][i]=1;// if no budget
        }
        for(int i=1;i<=fstages;i++)
        {
            for(int j=0;j<=fbudget;j++)
            {
                if(j<cost[i])
                {
                    dp[i][j]=0;
                }
            }
        }


        for(int i = 1 ; i<=fstages;++i)
        {
            for (int j = 1; j<=fbudget ;j++)
            {
                //dp[i][c] = Double.min(dp[i - 1][c], dp[i - 1][c - cost[stages]] * (1 - prob[i]));
                for(int k=1;(k<=(int)(j/(cost[i])));k++)
                {


                    double temp1=dp[i-1][j-(k*cost[i])];
                    double temp2=1-Math.pow(1-prob[i],k);
                    temp1=temp1*temp2;

                    dp[i][j]=Double.max(dp[i][j],temp1);
                    if(dp[i][j]==temp1)
                    {
                        result[i][j]=k;
                    }

                }
            }
        }
        return  dp[fstages][fbudget];
    }


   static void print_result()
   {
       int temp[]=new int[stages];
       int i,j;
       for( i=budget,j=stages;i>0;j--)
       {
           temp[j-1]=result[j][i];
           i=i-cost[j]*result[j][i];

       }
       for(i=0;i<stages;i++)
       {
           System.out.print(temp[i]+"    ");
       }
       System.out.println("");
   }

    public static void main(String[] args) {


        try {
            getInput();
        } catch (Exception e) {
            e.printStackTrace();
        }

        double temp =findProbability(stages,budget);
        System.out.println(temp);

        if(temp!=0)
        {
            print_result();
        }


    }

}
