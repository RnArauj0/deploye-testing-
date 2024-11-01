package com.example.nutrisend.platform.user.domain.model.aggregates;

public class Users {
    private Long id;
    private String name;
    private String surname;
    private String email;
    private String password;
    private String plan;

    // Constructor
    public Users(Long id, String name, String surname, String email, String password, String plan) {
        this.id = id;
        this.name = name;
        this.surname = surname;
        this.email = email;
        this.password = password;
        this.plan = plan;
    }

    // Getters y setters
    public Long getId() { return id; }
    public String getName() { return name; }
    public String getSurname() { return surname; }
    public String getEmail() { return email; }
    public String getPassword() { return password; }
    public String getPlan() { return plan; }

}