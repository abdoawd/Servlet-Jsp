package beans;

import java.util.Date;


public class User  implements ListModelInterface{

    /**
     * @return the address
     */
    public Address getAddress() {
        return address;
    }

    /**
     * @param address the address to set
     */
    public void setAddress(Address address) {
        this.address = address;
    }

 

    /*user_id	fanme 	last name	role	email
            password	job	birthday	credit_limit*/
    private int  id ;



    private String firstName;
    private String lastName;
    private String role;
    private String email;
    private String password;
    private String job;
    private String /*date not sure */ birthday;
    private double creditlimits;

   private Address address;

    private String street;
    private String city;
    private String country;    


    public User() {
    }

    public User(String firstName, String lastName) {
        this.firstName = firstName;
        this.lastName = lastName;
    }



    public User(int id, String firstName, String lastName,String role, String email,String password, String job) {
   // public User(String id, String firstName, String lastName,String role, String email,
            //String password, String job,String birthday, double creditlimits) {

        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.role = role;
        this.email = email;
        this.password = password;
        this.job = job;

       
    }
    public int getId() {

        return id;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }


    public void setId(int id) {
        this.id = id;
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

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getJob() {
        return job;
    }

    public void setJob(String job) {
        this.job = job;
    }

    public String getBirthday() {
        return birthday;
    }

    public void setBirthday(String birthday) {
        this.birthday = birthday;
    }

    public double getCreditlimits() {
        return creditlimits;
    }

    public void setCreditlimits(double creditlimits) {
        this.creditlimits = creditlimits;
    }
}
