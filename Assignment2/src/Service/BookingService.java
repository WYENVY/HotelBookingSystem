package service;

import DataAcessObjects.BookingDAO;
import DataAcessObjects.RoomDAO;
import model.Booking;
import model.Room;

import java.util.Date;
import java.util.List;

public class BookingService 
{
    private BookingDAO bookingDAO = new BookingDAO();
    private RoomDAO roomDAO = new RoomDAO();

    public boolean bookRoom(int userId, int roomId, Date startDate, Date endDate) 
    {
        Room room = roomDAO.getRoomById(roomId);
        if (room != null && room.getStatus().equals("available"))
        {
            room.setStatus("booked");
            roomDAO.updateRoom(room);

            Booking booking = new Booking(0, userId, roomId, startDate, endDate, "confirmed");
            bookingDAO.addBooking(booking);
            return true; 
        }
        return false; 
    }

    public List<Booking> getUserBookings(int userId) 
    {
        return bookingDAO.getBookingsByUserId(userId);
    }

    public boolean cancelBooking(int bookingId) 
    {
        Booking booking = bookingDAO.getBookingById(bookingId);
        if (booking != null) 
        {
            Room room = roomDAO.getRoomById(booking.getRoomId());
            room.setStatus("available");
            roomDAO.updateRoom(room);

            bookingDAO.cancelBooking(booking);
            return true; 
        }
        return false; 
    }
}
