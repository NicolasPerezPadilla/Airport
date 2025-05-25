package dataTransfer;

public class PlaneDTO {
    private String id, brand, model, airline;
    private int maxCapacity;

    public PlaneDTO(){}

    public PlaneDTO(String id, String brand, String model, int maxCapacity, String airline){
        this.id = id;
        this.brand = brand;
        this.model = model;
        this.maxCapacity = maxCapacity;
        this.airline = airline;
    }

    // Getters
    public String getId() {return id;}
    public String getBrand() {return brand;}
    public String getModel() {return model;}
    public int getMaxCapacity() {return maxCapacity;}
    public String getAirline() {return airline;}

    // Setters
    public void setId(String id) {this.id = id;}
    public void setBrand(String brand) {this.brand = brand;}
    public void setModel(String model) {this.model = model;}
    public void setMaxCapacity(int maxCapacity) {this.maxCapacity = maxCapacity;}
    public void setAirline(String airline) {this.airline = airline;}
}

