package com.rs.listing.Restuarant.listing.service;

import com.rs.listing.Restuarant.listing.DTO.AdminRolesReqDto;
import com.rs.listing.Restuarant.listing.DTO.AdminRolesResDto;
import com.rs.listing.Restuarant.listing.DTO.AdminRolesWrapper;
import com.rs.listing.Restuarant.listing.DTO.RolesWrapper;
import com.rs.listing.Restuarant.listing.pojo.AdminRoles;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface AdminRolesService {


    ResponseEntity<AdminRolesResDto> addRoles(AdminRolesReqDto req);
    ResponseEntity<AdminRolesWrapper> getAllRoles();

    ResponseEntity<RolesWrapper> getRole(int id);
    ResponseEntity<AdminRolesResDto> updateRole(int id, AdminRolesReqDto req);

    ResponseEntity<AdminRolesResDto> deleteRole(int id);
}
