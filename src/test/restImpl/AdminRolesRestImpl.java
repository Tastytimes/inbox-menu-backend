package com.rs.listing.Restuarant.listing.restImpl;

import com.rs.listing.Restuarant.listing.DTO.AdminRolesReqDto;
import com.rs.listing.Restuarant.listing.DTO.AdminRolesResDto;
import com.rs.listing.Restuarant.listing.DTO.AdminRolesWrapper;
import com.rs.listing.Restuarant.listing.DTO.RolesWrapper;
import com.rs.listing.Restuarant.listing.rest.AdminRoles;
import com.rs.listing.Restuarant.listing.service.AdminRolesService;
import com.rs.listing.Restuarant.listing.serviceImpl.AdminRolesServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@Slf4j
public class AdminRolesRestImpl implements AdminRoles {

    Logger logger = LoggerFactory.getLogger(AdminRolesServiceImpl.class);
    @Autowired
    AdminRolesService adminRolesService;

    public ResponseEntity<AdminRolesResDto> addRoles(AdminRolesReqDto req) {
        try {
            return adminRolesService.addRoles(req);
        }catch (Exception e) {
            e.printStackTrace();
        }
        return new ResponseEntity<>( HttpStatus.INTERNAL_SERVER_ERROR);
    }

    public ResponseEntity<AdminRolesWrapper> getAllRoles() {
        try {
            return adminRolesService.getAllRoles();
        }catch (Exception e) {
            e.printStackTrace();
        }
        return new ResponseEntity<>( HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @Override
    public ResponseEntity<RolesWrapper> getRole(int roleId) {
        try {
            return adminRolesService.getRole(roleId);
        }catch (Exception e) {
            e.printStackTrace();
        }
        return new ResponseEntity<>( HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @Override
    public ResponseEntity<AdminRolesResDto> updateRole(int roleId, AdminRolesReqDto req) {
        try {
            return adminRolesService.updateRole(roleId, req);
        }catch (Exception e) {
            e.printStackTrace();
        }
        return new ResponseEntity<>( HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @Override
    public ResponseEntity<AdminRolesResDto> deleteRole(int roleId) {
        try {
            return adminRolesService.deleteRole(roleId);
        }catch (Exception e) {
            e.printStackTrace();
        }
        return new ResponseEntity<>( HttpStatus.INTERNAL_SERVER_ERROR);
    }

    ;



//    @Override
//    public ResponseEntity<String> addRoles(com.rs.listing.Restuarant.listing.pojo.AdminRoles req) {
//        return null;
//    }
}
