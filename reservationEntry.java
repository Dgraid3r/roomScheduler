import java.sql.Date;
import java.sql.Timestamp;
public class reservationEntry 
{
    private String faculty;
    private String room;
    private Date date;
    private int seats;
    private Timestamp timestamp;
    
    public reservationEntry(String a, String b, Date c, int d, Timestamp e)
    {
        faculty = a;
        room = b;
        date = c;
        seats = d;
        timestamp = e;
        
    }
        public String getFaculty()
        {
            return faculty;
        }
        
        public String getRoom()
        {
            return room;
        }
        
        public Date getDate()
        {
            return date;
        }
        
        public int getSeats()
        {
            return seats;
        }
        
        public Timestamp getTimestamp()
        {
            return timestamp;
        }
    
}
