package com.rs.listing.Restuarant.listing.DTO;

import com.rs.listing.Restuarant.listing.rest.AdminRoles;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Optional;

@Data
public class AdminRolesResDto {
    public String message;
    public int status;
    public AdminRolesResDto(){}

    public AdminRolesResDto(String message, int status) {
        this.message = message;
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "AdminRolesResDto{" +
                "message='" + message + '\'' +
                ", status=" + status +
                '}';
    }
}
