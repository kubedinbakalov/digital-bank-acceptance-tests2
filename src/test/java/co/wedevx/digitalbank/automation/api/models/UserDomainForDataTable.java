package co.wedevx.digitalbank.automation.api.models;

public class UserDomainForDataTable {
    String title;
    String firstName;
    String lastName;
    String gender;
    String dob;
    String ssn ;
    String emailAddress;
    String password;
    String address;
    String locality;
    String region;
    String postalCode;
    String country ;
    String homePhone ;
    String mobilPhone;
    String workPhone;
    public String getTitle() {
        return title;
    }
    public void setTitle(String title) {
        this.title = title;
    }
    public String getFirstName() {
        return firstName;
    }
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }
    public String getLastName() {
        return lastName;
    }
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }
    public String getGender() {
        return gender;
    }
    public void setGender(String gender) {
        this.gender = gender;
    }
    public String getDob() {
        return dob;
    }
    public void setDob(String dob) {
        this.dob = dob;
    }
    public String getSsn() {
        return ssn;
    }
    public void setSnn(String ssn) {
        this.ssn = ssn;
    }
    public String getEmailAddress() {
        return emailAddress;
    }
    public void setEmailAddress(String emailAddress) {
        this.emailAddress = emailAddress;
    }
    public String getPassword() {
        return password;
    }
    public void setPassword(String password) {
        this.password = password;
    }
    public String getAddress() {
        return address;
    }
    public void setAddress(String address) {
        this.address = address;
    }
    public String getLocality() {
        return locality;
    }
    public void setLocality(String locality) {
        this.locality = locality;
    }
    public String getRegion() {
        return region;
    }
    public void setRegion(String region) {
        this.region = region;
    }
    public String getPostalCode() {
        return postalCode;
    }
    public void setPostalCode(String postalCode) {
        this.postalCode = postalCode;
    }
    public String getCountry() {
        return country;
    }
    public void setCountry(String country) {
        this.country = country;
    }
    public String getHomePhone() {
        return homePhone;
    }
    public void setHomePhone(String homePhone) {
        this.homePhone = homePhone;
    }
    public String getMobilPhone() {
        return mobilPhone;
    }
    public void setMobilPhone(String mobilPhone) {
        this.mobilPhone = mobilPhone;
    }
    public String getWorkPhone() {
        return workPhone;
    }
    public void setWorkPhone(String workPhone) {
        this.workPhone = workPhone;
    }
    public UserDomainForDataTable(String title, String firstName, String lastName, String gender, String dob, String ssn, String emailAddress, String password, String address, String locality, String region, String postalCode, String country, String homePhone, String mobilPhone, String workPhone) {
        this.title = title;
        this.firstName = firstName;
        this.lastName = lastName;
        this.gender = gender;
        this.dob = dob;
        this.ssn = ssn;
        this.emailAddress = emailAddress;
        this.password = password;
        this.address = address;
        this.locality = locality;
        this.region = region;
        this.postalCode = postalCode;
        this.country = country;
        this.homePhone = homePhone;
        this.mobilPhone = mobilPhone;
        this.workPhone = workPhone;
    }

    @Override
    public String toString() {
        return "User{" +
                "address='" + address + '\'' +
                ", country='" + country + '\'' +
                ", dob='" + dob + '\'' +
                ", emailAddress='" + emailAddress + '\'' +
                ", firstName='" + firstName + '\'' +
                ", gender='" + gender + '\'' +
                ", homePhone='" + homePhone + '\'' +
                ", lastName='" + lastName + '\'' +
                ", locality='" + locality + '\'' +
                ", mobilPhone='" + mobilPhone + '\'' +
                ", password='" + password + '\'' +
                ", postalCode='" + postalCode + '\'' +
                ", region='" + region + '\'' +
                ", ssn='" + ssn + '\'' +
                ", title='" + title + '\'' +
                ", workPhone='" + workPhone + '\'' +
                '}';
    }
}
