
package myoperator_assignment3;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;


public class question7 {
    public static void main(String [] args)
    {
        Connection con = null;
        String path = "jdbc:mysql://localhost:3306/myoperator";
        try{
            Class.forName("com.mysql.cj.jdbc.Driver");
            con = DriverManager.getConnection(path,"root","root");
            PreparedStatement pst1 = con.prepareStatement("select start_time,end_time from assignment3");
            ResultSet rst1 = pst1.executeQuery();
            String[] start_time = new String[48879];
            int i=0;
            while(rst1.next())
            {
                start_time[i] = rst1.getString(1);
                
                i++;
            }
            System.out.println(start_time[i-1]);
        }
        catch(Exception e)
        {
            System.out.println("Alert!"+e);
        }
    }
}
