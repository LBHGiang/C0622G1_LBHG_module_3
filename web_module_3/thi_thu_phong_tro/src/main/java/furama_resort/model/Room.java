package furama_resort.model;

public class Room {
    private int id;
    private String name;
    private String phoneNumber;
    private String rentDay;
    private int payMethod;
    private String description;

    public Room() {
    }

    public Room(int id, String name, String phoneNumber, String rentDay, int payMethod, String description) {
        this.id = id;
        this.name = name;
        this.phoneNumber = phoneNumber;
        this.rentDay = rentDay;
        this.payMethod = payMethod;
        this.description = description;
    }

    public Room(String name, String phoneNumber, String rentDay, int payMethod, String description) {
        this.name = name;
        this.phoneNumber = phoneNumber;
        this.rentDay = rentDay;
        this.payMethod = payMethod;
        this.description = description;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getRentDay() {
        return rentDay;
    }

    public void setRentDay(String rentDay) {
        this.rentDay = rentDay;
    }

    public int getPayMethod() {
        return payMethod;
    }

    public void setPayMethod(int payMethod) {
        this.payMethod = payMethod;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
