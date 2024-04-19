package com.rs.listing.Restuarant.listing.DTO;

import com.rs.listing.Restuarant.listing.pojo.AdminUsers;

import java.util.List;

public class AdminUsersResDto extends AdminRolesResDto{

    public List<AdminUsers> users;

    public AdminUsersResDto() {}
    public AdminUsersResDto(String message, int status, List<AdminUsers> users) {
        super(message, status);
        this.users = users;
    }

    public List<AdminUsers> getUsers() {
        return users;
    }

    public void setUsers(List<AdminUsers> users) {
        this.users = users;
    }

    @Override
    public String toString() {
        return "AdminUsersResDto{" +
                "users=" + users +
                ", message='" + getMessage() + '\'' +
                ", status=" + getStatus() +
                '}';
    }
}
