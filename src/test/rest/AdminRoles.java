package com.rs.listing.Restuarant.listing.rest;

import com.rs.listing.Restuarant.listing.DTO.AdminRolesReqDto;
import com.rs.listing.Restuarant.listing.DTO.AdminRolesResDto;
import com.rs.listing.Restuarant.listing.DTO.AdminRolesWrapper;
import com.rs.listing.Restuarant.listing.DTO.RolesWrapper;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@Validated
@RequestMapping(path="/api-v1/roles")
public interface AdminRoles {

    @PostMapping(path = "/add")
    public ResponseEntity<AdminRolesResDto> addRoles(@Valid  @RequestBody AdminRolesReqDto req);

    @GetMapping(path = "/get-roles")
    public ResponseEntity<AdminRolesWrapper> getAllRoles();

    @GetMapping(path = "/{roleId}")
    public ResponseEntity<RolesWrapper> getRole(@PathVariable int roleId);

    @PutMapping(path = "/{roleId}")
    public ResponseEntity<AdminRolesResDto> updateRole(@PathVariable int roleId, @RequestBody AdminRolesReqDto req);
    @DeleteMapping(path = "/{roleId}")
    public ResponseEntity<AdminRolesResDto> deleteRole(@PathVariable int roleId);
}
