package model;

public class Room
{
    private int id;
    private int roomNumber;
    private String type;
    private double price;
    private String status;

    
    public Room(int id, int roomNumber, String type, double price, String status) 
    {
        this.id = id;
        this.roomNumber = roomNumber;
        this.type = type;
        this.price = price;
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

    public int getRoomNumber()
    {
        return roomNumber; 
    }
    
    public void setRoomNumber(int roomNumber)
    { 
        this.roomNumber = roomNumber;
    }

    public String getType() 
    { 
        return type; 
    }
    
    public void setType(String type) 
    {
        this.type = type; 
    }

    public double getPrice() 
    { 
        return price; 
    }
    public void setPrice(double price) 
    { 
        this.price = price; 
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
        return "Room [id= " + id + ", roomNumber= " + roomNumber + ", type= " + type + ", price= " + price + ", status= " + status + "]";
    }
}