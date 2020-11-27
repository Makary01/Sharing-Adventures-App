package model;

import org.mindrot.jbcrypt.BCrypt;

public class User {
    private Integer id;
    private String username;
    private String email;
    private String password;
    private String city;
    private String country;

    //constructor for Dao class
    public User(Integer id, String username, String email, String password, String city, String country) {
        this.id = id;
        this.username = username;
        this.email = email;
        this.password = password;
        this.city = city;
        this.country = country;
    }
    public User() {
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setPassword(String password) {
        this.password = hashPassword(password);
    }


    public void setCity(String city) {
        this.city = city;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public Integer getId() {
        return id;
    }

    public String getUsername() {
        return username;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    public String getCity() {
        return city;
    }

    public String getCountry() {
        return country;
    }

    public void setId(Integer id) {
        this.id = id;
    }
    private String hashPassword(String password) {
        return BCrypt.hashpw(password, BCrypt.gensalt());
    }
}
