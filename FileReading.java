
package myoperator_assignment3;

//This code is made to read the csv file and put it into the database. 
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.sql.Connection;

import java.sql.DriverManager;
import java.sql.PreparedStatement;

import java.text.SimpleDateFormat;
import java.util.Date;





public class FileReading {
    public static void main(String [] args)
    {
        Connection con = null;
        String path = "jdbc:mysql://localhost:3306/myoperator";
        try{
            Class.forName("com.mysql.cj.jdbc.Driver");
            con = DriverManager.getConnection(path,"root","root");// getting the connection
            FileInputStream input = new FileInputStream("C:/Users/Mukul-Gupta/Documents/myoperator_assignment/3Aug.csv");//reading the whole
            BufferedReader reader = new BufferedReader(new InputStreamReader(input));//reading the file line by line
            String row = "";
            int i=0;
            while((row = reader.readLine())!=null)// while we reach the end of the file 
            {
            //System.out.println(row);
            //row = reader.readLine();
            String start_date = row.split(",")[0];// getting the start time by splitting on ','
            String end_date = row.split(",")[1];// getting the end time by splitting on ','
            String type_of_call = row.split(",")[2];// getting the type of call by splitting on ','
            String status_of_call = row.split(",")[3];// getting the status of call by splitting on ','
            String feature = row.split(",")[4];//// getting the feature by splitting on ','
            //String sql = "INSERT INTO assignment3_2 (start_time, end_time, type_of_call, status_of_call, feature) VALUES('" + start_date + "','" + end_date + "','"+type_of_call+"','"+status_of_call+"','"+feature+"')'";
           // String sql = "insert into assignment3_2 (start_time, end_time, type_of_call, status_of_call, feature) VALUES ('?','?','?','?','?')";
           String sql = "insert into assignment3_2 values(?,?,?,?,?)";//sql statement to insert into database myoperator and table assignment3_2
            PreparedStatement pst = con.prepareStatement(sql);
            pst.setString(1, start_date);
            pst.setString(2, end_date);
            pst.setString(3, type_of_call);
            pst.setString(4, status_of_call);
            pst.setString(5, feature);
            pst.execute();
            i++;// for counting the number of rows
            }
            System.out.println("imported rows:"+i);
        }
        catch(Exception ex)// catching the exception
        {
            System.out.print(ex);
        }
    }
}
