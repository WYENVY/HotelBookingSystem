package service;

import DataAcessObjects.UserDAO;
import model.User;

public class UserService
{
    private UserDAO userDAO = new UserDAO();

    public void registerUser(User user)
    {
        userDAO.addUser(user);
    }

    public User login(String username, String password) 
    {
        User user = userDAO.getUserByUsername(username);
        if (user != null && user.getPassword().equals(password)) 
        {
            return user;
        }
        return null;
    }
}
