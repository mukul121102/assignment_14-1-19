
package myoperator_assignment3;
//Find which hour has max. number of calls.
import java.sql.*;


public class question4 {
    public static void main(String [] args)
    {
        doQuestion();
        //System.out.println("Answer is "+ answer);
    }
    public static int doQuestion()
    {
        Connection con = null;
        String path = "jdbc:mysql://localhost:3306/myoperator";
        try{
            Class.forName("com.mysql.cj.jdbc.Driver");
            con = DriverManager.getConnection(path,"root","root");
            
            PreparedStatement pst = con.prepareStatement("select hour(start_time),count(hour(start_time)) as mycount from assignment3 group by hour(start_time) order by mycount desc limit 1;");
            //get the hour time with max number of calls. 
            ResultSet rst = pst.executeQuery();
            int count = 0, max_hour=0;
            while(rst.next())
            {
                max_hour = Integer.parseInt(rst.getString(1));
                
                count = rst.getInt(2);// count of the total number of calls for that hour. 
                
            }
            
            System.out.print("Hour having max. number of calls:"+max_hour+" with max calls of:"+count+"calls\n");
            return count;
        }
        catch(Exception e)
        {
            System.out.println("Alert!"+e);
        }
        return 0;
    }
}
