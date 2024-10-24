package DataAcessObjects;

import model.Booking;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class BookingDAO 
{
    
    private static final String DB_URL = "jdbc:derby://localhost:1527/hotel_booking";
    private static final String USER = "hotel"; 
    private static final String PASS = "hotel"; 

    
    public void addBooking(Booking booking) 
    {
        String sql = "INSERT INTO BOOKINGS (USER_ID, ROOM_ID, START_DATE, END_DATE, STATUS) VALUES (?, ?, ?, ?, ?)";
        try (Connection conn = DriverManager.getConnection(DB_URL, USER, PASS);
             PreparedStatement pstmt = conn.prepareStatement(sql)) 
        {

            pstmt.setInt(1, booking.getUserId());
            pstmt.setInt(2, booking.getRoomId());
            pstmt.setDate(3, new java.sql.Date(booking.getStartDate().getTime()));
            pstmt.setDate(4, new java.sql.Date(booking.getEndDate().getTime()));
            pstmt.setString(5, booking.getStatus()); 

            pstmt.executeUpdate();

        } 
        catch (SQLException e) 
        {
            e.printStackTrace();
        }
    }

    
    public List<Booking> getBookingsByUserId(int userId) 
    {
        List<Booking> userBookings = new ArrayList<>();
        String sql = "SELECT * FROM BOOKINGS WHERE USER_ID = ?";

        try (Connection conn = DriverManager.getConnection(DB_URL, USER, PASS);
             PreparedStatement pstmt = conn.prepareStatement(sql)) 
        {

            pstmt.setInt(1, userId);
            ResultSet rs = pstmt.executeQuery();

            while (rs.next()) 
            {
                Booking booking = new Booking(
                    rs.getInt("ID"),
                    rs.getInt("USER_ID"),
                    rs.getInt("ROOM_ID"),
                    rs.getDate("START_DATE"),
                    rs.getDate("END_DATE"),
                    rs.getString("STATUS")
                );
                userBookings.add(booking);
            }

        } 
        catch (SQLException e) 
        {
            e.printStackTrace();
        }
        return userBookings;
    }

    
    public Booking getBookingById(int id)
    {
        String sql = "SELECT * FROM BOOKINGS WHERE ID = ?";
        Booking booking = null;

        try (Connection conn = DriverManager.getConnection(DB_URL, USER, PASS);
             PreparedStatement pstmt = conn.prepareStatement(sql)) 
        {

            pstmt.setInt(1, id);
            ResultSet rs = pstmt.executeQuery();

            if (rs.next()) 
            {
                booking = new Booking(
                    rs.getInt("ID"),
                    rs.getInt("USER_ID"),
                    rs.getInt("ROOM_ID"),
                    rs.getDate("START_DATE"),
                    rs.getDate("END_DATE"),
                    rs.getString("STATUS")
                );
            }

        } 
        catch (SQLException e) 
        {
            e.printStackTrace();
        }
        return booking;
    }

    
    public void cancelBooking(Booking booking)
    {
        String sql = "DELETE FROM BOOKINGS WHERE ID = ?";

        try (Connection conn = DriverManager.getConnection(DB_URL, USER, PASS);
             PreparedStatement pstmt = conn.prepareStatement(sql)) 
        {

            pstmt.setInt(1, booking.getId());
            pstmt.executeUpdate();

        } 
        catch (SQLException e)
        {
            e.printStackTrace();
        }
    }
}
