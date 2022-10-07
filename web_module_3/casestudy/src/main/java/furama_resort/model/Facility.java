package furama_resort.model;

public class Facility {
    private int id;
    private String name;
    private int area;
    private double cost;
    private int maxPeople;
    private int rentTypeId;
    private int facilityTypeId;
    private String standard;
    private String description;
    private double poolArea;
    private int floors;
    private String facilityFree;

    public Facility() {
    }

    public Facility(int id, String name, int area, double cost, int maxPeople, int rentTypeId, int facilityTypeId, String standard, String description, double poolArea, int floors, String facilityFree) {
        this.id = id;
        this.name = name;
        this.area = area;
        this.cost = cost;
        this.maxPeople = maxPeople;
        this.rentTypeId = rentTypeId;
        this.facilityTypeId = facilityTypeId;
        this.standard = standard;
        this.description = description;
        this.poolArea = poolArea;
        this.floors = floors;
        this.facilityFree = facilityFree;
    }

    public Facility(String name, int area, double cost, int maxPeople, int rentTypeId, int facilityTypeId, String standard, String description, double poolArea, int floors, String facilityFree) {
        this.name = name;
        this.area = area;
        this.cost = cost;
        this.maxPeople = maxPeople;
        this.rentTypeId = rentTypeId;
        this.facilityTypeId = facilityTypeId;
        this.standard = standard;
        this.description = description;
        this.poolArea = poolArea;
        this.floors = floors;
        this.facilityFree = facilityFree;
    }

//    Villa constructor
    public Facility(String name, int area, double cost, int maxPeople, int rentTypeId, int facilityTypeId, String standard, String description, double poolArea, int floors) {
        this.id = id;
        this.name = name;
        this.area = area;
        this.cost = cost;
        this.maxPeople = maxPeople;
        this.rentTypeId = rentTypeId;
        this.facilityTypeId = facilityTypeId;
        this.standard = standard;
        this.description = description;
        this.poolArea = poolArea;
        this.floors = floors;
    }

    //    House constructor
    public Facility(String name, int area, double cost, int maxPeople, int rentTypeId, int facilityTypeId, String standard, String description, int floors) {
        this.name = name;
        this.area = area;
        this.cost = cost;
        this.maxPeople = maxPeople;
        this.rentTypeId = rentTypeId;
        this.facilityTypeId = facilityTypeId;
        this.standard = standard;
        this.description = description;
        this.floors = floors;
    }

    //    Room constructor
    public Facility(String name, int area, double cost, int maxPeople, int rentTypeId, int facilityTypeId, String facilityFree) {
        this.name = name;
        this.area = area;
        this.cost = cost;
        this.maxPeople = maxPeople;
        this.rentTypeId = rentTypeId;
        this.facilityTypeId = facilityTypeId;
        this.facilityFree = facilityFree;
    }

    public Facility(int id) {
        this.id = id;
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

    public int getArea() {
        return area;
    }

    public void setArea(int area) {
        this.area = area;
    }

    public double getCost() {
        return cost;
    }

    public void setCost(double cost) {
        this.cost = cost;
    }

    public int getMaxPeople() {
        return maxPeople;
    }

    public void setMaxPeople(int maxPeople) {
        this.maxPeople = maxPeople;
    }

    public int getRentTypeId() {
        return rentTypeId;
    }

    public void setRentTypeId(int rentTypeId) {
        this.rentTypeId = rentTypeId;
    }

    public int getFacilityTypeId() {
        return facilityTypeId;
    }

    public void setFacilityTypeId(int facilityTypeId) {
        this.facilityTypeId = facilityTypeId;
    }

    public String getStandard() {
        return standard;
    }

    public void setStandard(String standard) {
        this.standard = standard;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public double getPoolArea() {
        return poolArea;
    }

    public void setPoolArea(double poolArea) {
        this.poolArea = poolArea;
    }

    public int getFloors() {
        return floors;
    }

    public void setFloors(int floors) {
        this.floors = floors;
    }

    public String getFacilityFree() {
        return facilityFree;
    }

    public void setFacilityFree(String facilityFree) {
        this.facilityFree = facilityFree;
    }
}
