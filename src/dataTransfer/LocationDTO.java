package dataTransfer;


public class LocationDTO {
    private String airportId, airportName, airportCity, airportCountry;
    private double airportLatitude, airportLongitude;

    public LocationDTO(){}

    public LocationDTO(
        String airportId, String airportName, String airportCity, String airportCountry, double airportLatitude, double airportLongitude) {
        this.airportId = airportId;
        this.airportName = airportName;
        this.airportCity = airportCity; 
        this.airportCountry = airportCountry;
        this.airportLatitude = airportLatitude;
        this.airportLongitude = airportLongitude;
    }

    // Getters
    public String getAirportId() {return airportId;}
    public String getAirportName() {return airportName;}
    public String getAirportCity() {return airportCity;}
    public String getAirportCountry() {return airportCountry;}
    public double getAirportLatitude() {return airportLatitude;}
    public double getAirportLongitude() {return airportLongitude;}

    // Setters
    public void setAirportId(String airportId) {this.airportId = airportId;}
    public void setAirportName(String airportName) {this.airportName = airportName;}
    public void setAirportCity(String airportCity) {this.airportCity = airportCity;}
    public void setAirportCountry(String airportCountry) {this.airportCountry = airportCountry;}
    public void setAirportLatitude(double airportLatitude) {this.airportLatitude = airportLatitude;}
    public void setAirportLongitude(double airportLongitude) {this.airportLongitude = airportLongitude;}
}

