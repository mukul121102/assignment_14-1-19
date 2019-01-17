
package myoperator_assignment3;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.*;

public class try_and_run_algo {
    
    public static void main(String[] args)
    {
        try{
        FileInputStream input = new FileInputStream("C:/Users/Mukul-Gupta/Documents/myoperator_assignment/3Aug.csv");//reading the whole
            BufferedReader reader = new BufferedReader(new InputStreamReader(input));//reading the file line by line
            String row = "";
            int i=0;
            String[] start_time = new String[58578]; // max number of rows in database is 48879. get the start times from database and load it into string array.  
            String[] end_time = new String[58578];
            while((row = reader.readLine())!=null)
            {
                String start_date = row.split(",")[0];// getting the start time by splitting on ','
                String end_date = row.split(",")[1];// getting the end time by splitting on ','
                start_time[i] = start_date.split(" ")[1];
                end_time[i] = end_date.split(" ")[1];
                i++;
            }
            //logic is to merge the two arrays and identify if they are start time and end time. if they are start time, then increment the counter and if at end decrement the counter. Store the max counter
            int n = start_time.length, m = end_time.length,k=0,current_overlapping=0, max_overlapping=0;
            String merged[] = new String [n+m];
            
            //System.out.println(merged[0].charAt(5)=='s');
            for(i= 0; i<n; i++)
            {
                merged[k++] = start_time[i]+"s";
            }
            for(int j=0; j<m; j++)
            {
                merged[k++] = end_time[j]+"e";
            }
            //creating a merged array with start time and end time in sorted order.
            Arrays.sort(merged);
            
            for(k=0; k<merged.length; k++)
            {
                int leng= merged[k].length();
                if(merged[k].charAt(leng-1)=='s')
                {
                    current_overlapping++;
                    max_overlapping = Math.max(current_overlapping, max_overlapping);
                }
                else
                    current_overlapping--;
            }
            System.out.println("Maximum number of overlapping intervals are :"+max_overlapping);
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
    }
    
    
}
