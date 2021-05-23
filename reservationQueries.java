
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.sql.Date;
import java.sql.Timestamp;
import java.util.Calendar;
import java.util.Locale;


public class reservationQueries
{
    private static Connection connection;
    private static ArrayList<reservationEntry> reservations = new ArrayList<reservationEntry>();
    private static PreparedStatement addReservationEntry;
    private static PreparedStatement getReservationsByDate;
    private static PreparedStatement dropReservationEntry;
    private static PreparedStatement getReservationsByFaculty;
    private static PreparedStatement getRoomsReservedByDate;
    private static PreparedStatement getRoomsReserved;
    private static ResultSet resultSet;
    
    public static void addReservationEntry(reservationEntry entry)
    {
        connection = DBConnection.getConnection();
        try
        {
            addReservationEntry = connection.prepareStatement("insert into reservations values (?, ?, ?, ? ,?)");
            addReservationEntry.setString(1, entry.getFaculty());
            addReservationEntry.setString(2, entry.getRoom());
            addReservationEntry.setDate(3, entry.getDate());
            addReservationEntry.setInt(4,entry.getSeats());
            addReservationEntry.setTimestamp(5,entry.getTimestamp());
            addReservationEntry.executeUpdate();
        }
        catch(SQLException sqlException)
        {
            sqlException.printStackTrace();
        }
        
    }
    //add getRoomsReservedByDate
    //add getReservationsByFaculty
    public static void getRoomsReservedByDate()
    {
        //either change return type to arraylist or some other jawn
    }
    public static void deleteReservation(reservationEntry entry)
    {

    
        //will just remove a room from the room database
        //sql command for deleting a room is 
        //DELETE FROM table_name WHERE name = 'name_here'
        connection = DBConnection.getConnection();
        try
        {
            dropReservationEntry = connection.prepareStatement("DELETE FROM reservations WHERE Faculty=?");
            dropReservationEntry.setString(1, entry.getFaculty());
            dropReservationEntry.executeUpdate();
        }
        catch(SQLException sqlException)
        {
            sqlException.printStackTrace();
        }
    
    }
    public static ArrayList<reservationEntry> getReservationsByDate()
    {
        connection = DBConnection.getConnection();
        ArrayList<reservationEntry> entries = new ArrayList<reservationEntry>();
        try
        {
            getReservationsByDate = connection.prepareStatement("select * from reservations order by date");
            resultSet = getReservationsByDate.executeQuery();
            
            while(resultSet.next())
            {
                String temp1 = resultSet.getString(1);
                String temp2 = resultSet.getString(2);
                Date temp3 = resultSet.getDate(3);
                int temp4 = resultSet.getInt(4);
                Timestamp temp5 = resultSet.getTimestamp(5);
                reservationEntry temp6 = new reservationEntry(temp1, temp2, temp3, temp4, temp5);
                entries.add(temp6);
            }
        }
        catch(SQLException sqlException)
        {
            sqlException.printStackTrace();
        }
        return entries;
        
    }
    public static ArrayList<String> getRoomsReserved()
    {
        connection = DBConnection.getConnection();
        ArrayList<String> entries = new ArrayList<String>();
        try
        {
            getReservationsByDate = connection.prepareStatement("select * from reservations ");
            resultSet = getReservationsByDate.executeQuery();
            
            while(resultSet.next())
            {
                String temp1 = resultSet.getString(2);
                int temp2 = resultSet.getInt(4);
                roomEntry temp3 = new roomEntry(temp1, temp2);
                entries.add(temp1);
            }
        }
        catch(SQLException sqlException)
        {
            sqlException.printStackTrace();
        }
        return entries;
    }
    
    
    public static ArrayList<reservationEntry> getReservationsByFaculty()
    {
        connection = DBConnection.getConnection();
        ArrayList<reservationEntry> entries = new ArrayList<reservationEntry>();
        try
        {
            getReservationsByFaculty = connection.prepareStatement("select * from reservations order by faculty");
            resultSet = getReservationsByFaculty.executeQuery();
            
            while(resultSet.next())
            {
                String temp1 = resultSet.getString(1);
                String temp2 = resultSet.getString(2);
                Date temp3 = resultSet.getDate(3);
                int temp4 = resultSet.getInt(4);
                Timestamp temp5 = resultSet.getTimestamp(5);
                reservationEntry temp6 = new reservationEntry(temp1, temp2, temp3, temp4, temp5);
                entries.add(temp6);
            }
        }
        catch(SQLException sqlException)
        {
            sqlException.printStackTrace();
        }
        return entries;
        
    }
    
    
}
