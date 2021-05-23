
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;


public class roomqueries 
{
    /*
    java methods for the following sql queries on rooms
    get all possible rooms(select all), addroom(new row), drop room(delete room)
    */
        private static Connection connection;
    private static ArrayList<roomEntry> rooms = new ArrayList<roomEntry>();
    private static PreparedStatement addRoom;
    private static PreparedStatement getAllPossibleRooms;
    private static PreparedStatement dropRoom;
    private static PreparedStatement getRoomsBySize;
    private static ResultSet resultSet;
    
    public static void addRoom(roomEntry room)
    {
        connection = DBConnection.getConnection();
        try
        {
            addRoom = connection.prepareStatement("insert into rooms  values (?, ?)");
            addRoom.setString(1, room.getName());
            addRoom.setInt(2, room.getSeats());
            addRoom.executeUpdate();
        }
        catch(SQLException sqlException)
        {
            sqlException.printStackTrace();
        }
        
    }
    
    public static void dropRoom(roomEntry room)
    {
        //will just remove a room from the room database
        //sql command for deleting a room is 
        //DELETE FROM table_name WHERE name = 'name_here'
        connection = DBConnection.getConnection();
        try
        {
            dropRoom = connection.prepareStatement("DELETE FROM rooms WHERE Name=?");
            dropRoom.setString(1, room.getName());
            dropRoom.executeUpdate();
        }
        catch(SQLException sqlException)
        {
            sqlException.printStackTrace();
        }
        //when a room gets dropped, any faculty members who had the room reserved for any date will get another room reserved for that date if possible
        //and the new reservation will be reprted to the user; same setup as waitlist
    }
    public static ArrayList<String> getAllRooms()
    {
        connection = DBConnection.getConnection();
        ArrayList<roomEntry> rooms = new ArrayList<roomEntry>();
        ArrayList<String> roms = new ArrayList<String>();
        rooms = getAllPossibleRooms();
        for(int i = 0; i < rooms.size(); i ++)
        {
            roms.add(rooms.get(i).getName());
        }
        return roms;
    }
    
    public static ArrayList<roomEntry> getAllPossibleRooms()
    {
        connection = DBConnection.getConnection();
        ArrayList<roomEntry> rooms = new ArrayList<roomEntry>();
        try
        {
            getAllPossibleRooms = connection.prepareStatement("select * from rooms order by name");
            resultSet = getAllPossibleRooms.executeQuery();
            
            while(resultSet.next())
            {
                String temp = resultSet.getString(1);
                int temp2 = resultSet.getInt(2);
                roomEntry temp3 = new roomEntry(temp, temp2);
                rooms.add(temp3);
            }
        }
        catch(SQLException sqlException)
        {
            sqlException.printStackTrace();
        }
        return rooms;
    }
    
        public static ArrayList<roomEntry> getRoomsBySize()
    {
        connection = DBConnection.getConnection();
        ArrayList<roomEntry> rooms = new ArrayList<roomEntry>();
        try
        {
            getRoomsBySize = connection.prepareStatement("select * from rooms order by seats ASC");
            resultSet = getRoomsBySize.executeQuery();
            
            while(resultSet.next())
            {
                String temp = resultSet.getString(1);
                int temp2 = resultSet.getInt(2);
                roomEntry temp3 = new roomEntry(temp, temp2);
                rooms.add(temp3);
            }
        }
        catch(SQLException sqlException)
        {
            sqlException.printStackTrace();
        }
        return rooms;
    }
    
    
    
    
}
