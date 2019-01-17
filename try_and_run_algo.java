
package myoperator_assignment3;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

public class try_and_run_algo {
    
    public static void main(String[] args)
    {
        try{
        FileInputStream input = new FileInputStream("C:/Users/Mukul-Gupta/Documents/myoperator_assignment/3Aug.csv");//reading the whole
            BufferedReader reader = new BufferedReader(new InputStreamReader(input));//reading the file line by line
            String row = "";
            int i=0;
            String[][] content = new String[58578][2]; // create a 2D array with start_date in first colum and end_date in second column
            while((row = reader.readLine())!=null)
            {
                String start_date = row.split(",")[0];// getting the start time by splitting on ','
                String end_date = row.split(",")[1];// getting the end time by splitting on ','
                    content[i][0]= start_date;//ith row and 0th column
                    content[i][1] = end_date;  // ith row and 0th column 
                i++;
            }
            int csv_size = i;//number of rows imported or the size of csv file. 
            SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy HH:mm");
            Date[] start_time = new Date[i];
            Date[] end_time = new Date[i];
            HashMap<Date, List<Date>> calls = new HashMap<Date, List<Date>>();
            ArrayList<Date> running_calls = new ArrayList<Date>();
            int max = 0;
            for(int j=0; j<csv_size; j++)
            {
                try{ // used because unable to parse 0000-00-0000 00:00:00
                 start_time[j] = formatter.parse(content[j][0]);// converting string to date and getting the start_datetime at 0th column.
                 end_time[j] = formatter.parse(content[j][1]);// converting string to date and getting the end_datetime at 1st column.
                }
                catch(Exception e)
                {
                    continue;
                }
                 calls.put(start_time[j], null);// put every start time as a key in hash map. 
                 running_calls.add(end_time[j]);// put end time into the array list running calls. 
                 ArrayList<Date> ended_calls = new ArrayList<>();
                 for(i=0;i<running_calls.size(); i++)
                 {
                     if(running_calls.get(i).after(start_time[j]))// end time > start time means the call is running. 
                     {
                         calls.put(start_time[j],running_calls);// the put that end to corresponding start time. 
                     }
                     else // call has ended
                         ended_calls.add(running_calls.get(i));// if the call has ended i.e. start time < end time. 
                 }
                 for(i=0; i<ended_calls.size();i++)
                 {
                     running_calls.remove(ended_calls.get(i)); // remove those calls from running calls which has ended.
                 }
                 try{ // used try because else it was throw null pointer exception
                  if(calls.get(start_time[j]).size() > max)
                      max = calls.get(start_time[j]).size() ;// get the max size of the list of ending calls for each starting call. 
                 }
                 catch(Exception e)
                 {
                     continue;
                 }   
            }
            System.out.println("max size is:"+max);
           
        }       
        catch(Exception e)
        {
            e.printStackTrace();
        }
    }
    
    
}
