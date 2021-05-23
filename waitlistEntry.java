
import java.sql.Timestamp;
import java.sql.Date;
public class waitlistEntry 
{
    private String faculty;
    private Date date;
    private int seats;
    private Timestamp timestamp;
    
    public waitlistEntry(String a, Date b, int c, Timestamp d)
    {
        faculty = a;
        date = b;
        seats = c;
        timestamp = d;
    }
    
    public String getFaculty()
    {
        return faculty;
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
