package com.rs.listing.Restuarant.listing.DTO;

import com.rs.listing.Restuarant.listing.pojo.AdminRoles;

import java.util.List;

public class AdminRolesWrapper extends AdminRolesResDto{
    public List<AdminRoles> data;

    public List getData() {
        return data;
    }
    public AdminRolesWrapper(){}
    public AdminRolesWrapper(String message, int status, List data) {
        super(message, status);
        this.data = data;
    }

    public void setData(List data) {
        this.data = data;
    }

    @Override
    public String toString() {
        return "AdminRolesWrapper{" +
                "data=" + data +
                ", message='" + getMessage() + '\'' + // Access message field using getter method
                ", status=" + getStatus() +           // Access status field using getter method
                '}';
    }
}
