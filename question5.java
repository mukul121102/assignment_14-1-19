
package myoperator_assignment3;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class question5 {
    public static void main(String [] args)
    {
        Connection con = null;
        String path = "jdbc:mysql://localhost:3306/myoperator";
        try{
            Class.forName("com.mysql.cj.jdbc.Driver");
            con = DriverManager.getConnection(path,"root","root");
            int max_calls = question4.doQuestion();
            int capacity = max_calls/10;
            System.out.println("Max capacity of each server is:"+capacity);
            PreparedStatement pst = con.prepareStatement("select hour(start_time), count(*)as calls from assignment3 group by hour(start_time)");
            ResultSet rst = pst.executeQuery();
            int i=1;
            while(rst.next())
            {
                String hour = rst.getString(1);
                int calls = rst.getInt(2);
                
                i=1;
                while(i<=10)
                {
                    capacity = 498;
                    capacity = capacity*i;
                    int min_server = i;
                    if(calls<=capacity)
                    {
                        System.out.println(hour+"\t"+calls+"\t"+min_server+"\t"+capacity);
                        break;
                    }
                    else
                        i++;
                }
            }
        }
        catch(Exception e)
        {
            System.out.println("Alert!"+e);
        }
    }
}
