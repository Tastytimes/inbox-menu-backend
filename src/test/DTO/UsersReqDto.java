package com.rs.listing.Restuarant.listing.DTO;

public class UsersReqDto {
    private String name;
    private int id;
    private String email;
    private String password;
    private Boolean status;

    private String role;

    public UsersReqDto(){}
    public UsersReqDto(String name, String email, String password, Boolean status, String role, int id) {
        this.name = name;
        this.email = email;
        this.password = password;
        this.status = status;
        this.role = role;
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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

    public Boolean getStatus() {
        return status;
    }

    public void setStatus(Boolean status) {
        this.status = status;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    @Override
    public String toString() {
        return "UsersReqDto{" +
                "name='" + name + '\'' +
                ", id=" + id +
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
                ", status=" + status +
                ", role='" + role + '\'' +
                '}';
    }
}
