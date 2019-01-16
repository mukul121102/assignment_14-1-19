package myoperator_assignment3;

//Which hour has lowest connect rate (bridge / total calls).
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
            //get the total number of calls for each hour. 
            ResultSet rst1 = pst1.executeQuery();
            float min = 1.0f;// since bridge/total calls will be output in decimal. So, it is taken as float . 
            String min_hour = null;// variable created to get the hour with lowest connect rate. 
            while(rst1.next())// loop getting the total number of calls for each hour. 
            {
                String hour = rst1.getString(1);
                int total_calls = rst1.getInt(2);
                PreparedStatement pst2 = con.prepareStatement("select hour(start_time),count(*) as mycount from assignment3 where status_of_call='bridged' and hour(start_time)="+ hour+"");
            //gets the count of bridged call for that particular hour for which above while loop is working . 
                ResultSet rst2 = pst2.executeQuery();
                if(rst2.next())
                { 
                int bridged = rst2.getInt(2);// number of briged calls on that hour 
                float connect_rate = (float)bridged/(float)total_calls; // calculation of connect rate. 
               // System.out.println(hour+"\t"+bridged+"\t"+total_calls+"\t"+connect_rate);
                    if(connect_rate<min)// to check if the connect rate is minimum. 
                    {
                        min = connect_rate;// if yes, load that connect rate into minimum. 
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
