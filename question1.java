
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
            PreparedStatement pst2 = con.prepareStatement("select count(*) from assignment3 where feature != 'sms'");
            //Remove rows which has feature = sms
            
            PreparedStatement pst3 = con.prepareStatement("select count(*) from assignment3 where type_of_call != 'outgoing'");
            //Remove rows which has Type = outgoing
            
            PreparedStatement pst4 = con.prepareStatement("select count(*) from assignment3 where status_of_call != 'voicemail'");
            //Remove rows which has Status = Voicemail
            
            ResultSet rst2 = pst2.executeQuery();
            ResultSet rst3 = pst3.executeQuery();
            ResultSet rst4 = pst4.executeQuery();
            if(rst2.next())
            {
                System.out.println(rst2.getInt(1));
            }
            if(rst3.next())
            {
                System.out.println(rst3.getInt(1));
            }
            if(rst4.next())
            {
                System.out.println(rst4.getInt(1));
            }
        }
        catch (Exception e)
        {
            System.out.println("Alert!"+e);
        }
       
    }
}
