package DataAcessObjects;

import model.Room;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class RoomDAO 
{
    private Connection connect() 
    {
        String url = "jdbc:derby://localhost:1527/hotel_booking;user=hotel;password=hotel"; 
        Connection conn = null;
        try 
        {
            conn = DriverManager.getConnection(url);
        } 
        catch (SQLException e) 
        {
            System.out.println(e.getMessage());
        }
        return conn;
    }

    public void addRoom(Room room) 
    {
        String sql = "INSERT INTO Rooms(room_number, type, price, status) VALUES(?, ?, ?, ?)";

        try (Connection conn = connect();
             PreparedStatement pstmt = conn.prepareStatement(sql)) 
        {
            pstmt.setInt(1, room.getRoomNumber());
            pstmt.setString(2, room.getType());
            pstmt.setDouble(3, room.getPrice());
            pstmt.setString(4, room.getStatus());
            pstmt.executeUpdate();
        } 
        catch (SQLException e) 
        {
            System.out.println(e.getMessage());
        }
    }

    public Room getRoomById(int id) 
    {
        String sql = "SELECT * FROM Rooms WHERE id = ?";
        Room room = null;

        try (Connection conn = connect();
             PreparedStatement pstmt = conn.prepareStatement(sql)) 
        {
            pstmt.setInt(1, id);
            ResultSet rs = pstmt.executeQuery();

            if (rs.next()) 
            {
                room = new Room(
                        rs.getInt("id"),
                        rs.getInt("room_number"),
                        rs.getString("type"),
                        rs.getDouble("price"),
                        rs.getString("status")
                );
            }
        } 
        catch (SQLException e) 
        {
            System.out.println(e.getMessage());
        }
        return room;
    }

    public List<Room> getAvailableRooms()
    {
        String sql = "SELECT * FROM Rooms WHERE status = 'available'";
        List<Room> rooms = new ArrayList<>();

        try (Connection conn = connect();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) 
        {

            while (rs.next())
            {
                Room room = new Room(
                        rs.getInt("id"),
                        rs.getInt("room_number"),
                        rs.getString("type"),
                        rs.getDouble("price"),
                        rs.getString("status")
                );
                rooms.add(room);
            }
        } 
        catch (SQLException e)
        {
            System.out.println(e.getMessage());
        }
        return rooms;
    }

    public void updateRoom(Room room) 
    {
        String sql = "UPDATE Rooms SET status = ? WHERE id = ?";

        try (Connection conn = connect();
             PreparedStatement pstmt = conn.prepareStatement(sql)) 
        {
            pstmt.setString(1, room.getStatus());
            pstmt.setInt(2, room.getId());
            pstmt.executeUpdate();
        } 
        catch (SQLException e) 
        {
            System.out.println(e.getMessage());
        }
    }
}
