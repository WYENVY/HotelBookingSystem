package model;

import java.util.Date;

public class Booking 
{
    private int id;
    private int userId;
    private int roomId;
    private Date startDate;
    private Date endDate;
    private String status;

    
    public Booking(int id, int userId, int roomId, Date startDate, Date endDate, String status) 
    {
        this.id = id;
        this.userId = userId;
        this.roomId = roomId;
        this.startDate = startDate;
        this.endDate = endDate;
        this.status = status;
    }

    
    public int getId() 
    {
        return id; 
    }
    public void setId(int id) 
    { 
        this.id = id; 
    }

    public int getUserId() 
    { 
        return userId;
    }
    
    public void setUserId(int userId) 
    { 
        this.userId = userId; 
    }

    public int getRoomId() 
    { 
        return roomId; 
    }
    
    public void setRoomId(int roomId) 
    { 
        this.roomId = roomId; 
    }

    public Date getStartDate() 
    { 
        return startDate; 
    }
    
    public void setStartDate(Date startDate)
    {
        this.startDate = startDate; 
    }

    public Date getEndDate() 
    { 
        return endDate; 
    }
    public void setEndDate(Date endDate) 
    { 
        this.endDate = endDate;
    }

    public String getStatus() 
    {
        return status; 
    }
    
    public void setStatus(String status) 
    { 
        this.status = status;
    }

    @Override
    public String toString()
    {
        return "Booking [id= " + id + ", userId= " + userId + ", roomId= " + roomId + ", startDate= " + startDate + ", endDate= " + endDate + ", status= " + status + "]";
    }
}
