
package myoperator_assignment3;


import java.sql.*;

public class question1 
{
    
    public static void main(String[] args)
    {
        Connection con = null;
        String path = "jdbc:mysql://localhost:3306/myoperator";
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            con = DriverManager.getConnection(path,"root","root");
            PreparedStatement pst1 = con.prepareStatement("select * from assignment3 limit 10");
            ResultSet rst1 = pst1.executeQuery();
            while(rst1.next())
            {
                int id = rst1.getInt("id");
                String start_time = rst1.getTimestamp(1).toString();
            }
        }
        catch (Exception e)
        {
            System.out.println("Alert!"+e);
        }
        try{
            //question 1
            PreparedStatement pst2 = con.prepareStatement("delete from assignment3 where feature = 'sms'");
            pst2.executeUpdate();
            PreparedStatement pst3 = con.prepareStatement("delete from assignment3 where type_of_call = 'outgoing'");
            pst3.executeUpdate();
            PreparedStatement pst4 = con.prepareStatement("delete from assignment3 where status_of_call = 'voicemail'");
            pst4.executeUpdate();
        }
        catch(Exception ex)
        {
            System.out.println(ex);
        }
    }
}
