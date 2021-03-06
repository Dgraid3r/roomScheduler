
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.sql.Date;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


public class Dates 
{
    //code it like a faculty jawn
    //combobox for dates and faculty later on
    //all of the adds are adding a new row into their specific table
    private static Connection connection;
    private static ArrayList<Date> dates = new ArrayList<Date>();
    private static PreparedStatement addDate;
    private static PreparedStatement getDateList;
    private static ResultSet resultSet;
    
    public static void addDate(Date day)
    {
        connection = DBConnection.getConnection();
        try
        {
            addDate = connection.prepareStatement("insert into DATES (date) values (?)");
            addDate.setDate(1, day);
            addDate.executeUpdate();
        }
        catch(SQLException sqlException)
        {
            sqlException.printStackTrace();
        }
        
    }
    
    public static ArrayList<Date> getDateList()
    {
        connection = DBConnection.getConnection();
        ArrayList<Date> dates = new ArrayList<Date>();
        try
        {
            getDateList = connection.prepareStatement("select date from dates order by date");
            resultSet = getDateList.executeQuery();
            
            while(resultSet.next())
            {
                dates.add(resultSet.getDate(1));
            }
        }
        catch(SQLException sqlException)
        {
            sqlException.printStackTrace();
        }
        return dates;
        
    }
    
    
}
