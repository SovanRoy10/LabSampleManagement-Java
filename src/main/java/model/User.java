package model;


public class User {
	
	
    private int id;  // primary key (auto increment in DB)
    private String username;
    private String email;
    private String u_password; 
    private String phoneNumber;
   
    
    // --- Constructors For Registration Purpose ---
    public User(String username, String email, String u_password, String phoneNumber) 
    {
        this.username = username;
        this.email = email;
        this.u_password = u_password;
        this.phoneNumber = phoneNumber;
    }

    // --- Constructors For Login Purpose ---
    public User(String email, String u_password) {
		this.email = email;
		this.u_password = u_password;
	}

	// --- Getters & Setters ---
    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }

    
    
    public String getUsername() {
        return username;
    }
    public void setUsername(String username) {
        this.username = username;
    }

    
    
    public String getEmail() {
        return email;
    }
    public void setEmail(String email) {
        this.email = email;
    }

    
    
    public String getu_password() {
        return u_password;
    }
    public void setu_password(String u_password) {
        this.u_password = u_password;
    }

    
    
    public String getPhoneNumber() {
        return phoneNumber;
    }
    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

}
