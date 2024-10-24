package ui;

import model.Room;
import model.Booking;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class UserMenuPanel extends JPanel 
{
    private Main mainFrame;
    private static final String DATE_FORMAT = "dd-MM-yyyy"; 
    private static final SimpleDateFormat simpledateformat = new SimpleDateFormat(DATE_FORMAT);

    public UserMenuPanel(Main mainFrame) 
    {
        this.mainFrame = mainFrame;
        setLayout(new GridLayout(5, 1));

        JButton viewRoomsButton = new JButton("View Available Rooms");
        viewRoomsButton.addActionListener(new ViewRoomsAction());

        JButton bookRoomButton = new JButton("Book a Room");
        bookRoomButton.addActionListener(new BookRoomAction());

        JButton viewBookingsButton = new JButton("View My Bookings");
        viewBookingsButton.addActionListener(new ViewBookingsAction());

        JButton cancelBookingButton = new JButton("Cancel Booking");
        cancelBookingButton.addActionListener(new CancelBookingAction());

        JButton logoutButton = new JButton("Logout");
        logoutButton.addActionListener(e -> 
        {
            mainFrame.setCurrentUser(null);
            mainFrame.showPanel("Welcome");
        });

        add(viewRoomsButton);
        add(bookRoomButton);
        add(viewBookingsButton);
        add(cancelBookingButton);
        add(logoutButton);
    }

    private void showError(String message) 
    {
        JOptionPane.showMessageDialog(UserMenuPanel.this, message, "Error", JOptionPane.ERROR_MESSAGE);
    }

    private void showInfo(String message) 
    {
        JOptionPane.showMessageDialog(UserMenuPanel.this, message, "Information", JOptionPane.INFORMATION_MESSAGE);
    }

    private class ViewRoomsAction implements ActionListener 
    {
        @Override
        public void actionPerformed(ActionEvent e) 
        {
            List<Room> rooms = mainFrame.getRoomService().getAvailableRooms();
            if (rooms.isEmpty())
            {
                showError("No rooms available at the moment.");
                return;
            }
            StringBuilder roomsList = new StringBuilder("Available Rooms:\n");
            for (Room room : rooms) 
            {
                roomsList.append(room.toString()).append("\n");
            }
            JOptionPane.showMessageDialog(UserMenuPanel.this, roomsList.toString());
        }
    }

    private class BookRoomAction implements ActionListener
    {
    @Override
    public void actionPerformed(ActionEvent e)
    {
        String roomIdStr = JOptionPane.showInputDialog("Enter Room ID to book:");
        if (roomIdStr == null || roomIdStr.trim().isEmpty()) 
        {
            showError("Room ID cannot be empty.");
            return; 
        }

        try 
        {
            int roomId = Integer.parseInt(roomIdStr);
            
            
            String startDateStr = JOptionPane.showInputDialog("Enter Start Date (dd-MM-yyyy):");
            String endDateStr = JOptionPane.showInputDialog("Enter End Date (dd-MM-yyyy):");
            
            
            simpledateformat.setLenient(false);
            Date startDate = simpledateformat.parse(startDateStr);
            Date endDate = simpledateformat.parse(endDateStr);
            
            
            if (!endDate.after(startDate)) 
            {
                showError("End date must be after start date.");
                return;
            }

            
            int userId = mainFrame.getCurrentUser().getId();

            
            boolean bookingSuccess = mainFrame.getBookingService().bookRoom(userId, roomId, startDate, endDate);
            if (bookingSuccess) 
            {
                JOptionPane.showMessageDialog(UserMenuPanel.this, "Room booked successfully!");
            } 
            else
            {
                showError("Room booking failed. Please check room availability.");
            }
            
        } 
        catch (NumberFormatException ex) 
        {
            showError("Invalid Room ID format. Please enter a valid number.");
        } 
        catch (ParseException ex)
        {
            showError("Invalid date format. Please enter dates in the format dd-MM-yyyy.");
        }
    }
}


    private class ViewBookingsAction implements ActionListener 
    {
        @Override
        public void actionPerformed(ActionEvent e) 
        {
            List<Booking> bookings = mainFrame.getBookingService().getUserBookings(mainFrame.getCurrentUser().getId());
            if (bookings.isEmpty())
            {
                showError("You have no bookings.");
                return;
            }
            StringBuilder bookingsList = new StringBuilder("Your Bookings:\n");
            for (Booking booking : bookings) 
            {
                bookingsList.append(booking.toString()).append("\n");
            }
            JOptionPane.showMessageDialog(UserMenuPanel.this, bookingsList.toString());
        }
    }

    private class CancelBookingAction implements ActionListener 
    {
        @Override
        public void actionPerformed(ActionEvent e)
        {
            String bookingIdStr = JOptionPane.showInputDialog("Enter Booking ID to cancel:");
            if (bookingIdStr == null || bookingIdStr.trim().isEmpty()) 
            {
                showError("Booking ID cannot be empty.");
                return;
            }

            
            int confirm = JOptionPane.showConfirmDialog(UserMenuPanel.this, "Are you sure you want to cancel this booking?", "Confirm Cancellation", JOptionPane.YES_NO_OPTION);
            if (confirm != JOptionPane.YES_OPTION) 
            {
                return; 
            }

            try 
            {
                int bookingId = Integer.parseInt(bookingIdStr);
                boolean cancellationSuccess = mainFrame.getBookingService().cancelBooking(bookingId);
                if (cancellationSuccess) 
                {
                    showInfo("Booking canceled successfully!");
                } 
                else 
                {
                    showError("Cancellation failed. Please check if the booking ID is valid.");
                }
            }
            catch (NumberFormatException ex) 
            {
                showError("Invalid Booking ID format. Please enter a valid number.");
            }
        }
    }
}
