package com.rs.listing.Restuarant.listing.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AdminRolesReqDto {

    private String name;
    private Boolean status;
}
