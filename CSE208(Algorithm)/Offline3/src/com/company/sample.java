package com.company;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.StringTokenizer;

/**
 * Created by Partha on 5/4/2016 at 19 :46 (7:46 PM)
 */
public class sample {

    public static double dp[][];
    public static double r[];
    public static int c[];
    public static int B;
    public static int Red[][];

    public static double Dp(int n, int b)
    {
        for(int i=0;i<=b;i++) dp[0][i] = 1;
        for (int i=1;i<=n;i++)
        {
            for(int j=0;j<=b;j++)
            {
                if(b<c[i]) dp[i][j] = 0;
            }

        }

        for(int i=1;i<=n;i++)
        {
            for(int j=1;j<=b;j++)
            {
                double max = -99999.999;
                int max_k = 0;
                for(int k = 1;k<j/c[i];k++)
                {
                    double temp = dp[i-1][b-k*c[i]]*prob(i,k);
                    if(max < temp) {
                        max = temp;
                        max_k = k;

                    }

                }
                dp[i][j] = max;
                Red[i][j] = max_k;
            }
        }
        return dp[n][b];
    }


    public static double prob(int n, int i)
    {
        double p,temp,d=1.0;
        temp = 1-r[n];
        for(int j = 1;j<=i;j++) {
            d = d*temp;
        }
        p = 1 - d;
        return p;

    }

    public static void main(String[] args) throws Exception {

        dp = new double[1000][1000];
        r = new double[1000];
        c = new int[1000];
        Red = new int[1000][1000];

        FileReader fr = new FileReader("./src/inputs.txt");
        BufferedReader br=new BufferedReader(fr);


        String s = br.readLine();
        int n = Integer.parseInt(s);

        Main main = new Main();


        s = br.readLine();
        StringTokenizer stk = new StringTokenizer(s," ");
        r[0]  = -1.0;
        for(int i =1;i<=n;i++)
        {
            if(stk.hasMoreTokens()) r[i] = Double.parseDouble(stk.nextToken());
        }

        s = br.readLine();
        stk = new StringTokenizer(s," ");
        c[0]=-1;
        for(int i =1;i<=n;i++)
        {
            if(stk.hasMoreTokens()) c[i] = Integer.parseInt(stk.nextToken());
        }

        s = br.readLine();
        B = Integer.parseInt(s);

        System.out.println(Dp(n,B));
        for(int i=0;i<=n;i++)
        {
            for(int j=0;j<=B;j++)
            {
                //System.out.print(Red[i][j]+" ");
                System.out.printf("%3d",Red[i][j]);
            }
            System.out.println();
        }

       /* for(int i = 1;i<=n;i++)
        {
            System.out.print(Red[i][B]+" ");
        }*/



        br.close();
        fr.close();
    }
}