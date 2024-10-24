package DataAcessObjects;

import model.User;

import java.sql.*;

public class UserDAO 
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

    public void addUser(User user) 
    {
        String sql = "INSERT INTO Users(username, password, email, role) VALUES(?, ?, ?, ?)";

        try (Connection conn = connect();
             PreparedStatement pstmt = conn.prepareStatement(sql))
        {
            pstmt.setString(1, user.getUsername());
            pstmt.setString(2, user.getPassword());
            pstmt.setString(3, user.getEmail());
            pstmt.setString(4, user.getRole());
            pstmt.executeUpdate();
        }
        catch (SQLException e) 
        {
            System.out.println(e.getMessage());
        }
    }

    public User getUserByUsername(String username) 
    {
        String sql = "SELECT * FROM Users WHERE username = ?";
        User user = null;

        try (Connection conn = connect();
             PreparedStatement pstmt = conn.prepareStatement(sql)) 
        {
            pstmt.setString(1, username);
            ResultSet rs = pstmt.executeQuery();

            if (rs.next()) 
            {
                user = new User(
                        rs.getInt("id"),
                        rs.getString("username"),
                        rs.getString("password"),
                        rs.getString("email"),
                        rs.getString("role")
                );
            }
        }
        catch (SQLException e) 
        {
            System.out.println(e.getMessage());
        }
        return user;
    }
}
