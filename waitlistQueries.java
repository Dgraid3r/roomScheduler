
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.sql.Date;
import java.sql.Timestamp;


public class waitlistQueries 
{

    /*
    java methods for the following sql queries on rooms
    get all possible rooms(select all), addroom(new row), drop room(delete room)
    */
    private static Connection connection;
    private static ArrayList<waitlistEntry> entries = new ArrayList<waitlistEntry>();
    private static PreparedStatement addWaitlist;
    private static PreparedStatement getWaitlistByDate;
    private static PreparedStatement deleteWaitlistEntry;
    public static PreparedStatement getWaitlistByFaculty;
    private static ResultSet resultSet;
    
    public static void addWaitlist(waitlistEntry entry)
    {
        connection = DBConnection.getConnection();
        try
        {
            addWaitlist = connection.prepareStatement("insert into waitlist  values (?,?,?,?)");
            addWaitlist.setString(1, entry.getFaculty());
            addWaitlist.setDate(2, entry.getDate());
            addWaitlist.setInt(3, entry.getSeats());
            addWaitlist.setTimestamp(4, entry.getTimestamp());
            addWaitlist.executeUpdate();
        }
        catch(SQLException sqlException)
        {
            sqlException.printStackTrace();
        }
        
    }
    
    public static void deleteWaitlistEntry(waitlistEntry entry)
    {
        connection = DBConnection.getConnection();
        try
        {
            deleteWaitlistEntry = connection.prepareStatement("DELETE FROM waitlist WHERE Faculty=?");
            deleteWaitlistEntry.setString(1, entry.getFaculty());
            deleteWaitlistEntry.executeUpdate();
        }
        catch(SQLException sqlException)
        {
            sqlException.printStackTrace();
        }
    }
    
    public static ArrayList<waitlistEntry> getWaitlistByDate()
    {
        connection = DBConnection.getConnection();
        ArrayList<waitlistEntry> entries = new ArrayList<waitlistEntry>();
        try
        {
            getWaitlistByDate = connection.prepareStatement("select * from waitlist order by timestamp");
            resultSet = getWaitlistByDate.executeQuery();
            
            while(resultSet.next())
            {
                String temp = resultSet.getString(1);
                Date temp2 = resultSet.getDate(2);
                int temp3 = resultSet.getInt(3);
                Timestamp temp4 = resultSet.getTimestamp(4);
                waitlistEntry temp5 = new waitlistEntry(temp, temp2, temp3, temp4);
                entries.add(temp5);
            }
        }
        catch(SQLException sqlException)
        {
            sqlException.printStackTrace();
        }
        return entries;
    }
    
        public static ArrayList<waitlistEntry> getWaitlistByFaculty()
    {
        connection = DBConnection.getConnection();
        ArrayList<waitlistEntry> entries = new ArrayList<waitlistEntry>();
        try
        {
            getWaitlistByFaculty = connection.prepareStatement("select * from waitlist order by faculty");
            resultSet = getWaitlistByFaculty.executeQuery();
            
            while(resultSet.next())
            {
                String temp = resultSet.getString(1);
                Date temp2 = resultSet.getDate(2);
                int temp3 = resultSet.getInt(3);
                Timestamp temp4 = resultSet.getTimestamp(4);
                waitlistEntry temp5 = new waitlistEntry(temp, temp2, temp3, temp4);
                entries.add(temp5);
            }
        }
        catch(SQLException sqlException)
        {
            sqlException.printStackTrace();
        }
        return entries;
    }
    
}


