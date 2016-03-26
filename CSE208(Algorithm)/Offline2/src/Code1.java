import java.io.BufferedReader;
import java.io.FileReader;
import java.util.StringTokenizer;

/**
 * Created by Partha on 3/23/2016 at 09 :16 (9:16 AM)
 */
public class Code1 {
    final static int upperLeft=1;
    final static int upperRight=2;
    final static int lowerLeft=3;
    final static int lowerRight=4;
    static  int leftBoundary=0;
    static  int rightBoundary;
    static  int upBoundary=0;
    static  int downBoundary;






// Function to receive Output
    static int[][] getInput() throws  Exception
    {
        FileReader fileReader = new FileReader("input.txt");
        BufferedReader bufferedReader = new BufferedReader(fileReader);
        String temp1;

        temp1=bufferedReader.readLine();
        StringTokenizer stringTokenizer=new StringTokenizer(temp1," ");
        downBoundary= Integer.parseInt(stringTokenizer.nextToken());
        rightBoundary=Integer.parseInt(stringTokenizer.nextToken());
        rightBoundary--;
        downBoundary--;

        temp1=bufferedReader.readLine();
        int array[][]=new int[100][100];
        for(int i=0;i<=downBoundary;i++)
        {
            temp1=bufferedReader.readLine();
            stringTokenizer=new StringTokenizer(temp1," ");
            for(int j=0;j<=rightBoundary;j++)
            {
                array[i][j]= Integer.parseInt(stringTokenizer.nextToken());
            }
        }
        bufferedReader.close();
        fileReader.close();
        return array;
    }

    // Main function to find local minimum
    static  int findLocalMinima(int array[][],int leftBoundary,int rightBoundary,int upBoundary,int downBoundary)
    {
        int middleRow,middleColumn;
        middleRow=(upBoundary+downBoundary)/2;
        middleColumn=(leftBoundary+rightBoundary)/2;
        int minimum=Integer.MAX_VALUE;
        int minimumRow=middleRow,minimumColumn=middleColumn;
        for(int i=leftBoundary;i<=rightBoundary;i++)
        {
            if(minimum>array[middleRow][i])
            {
                minimum=array[middleRow][i];
                minimumRow=middleRow;
                minimumColumn=i;
            }
        }
        for(int i=upBoundary;i<=downBoundary;i++)
        {
            if(minimum>array[i][middleColumn])
            {
                minimum=array[i][middleColumn];
                minimumRow=i;
                minimumColumn=middleColumn;
            }
        }
        if(check(array,minimumRow,minimumColumn))
        {
            return  minimum;
        }
        else
        {
            if(direction(array,minimumRow,minimumColumn,middleRow,middleColumn)==upperLeft)
            {
                return  findLocalMinima(array,leftBoundary,middleColumn,upBoundary,middleRow);
            }
            else if(direction(array,minimumRow,minimumColumn,middleRow,middleColumn)==upperRight)
            {
                return  findLocalMinima(array,middleColumn,rightBoundary,upBoundary,middleRow);
            }
            else if(direction(array,minimumRow,minimumColumn,middleRow,middleColumn)==lowerLeft)
            {
                return  findLocalMinima(array,leftBoundary,middleColumn,middleRow,downBoundary);
            }
            else if(direction(array,minimumRow,minimumColumn,middleRow,middleColumn)==lowerRight)
            {
                return  findLocalMinima(array,middleColumn,rightBoundary,middleRow,downBoundary);
            }
            else
            {
                return -10000;
            }
        }
    }


    // used to find is global minimum  minimum
    /* Older Version
    static boolean check(int array[][],int row,int column)
    {
        try {
            if ((array[row][column - 1] > array[row][column]) && (array[row][column + 1] > array[row][column]) && (array[row - 1][column] > array[row][column]) && (array[row + 1][column] > array[row][column])) {
                return true;
            }
        }catch(Exception e)
        {

        }
        return  false;
    }*/
    //new version
    static boolean check(int array[][],int row,int column)// have to be edited it only search only less what about if equal
    {
        if(column!=leftBoundary)
        {
            if(array[row][column-1]<=array[row][column])
            {
                return  false;
            }
        }
        if(column!=rightBoundary)
        {
            if(array[row][column+1]<=array[row][column])
            {
                return  false;
            }
        }
        if(row!=upBoundary)
        {
            if(array[row-1][column]<=array[row][column])
            {
                return  false;
            }
        }
        if(row!=downBoundary)
        {
            if(array[row+1][column]<=array[row][column])
            {
                return  false;
            }
        }
        return  true;




    }



    //Used to find the location of minimum lower than global minimum
    static int direction(int array[][],int row,int column,int middleRow,int middleColumn)
    {
        if(row!=upBoundary) {

            if (array[row - 1][column] < array[row][column]) {
                if (column < middleColumn) {
                    return upperLeft;
                } else {
                    return upperRight;
                }
            }
        }
        if(row!=downBoundary) {
            if (array[row + 1][column] < array[row][column]) {
                if (column < middleColumn) {
                    return lowerLeft;
                } else {
                    return lowerRight;
                }
            }
        }
        if(column!=rightBoundary) {
            if (array[row][column + 1] < array[row][column]) {
                if (row < middleRow) {
                    return upperRight;
                } else {
                    return lowerRight;
                }
            }
        }
        if(column!=leftBoundary) {
            if (array[row][column - 1] < array[row][column]) {
                if (row < middleRow) {
                    return upperLeft;
                } else {
                    return lowerLeft;
                }
            }
        }
        return -1;
    }


//Driver function
    public static void main(String[] args) throws  Exception{



        int array[][]=getInput();
        System.out.println("Local minima : "+findLocalMinima(array, 0, rightBoundary, 0, downBoundary));

    }

}


// some idea

   // if boundary can't be minima
         //  then there  must be boundary check and you have to store  axis values in increasing order to
        //  find a non boundary minima


