package com.rs.listing.Restuarant.listing.rest;

import com.rs.listing.Restuarant.listing.DTO.*;
import com.rs.listing.Restuarant.listing.pojo.AdminUsers;
import com.rs.listing.Restuarant.listing.pojo.OtpMaster;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@Validated
@RequestMapping(path = "/api-v1/admin-users")
public interface AdminUser {
    @PostMapping(path = "/add")
    public ResponseEntity<AdminRolesResDto> addUser(@Validated @RequestBody UsersReqDto reqBody);

    @PostMapping(path = "/login")
    public ResponseEntity<LoginResDto> login(@Validated @RequestBody LoginReqDto reqBody);

    @GetMapping(path = "all-users")
    public ResponseEntity<List<UsersWrapper>> getAllUsers();

    @GetMapping(path = "/{userId}")
    public ResponseEntity<UsersWrapper> getUserById(@PathVariable int userId);

    @PutMapping(path = "/{userId}")
    public ResponseEntity<AdminRolesResDto> updateUser(@PathVariable int userId, @RequestBody UsersReqDto reqBody);

    @PostMapping(path = "/reset-password")
    public ResponseEntity<AdminRolesResDto> updatePassword(@RequestBody Map<String, String> requestMap);

    @DeleteMapping(path = "/{userId}")
    public ResponseEntity<AdminRolesResDto> deleteUserById(@PathVariable int userId);

    @PostMapping(path = "/generate-otp")
    public ResponseEntity<OtpMaster> generate(@RequestBody OtpMaster otp);

    @GetMapping(path = "/get-otp")
    public ResponseEntity<List<OtpMaster>> getOtp();
}
