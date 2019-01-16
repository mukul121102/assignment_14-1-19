
package myoperator_assignment3;

//Calculate max number of simultaneous calls (calls running at same time) at any point in time.
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Arrays;


public class question7 {
    public static void main(String [] args)
    {
        Connection con = null;
        String path = "jdbc:mysql://localhost:3306/myoperator";
        try{
            Class.forName("com.mysql.cj.jdbc.Driver");
            con = DriverManager.getConnection(path,"root","root");
            PreparedStatement pst1 = con.prepareStatement("select start_time,end_time from assignment3");//selecting data from database to load into java memory
            ResultSet rst1 = pst1.executeQuery();
            String[] start_time = new String[48879]; // max number of rows in database is 48879. get the start times from database and load it into string array.  
            String[] end_time = new String[48879];
            int i=0,count=0;
            while(rst1.next())
            {
                start_time[i] = rst1.getString(1);//start time is in format DD/MM/YY HH:mm:ss
                start_time[i]=start_time[i].split(" ")[1];// get the start time in HH:mm:ss
                end_time[i] = rst1.getString(2);//end time is in format DD/MM/YY HH:mm:ss
                end_time[i] = end_time[i].split(" ")[1];// get the end time in HH:mm:ss
                i++;
            }
            //Sorting the data to compare between next start time and previous end time. 
                //Arrays.sort(start_time);
                //Arrays.sort(end_time);
                //using the logic , check for next row of data , if start time < end time then count++
                i=0;
                int j=0;
                int max = 0;
                for(i=1 ; i<end_time.length; i++)  
                {
                    if((start_time[i].compareTo(end_time[i-1]))<0)// comparing the start time with end time
                    {
                        count++;
                        max = Math.max(count, max);
                    }
                    else
                       count--;
                }
                System.out.println("Maximum number of sim. calls is :"+max);
        }
        catch(Exception e)// used to catch the exception. 
        {
            System.out.println("Alert!"+e);
        }
    }
}
