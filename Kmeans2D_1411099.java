import java.util.*;
import java.lang.*;
import java.io.*;

class Kmeans2D_1411099
{
public static void main(String args[]) 
    {
    Scanner sc=new Scanner(System.in);
    System.out.println("Enter number of points in the data");
    int p=sc.nextInt();
    double arr[][]=new double[p][2];
    System.out.println("Enter numbers in the data");
    for(int q=0;q<p;q++)
        {
            arr[q][0]=sc.nextDouble();
            arr[q][1]=sc.nextDouble();
        }
    int i,  n = 0;
    double mean1x,mean1y, mean2x,mean2y, ax,ay,bx, by;
    boolean flag;
    double sum1x, sum2x;
    double sum1y, sum2y;
    ax = arr[0][0];
    bx = arr[1][0];
    ay = arr[0][1];
    by = arr[1][1];
    
    mean1x = ax;
    mean2x = by;
    mean1y = ax;
    mean2y = by;
    
    double cluster1[][] = new double[arr.length][2];
    double cluster2[][] = new double[arr.length][2];
    do {
        sum1x = 0;
        sum2x = 0;
        sum1y = 0;
        sum2y = 0;
        
        cluster1 = new double[arr.length][2];
        cluster2 = new double[arr.length][2];
        n++;
        int k = 0, j = 0;
        for (i = 0; i < arr.length; i++) 
            {
            if (Math.abs(distance(arr[i][0],arr[i][1] ,mean1x,mean1y)) <= Math.abs(distance(arr[i][0],arr[i][1] ,mean2x,mean2y))) 
                {
                cluster1[k][0] = arr[i][0];
                cluster1[k][1] = arr[i][1];
                k++;
                }
            else 
                {
                cluster2[j][0] = arr[i][0];
                cluster2[j][1] = arr[i][1];
                j++;
                }
            }
        System.out.println();
        for (i = 0; i < k; i++) 
            {
            sum1x = sum1x + cluster1[i][0];
            sum1y = sum1y + cluster1[i][1];
            }
        for (i = 0; i < j; i++) 
            {
            sum2x = sum2x + cluster2[i][0];
            sum2y = sum2y + cluster2[i][1];
            }
            
        System.out.println("mean1x=" + mean1x + "   mean1y=" + mean1y);
        System.out.println("mean2x=" + mean2x + "   mean2y=" + mean2y);
    
        ax = mean1x;
        bx = mean2x;
        ay = mean1y;
        by = mean2y;
        mean1x = Math.round(sum1x / k);
        mean2x = Math.round(sum2x / j);
        mean1y = Math.round(sum1y / k);
        mean2y = Math.round(sum2y / j);
        flag = !(mean1x == ax && mean2x == bx && mean1y == ay && mean2y == by);
        
        System.out.print("After iteration " + n + " , Cluster 1 :\n");
        for (i = 0; i < cluster1.length; i++) 
            {
            System.out.print("x: "+cluster1[i][0]);
            System.out.print("y: "+cluster1[i][1] + "\t");
            }
        System.out.print("\n");
        System.out.print("After iteration " + n + " , Cluster 2 :\n");
        for (i = 0; i < cluster2.length; i++) 
            {
            System.out.print("x: "+cluster2[i][0]);
            System.out.print("y: "+cluster2[i][1] + "\t");
            }
        System.out.print("\n");    
        }while (flag);
    
    
    System.out.println();
    System.out.println("Final mean1x=" + mean1x + "   Final mean1y=" + mean1y);
    System.out.println("Final mean2x=" + mean2x + "   Final mean2y=" + mean2y);
    System.out.print("Final cluster 1 :\n");
    for (i = 0; i < cluster1.length; i++) 
        {
        System.out.print("x: "+cluster1[i][0]);
        System.out.print("y: "+cluster1[i][1] + "\t");
        }
    System.out.println();
    System.out.print("Final cluster 2 :\n");
    for (i = 0; i < cluster2.length; i++) 
        {
        System.out.print("x: "+cluster2[i][0]);
        System.out.print("y: "+cluster2[i][1] + "\t");
        }
    }
    
    
    static double distance(double ax,double ay,double bx,double by)
        {
            return Math.sqrt((Math.pow(ax-bx,2)+Math.pow(ay-by,2)));
        }
}
