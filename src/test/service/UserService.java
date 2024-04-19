package com.rs.listing.Restuarant.listing.service;

import com.rs.listing.Restuarant.listing.DTO.*;
import com.rs.listing.Restuarant.listing.pojo.AdminUsers;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

public interface UserService {
    ResponseEntity<AdminRolesResDto> addUser(UsersReqDto reqBody);
    ResponseEntity<LoginResDto> login(LoginReqDto reqBody);

    ResponseEntity<List<UsersWrapper>> getAllUsers();

    ResponseEntity<UsersWrapper> getUserById(int userId);
    ResponseEntity<AdminRolesResDto> updateUser(int userId,UsersReqDto reqBody );
    ResponseEntity<AdminRolesResDto> updatePassword(Map<String, String> requestMap);
    ResponseEntity<AdminRolesResDto> deleteUserById(int userId);
}
