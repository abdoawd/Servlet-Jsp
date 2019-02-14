package beans;

public class User {

    /*user_id	fanme 	last name	role	email
            password	job	birthday	credit_limit*/
    private String id ;
    private String firstName;
    private String lastName;
    private String role;
    private String email;
    private String password;
    private String jop;
    private String /*date not sure */ birthday;
    private int creditlimits;

    public User() {
    }

    public User(String firstName, String lastName) {
        this.firstName = firstName;
        this.lastName = lastName;
    }

}
