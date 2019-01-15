package myoperator_assignment3;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;


public class question6 {
 public static void main(String [] args)
    {
        Connection con = null;
        String path = "jdbc:mysql://localhost:3306/myoperator";
        try{
            Class.forName("com.mysql.cj.jdbc.Driver");
            con = DriverManager.getConnection(path,"root","root");
            
            PreparedStatement pst1 = con.prepareStatement("select hour(start_time),count(*) as total_calls from assignment3 group by hour(start_time);");
            //get the total number of calls
            ResultSet rst1 = pst1.executeQuery();
            float min = 1.0f;
            String min_hour = null;
            while(rst1.next())
            {
                String hour = rst1.getString(1);
                int total_calls = rst1.getInt(2);
                PreparedStatement pst2 = con.prepareStatement("select hour(start_time),count(*) as mycount from assignment3 where status_of_call='bridged' and hour(start_time)="+ hour+"");
            //gets the count of bridged call for that particular hour
                ResultSet rst2 = pst2.executeQuery();
                if(rst2.next())
                { 
                int bridged = rst2.getInt(2);
                
                float connect_rate = (float)bridged/(float)total_calls;
               // System.out.println(hour+"\t"+bridged+"\t"+total_calls+"\t"+connect_rate);
                    if(connect_rate<min)
                    {
                        min = connect_rate;
                        min_hour = hour;
                    }
                }
            }
            System.out.println("hour having lowest connect rate:"+min_hour+" with connect_rate:"+min);
        }
        catch(Exception e)
        {
            System.out.println("Alert!"+e);
        }
    }
}
