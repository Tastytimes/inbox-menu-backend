package com.rs.listing.Restuarant.listing.restImpl;

import com.rs.listing.Restuarant.listing.DTO.*;
import com.rs.listing.Restuarant.listing.pojo.OtpMaster;
import com.rs.listing.Restuarant.listing.repo.OtpRepo;
import com.rs.listing.Restuarant.listing.rest.AdminUser;
import com.rs.listing.Restuarant.listing.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@RestController
@Slf4j
public class UserRestImpl implements AdminUser {
    @Autowired
   UserService userService;

    @Autowired
    OtpRepo otpRepo;


    @Override
    public ResponseEntity<AdminRolesResDto> addUser(UsersReqDto reqBody) {
        try{
            return userService.addUser(reqBody);
        }catch (Exception e){
            e.printStackTrace();
        }
        return new ResponseEntity<>( HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @Override
    public ResponseEntity<LoginResDto> login(LoginReqDto reqBody) {
        try{
            return userService.login(reqBody);
        }catch (Exception e){
            e.printStackTrace();
        }
        return new ResponseEntity<>( HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @Override
    public ResponseEntity<List<UsersWrapper>> getAllUsers() {
        try{
            return userService.getAllUsers();
        }catch (Exception e){
            e.printStackTrace();
        }
        return new ResponseEntity<>( HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @Override
    public ResponseEntity<UsersWrapper> getUserById(int userId) {
        try{
            return userService.getUserById(userId);
        }catch (Exception e){
            e.printStackTrace();
        }
        return new ResponseEntity<>( HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @Override
    public ResponseEntity<AdminRolesResDto> updateUser(int userId, UsersReqDto reqBody) {
        try{
            return userService.updateUser(userId,reqBody);
        }catch (Exception e){
            e.printStackTrace();
        }
        return new ResponseEntity<>( HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @Override
    public ResponseEntity<AdminRolesResDto> updatePassword(Map<String, String> requestMap) {
        try{
            return userService.updatePassword(requestMap);
        }catch (Exception e){
            e.printStackTrace();
        }
        return new ResponseEntity<>( HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @Override
    public ResponseEntity<AdminRolesResDto> deleteUserById(int userId) {
        try{
            return userService.deleteUserById(userId);
        }catch (Exception e){
            e.printStackTrace();
        }
        return new ResponseEntity<>( HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @Override
    public ResponseEntity<OtpMaster> generate(OtpMaster otp) {
        try {
            OtpMaster details = otpRepo.generateOtp(otp);
            return new ResponseEntity<OtpMaster>(details, HttpStatus.OK);
        }
        catch (Exception e){
            e.printStackTrace();
        }
        return  new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @Override
    public ResponseEntity<List<OtpMaster>> getOtp() {
        try{
            List<OtpMaster> allOtp = otpRepo.getAllOtp();
            return new ResponseEntity<List<OtpMaster>>(allOtp, HttpStatus.OK);
        }catch (Exception e) {
            e.printStackTrace();
        }
        return new ResponseEntity<List<OtpMaster>>(new ArrayList<>(),HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
