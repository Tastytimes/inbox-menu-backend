package com.rs.listing.Restuarant.listing.DTO;

import com.rs.listing.Restuarant.listing.pojo.AdminRoles;

public class RolesWrapper extends AdminRolesResDto  {
    private AdminRoles details;

    public RolesWrapper(){}
    public RolesWrapper(AdminRoles details) {
        this.details = details;
    }

    public RolesWrapper(String message, int status, AdminRoles details) {
        super(message, status);
        this.details = details;
    }

    public AdminRoles getDetails() {
        return details;
    }

    public void setDetails(AdminRoles details) {
        this.details = details;
    }

    @Override
    public String toString() {
        return "RolesWrapper{" +
                "details=" + details +
                ", message='" + message + '\'' +
                ", status=" + status +
                '}';
    }
}
