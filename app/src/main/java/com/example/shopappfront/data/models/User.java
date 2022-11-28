package com.example.shopappfront.data.models;

/**
 * Data class that captures user information for logged in users retrieved from LoginRepository
 */
public class User extends ApplicationModelWithId implements AuthenticationApplicationModel {

    private String username;
    private String firstName;
    private String lastName;
    private String token;
    private String picUrl;
    private String permissions;
    private String password;

    public User(int id, String username, String firstName, String lastName, String token, String picUrl) {
        this(id, username, firstName, lastName, picUrl);
        this.token = token;
    }

    public User(int id, String username, String firstName, String lastName, String token,
                String picUrl, boolean permissions) {
        this(id, username, firstName, lastName, token, picUrl);
        this.permissions = permissions? "Admin" : "User";
    }

    public User(String username, String password) {
        super(-1);
        this.username = username;
        this.password = password;
    }

    public User(String username, String password, String firstName, String lastName, String picUrl) {
        super(-1);
        this.username = username;
        this.password = password;
        this.firstName = firstName;
        this.lastName = lastName;
        this.picUrl = picUrl;
    }

    public User(int id, String username, String firstName, String lastName, String picUrl) {
        super(id);
        this.username = username;
        this.firstName = firstName;
        this.lastName = lastName;
        this.picUrl = picUrl;
    }

//    public String getIdString() {
//        return String.valueOf(id);
//    }

    public String getUsername() {
        return username;
    }

    public int getId() {
        return id;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getToken() {
        return token;
    }

    public String getPicUrl() {
        return picUrl;
    }
    public String getPermissions() {
        return permissions;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getPassword() {
        return password;
    }
}