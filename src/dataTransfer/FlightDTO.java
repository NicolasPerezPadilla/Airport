package dataTransfer;

import java.time.LocalDateTime;

public class FlightDTO {
    private String id, planeId, departureLocationId, scaleLocationId, arrivalLocationId;
    private int hoursDurationArrival, minutesDurationArrival, hoursDurationScale, minutesDurationScale;
    private LocalDateTime departureDate;
    
    public FlightDTO(){
        
    }
    public FlightDTO(
        String id, String planeId, String departureLocationId, String scaleLocationId, String arrivalLocationId,
        LocalDateTime departureDate, int hoursDurationArrival, int minutesDurationArrival,
        int hoursDurationScale, int minutesDurationScale){
        
        this.id = id;
        this.planeId = planeId;
        this.departureLocationId = departureLocationId;
        this.scaleLocationId = scaleLocationId;
        this.arrivalLocationId = arrivalLocationId;
        this.departureDate = departureDate;
        this.hoursDurationArrival = hoursDurationArrival;
        this.minutesDurationArrival = minutesDurationArrival;
        this.hoursDurationScale = hoursDurationScale;
        this.minutesDurationScale = minutesDurationScale;
    }

    //Getters
    public String getId() {return id;}
    public String getPlaneId() {return planeId;}
    public String getDepartureLocationId() {return departureLocationId;}
    public String getScaleLocationId() {return scaleLocationId;}
    public String getArrivalLocationId() {return arrivalLocationId;}
    public LocalDateTime getDepartureDate() {return departureDate;}
    public int getHoursDurationArrival() {return hoursDurationArrival;}
    public int getMinutesDurationArrival() {return minutesDurationArrival;}
    public int getHoursDurationScale() {return hoursDurationScale; }
    public int getMinutesDurationScale() {return minutesDurationScale;}

    //Setters
    public void setId(String id) {this.id = id;}
    public void setPlaneId(String planeId) {this.planeId = planeId;}
    public void setDepartureLocationId(String departureLocationId) {this.departureLocationId = departureLocationId;}
    public void setScaleLocationId(String scaleLocationId) {this.scaleLocationId = scaleLocationId;}
    public void setArrivalLocationId(String arrivalLocationId) {this.arrivalLocationId = arrivalLocationId;}
    public void setDepartureDate(LocalDateTime departureDate) {this.departureDate = departureDate;}
    public void setHoursDurationArrival(int hoursDurationArrival) {this.hoursDurationArrival = hoursDurationArrival;}
    public void setMinutesDurationArrival(int minutesDurationArrival) {this.minutesDurationArrival = minutesDurationArrival;}
    public void setHoursDurationScale(int hoursDurationScale) {this.hoursDurationScale = hoursDurationScale;}
    public void setMinutesDurationScale(int minutesDurationScale) {this.minutesDurationScale = minutesDurationScale;}
}

