
package myoperator_assignment3;

/*
Let’s assume max number of calls from problem 4 be X, if we are
currently using 10 servers to facilitate the calls X, which is just
enough to distribute the load evenly on all 10 server with all
servers running on their full capacity. Find minimum number of
servers required for each hour in a day to facilitate the calls for
that hour.
*/
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
            int max_calls = question4.doQuestion(); // used to get the max number of calls from previous question and question4.class
            float capacity = (float) max_calls/10;// since there are 10 servers and each server is working at full capacity. So, capacity for each server would maxcalls/10
            System.out.println("Max capacity of each server is:"+capacity);
            PreparedStatement pst = con.prepareStatement("select hour(start_time), count(*)as calls from assignment3 group by hour(start_time)");
            //this sql statement is used to get calls for each hour. 
            ResultSet rst = pst.executeQuery();
            int i=1;
            while(rst.next())
            {
                String hour = rst.getString(1);
                int calls = rst.getInt(2);
                
                i=1;
                //one way to solve the problem. 
                /*while(i<=10) //make this problem to be solved using n^2.
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
                }*/
                //better way to solve this problem. 
                int min_server = (int) Math.ceil(calls/capacity); //get the round-off value calls/capacity which would give the minimum number of servers required. 
                System.out.println(hour+"\t"+calls+"\t"+min_server);
            }
        }
        catch(Exception e)
        {
            System.out.println("Alert!"+e);
        }
    }
}
