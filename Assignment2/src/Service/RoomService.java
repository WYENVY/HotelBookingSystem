package service;

import DataAcessObjects.RoomDAO;
import model.Room;

import java.util.List;

public class RoomService 
{
    private RoomDAO roomDAO = new RoomDAO();

    public List<Room> getAvailableRooms()
    {
        return roomDAO.getAvailableRooms();
    }

    public Room getRoomById(int roomId) 
    {
        return roomDAO.getRoomById(roomId);
    }

    public void updateRoomStatus(Room room, String status)
    {
        room.setStatus(status);
        roomDAO.updateRoom(room);
    }
}
