package DataAcessObjects;

import model.Booking;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.sql.Date;
import java.util.List;

import static org.junit.Assert.*;

public class BookingDAOTest 
{
    
    private BookingDAO bookingDAO;
    private Booking testBooking;

    @Before
    public void setUp() throws Exception 
    {
        bookingDAO = new BookingDAO();
        
        testBooking = new Booking(1, 1, 101, Date.valueOf("2024-10-01"), Date.valueOf("2024-10-05"), "CONFIRMED");
        
        bookingDAO.addBooking(testBooking);
    }

    @After
    public void tearDown() throws Exception 
    {
        
        List<Booking> bookings = bookingDAO.getBookingsByUserId(1);
        for (Booking booking : bookings) 
        {
            bookingDAO.cancelBooking(booking);
        }
    }

    @Test
    public void testAddBooking() 
    {
        Booking booking = new Booking(2, 1, 102, Date.valueOf("2024-10-10"), Date.valueOf("2024-10-15"), "CONFIRMED");
        bookingDAO.addBooking(booking);
        
        Booking fetchedBooking = bookingDAO.getBookingById(booking.getId());
        assertNotNull(fetchedBooking);
        assertEquals(booking.getUserId(), fetchedBooking.getUserId());
        assertEquals(booking.getRoomId(), fetchedBooking.getRoomId());
        assertEquals(booking.getStartDate(), fetchedBooking.getStartDate());
        assertEquals(booking.getEndDate(), fetchedBooking.getEndDate());
        assertEquals(booking.getStatus(), fetchedBooking.getStatus());
    }

    @Test
    public void testGetBookingsByUserId() 
    {
        List<Booking> bookings = bookingDAO.getBookingsByUserId(1);
        assertFalse(bookings.isEmpty());
        assertEquals(1, bookings.size());
        assertEquals(testBooking.getId(), bookings.get(0).getId());
    }

    @Test
    public void testGetBookingById() 
    {
        Booking fetchedBooking = bookingDAO.getBookingById(testBooking.getId());
        assertNotNull(fetchedBooking);
        assertEquals(testBooking.getId(), fetchedBooking.getId());
    }

    @Test
    public void testCancelBooking()
    {
        bookingDAO.cancelBooking(testBooking);
        Booking fetchedBooking = bookingDAO.getBookingById(testBooking.getId());
        assertNull(fetchedBooking); 
    }
}
