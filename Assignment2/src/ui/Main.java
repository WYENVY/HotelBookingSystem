package ui;

import service.UserService;
import service.RoomService;
import service.BookingService;
import model.User;

import javax.swing.*;
import java.awt.*;

public class Main extends JFrame 
{
    private CardLayout cardLayout;
    private JPanel mainPanel;
    private UserService userService = new UserService();
    private RoomService roomService = new RoomService();
    private BookingService bookingService = new BookingService();
    private User currentUser;

    public Main() 
    {
        setTitle("Hotel Booking System");
        setSize(600, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        cardLayout = new CardLayout();
        mainPanel = new JPanel(cardLayout);

        mainPanel.add(new WelcomePanel(this), "Welcome");
        mainPanel.add(new UserMenuPanel(this), "UserMenu");

        add(mainPanel);
        setVisible(true);
    }

    public void showPanel(String panelName)
    {
        cardLayout.show(mainPanel, panelName);
    }

    public UserService getUserService() 
    {
        return userService;
    }

    public RoomService getRoomService() 
    {
        return roomService;
    }

    public BookingService getBookingService()
    {
        return bookingService;
    }

    public User getCurrentUser()
    {
        return currentUser;
    }

    public void setCurrentUser(User currentUser)
    {
        this.currentUser = currentUser;
    }

    public static void main(String[] args)
    {
        new Main();
    }
}
