package dataTransfer;

import java.time.LocalDate;

public class PassengerDTO {
    private Long id, phone;
    private String firstName, lastName, country;
    private int countryPhoneCode;
    private LocalDate birthDate;
    
    public PassengerDTO(){}
    
    public PassengerDTO(Long id, String firstName, String lastName, LocalDate birthDate, int countryPhoneCode, Long phone, String country){
        this.id=id;
        this.phone=phone;
        this.firstName=firstName;
        this.lastName=lastName;
        this.birthDate=birthDate;
        this.country=country;
        this.countryPhoneCode=countryPhoneCode;
    }
    
    //Getters
    public long getId() {return id;}
    public long getPhone() {return phone;}
    public String getFirstName() {return firstName;}
    public String getLastName() {return lastName;}
    public LocalDate getBirthDate() {return birthDate;}
    public String getCountry() {return country;}
    public int getCountryPhoneCode() {return countryPhoneCode;}
    
    //Setters
    public void setId(Long id) {this.id = id;}
    public void setPhone(Long phone) {this.phone = phone;}
    public void setFirstName(String firstName) {this.firstName = firstName;}
    public void setLastName(String lastName) {this.lastName = lastName;}
    public void setBirthDate(LocalDate birthDate) {this.birthDate = birthDate;}
    public void setCountry(String country) {this.country = country;}
    public void setCountryPhoneCode(int countryPhoneCode) {this.countryPhoneCode = countryPhoneCode;} 
    
}
