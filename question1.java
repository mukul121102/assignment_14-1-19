
package myoperator_assignment3;

//filtering data from database. 
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
            PreparedStatement pst2 = con.prepareStatement("delete from assignment3 where feature = 'sms'");
            //Remove rows which has feature = sms
            pst2.executeUpdate();
            PreparedStatement pst3 = con.prepareStatement("delete from assignment3 where type_of_call = 'outgoing'");
            //Remove rows which has Type = outgoing
            pst3.executeUpdate();
            PreparedStatement pst4 = con.prepareStatement("delete from assignment3 where status_of_call = 'voicemail'");
            //Remove rows which has Status = Voicemail
            pst4.executeUpdate();
        }
        catch (Exception e)
        {
            System.out.println("Alert!"+e);
        }
       
    }
}
