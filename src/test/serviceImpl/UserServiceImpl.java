package com.rs.listing.Restuarant.listing.serviceImpl;

import com.rs.listing.Restuarant.listing.DTO.*;
import com.rs.listing.Restuarant.listing.config.CustomerUsersDetailService;
import com.rs.listing.Restuarant.listing.config.JWTFilter;
import com.rs.listing.Restuarant.listing.config.JWTUtill;
import com.rs.listing.Restuarant.listing.pojo.AdminRoles;
import com.rs.listing.Restuarant.listing.pojo.AdminUsers;
import com.rs.listing.Restuarant.listing.repo.AdminRolesRepo;
import com.rs.listing.Restuarant.listing.repo.UsersRepo;
import com.rs.listing.Restuarant.listing.rest.AdminUser;
import com.rs.listing.Restuarant.listing.service.UserService;
import com.rs.listing.Restuarant.listing.utill.ExtractRoleName;
import com.rs.listing.Restuarant.listing.utill.PasswordGenerator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class UserServiceImpl implements UserService {
    Logger logger = LoggerFactory.getLogger(UserServiceImpl.class);
    @Autowired
    AdminRolesRepo adminRolesRepo;
    @Autowired
    UsersRepo usersRepo;

    @Autowired
    AuthenticationManager authenticationManager;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    JWTUtill jwtUtill;
    @Autowired
    JWTFilter jwtFilter;

    @Autowired
    CustomerUsersDetailService customerUsersDetailsService;

    @Autowired
    private JavaMailSender mailSender;


    @Override
    public ResponseEntity<AdminRolesResDto> addUser(UsersReqDto reqBody) {
        try {
//            String currentRole = jwtFilter.getRole();
//            sendMail();
            AdminRolesResDto dto = new AdminRolesResDto();
//            if(currentRole == "super_admin" && currentRole == "admin") {
                String roleName = reqBody.getRole();
                AdminRoles roles = adminRolesRepo.findByName(roleName).orElse(null);
                String randomPassword = PasswordGenerator.generateRandomPassword(8);
                logger.info("password {}", randomPassword);
                if(roles == null) {
                    dto.setStatus(404);
                    dto.setMessage("Role didn't find");
                    return new ResponseEntity<AdminRolesResDto>(dto,HttpStatus.BAD_REQUEST);
                }
                else {
                    AdminUsers user1 = new AdminUsers();
                    user1.setFullName(reqBody.getName());
                    user1.setEmail(reqBody.getEmail());
                    user1.setAdminRoles(roles);
                    user1.setPassword(passwordEncoder.encode(randomPassword));
                    user1.setIsFirstTimeLogin(true);
                    user1.setStatus(reqBody.getStatus());
                    AdminUsers resp = usersRepo.save(user1);
                    dto.setStatus(200);
                    dto.setMessage("user is created successfully");
                    return new ResponseEntity<AdminRolesResDto>(dto,HttpStatus.OK);
                }
//            }else{
//                dto.setStatus(404);
//                dto.setMessage("User dont have enough access to create user");
//                return new ResponseEntity<AdminRolesResDto>(dto,HttpStatus.BAD_REQUEST);
//            }
// Create AdminUsers objects
        }catch (Exception e) {
            e.printStackTrace();
        }
        return new ResponseEntity<>( HttpStatus.INTERNAL_SERVER_ERROR);
    }

    public void sendMail(){
        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom("manitindi@gmail.com");
        message.setTo("muraliagnihotri07@gmail.com");
        message.setText("test mail");
        message.setSubject("test mail from spring booot");
        mailSender.send(message);
        logger.info("mail send successfully {}", mailSender);
    }

    @Override
    public ResponseEntity<LoginResDto> login(LoginReqDto reqBody) {
        logger.info("reqBody {}", reqBody);
        try{
            LoginResDto dto = new LoginResDto();
            Authentication auth = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(reqBody.getEmail(), reqBody.getPassword()));
            if(auth.isAuthenticated()){
                if (customerUsersDetailsService.getUserDetails().getStatus() == true){
                    String generatedToken = jwtUtill.generateToken(customerUsersDetailsService.getUserDetails().getEmail(),customerUsersDetailsService.getUserDetails().getAdminRoles().getName() );
                    dto.setStatus(200);
                    dto.setMessage("Login Successful");
                    dto.setToken(generatedToken);
                    dto.setFirstTimeLogin(customerUsersDetailsService.getUserDetails().getFirstTimeLogin());
                    return new ResponseEntity<LoginResDto>(dto, HttpStatus.OK);
                }else {
                    dto.setStatus(404);
                    dto.setMessage("user is not active. Please contact Adminstrator");
                    return new ResponseEntity<LoginResDto>(dto, HttpStatus.BAD_REQUEST);
                }

            }else {
                dto.setStatus(404);
                dto.setMessage("Password did not match");
                return new ResponseEntity<LoginResDto>(dto, HttpStatus.BAD_REQUEST);
            }
        }catch (Exception e) {
            e.printStackTrace();
        }
        return new ResponseEntity<>( HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @Override
    public ResponseEntity<List<UsersWrapper>> getAllUsers() {
        try{
            String role = jwtFilter.getRole();
            AdminUsersResDto dto = new AdminUsersResDto();
            if("super_admin".equals(role) || "admin".equals(role) ) {
                List<AdminUsers> users = usersRepo.findAll();
                List<UsersWrapper> wrappers = new ArrayList<>();
                logger.info("users {}", users);
                for ( AdminUsers listUsers: users){
                    UsersWrapper wrap = new UsersWrapper();
                    wrap.setId(listUsers.getId());
                    wrap.setFullName(listUsers.getFullName());
                    wrap.setEmail(listUsers.getEmail());
                    wrap.setStatus(listUsers.getStatus());
                    String roleName = ExtractRoleName.getRolename(listUsers.getAdminRoles().toString());
                    wrap.setRole(roleName);
// Print or use the extracted role name
                    System.out.println("Role Name: " + roleName);
                    wrappers.add(wrap);
                }
                logger.info("wrapper {}", wrappers);
                dto.setStatus(200);
                dto.setMessage("Fetched the all users");
                dto.setUsers(users);
                logger.info("dto {}", dto);
                return new ResponseEntity<List<UsersWrapper>>(wrappers, HttpStatus.OK);
            }else {
                dto.setStatus(401);
                dto.setMessage("Dont have enough accessp");
                return new ResponseEntity<>( HttpStatus.UNAUTHORIZED);
            }

        }catch (Exception e) {
            e.printStackTrace();
        }
        return new ResponseEntity<>( HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @Override
    public ResponseEntity<UsersWrapper> getUserById(int userId) {
        try{
            AdminUsers user = usersRepo.findById(userId).orElse(null);
            if(user == null){
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }else {
                UsersWrapper wrap = new UsersWrapper();
                wrap.setId(user.getId());
                wrap.setEmail(user.getEmail());
                wrap.setFullName(user.getFullName());
                wrap.setStatus(user.getStatus());
                String role = ExtractRoleName.getRolename(user.getAdminRoles().toString());
                wrap.setRole(role);
                return new ResponseEntity<UsersWrapper>(wrap, HttpStatus.OK);
            }
        }catch (Exception e) {
            e.printStackTrace();
        }
        return new ResponseEntity<>( HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @Override
    public ResponseEntity<AdminRolesResDto> updateUser(int userId, UsersReqDto reqBody) {
        try{
            AdminRolesResDto dto = new AdminRolesResDto();
            AdminUsers userDetails = usersRepo.findById(userId).orElse(null);
            String roleName = reqBody.getRole();
            AdminRoles roles = adminRolesRepo.findByName(roleName).orElse(null);
            if(roles == null) {
                dto.setStatus(404);
                dto.setMessage("Role not found");
                return new ResponseEntity<AdminRolesResDto>(dto, HttpStatus.NOT_FOUND);
            }
        if(userDetails == null) {
            dto.setStatus(404);
            dto.setMessage("user not found");
            return new ResponseEntity<AdminRolesResDto>(dto, HttpStatus.NOT_FOUND);
        }else{
//            userDetails.setId(userId);
            userDetails.setEmail(reqBody.getEmail());
            userDetails.setStatus(reqBody.getStatus());
            userDetails.setFullName(reqBody.getName());
            userDetails.setAdminRoles(roles);
            logger.info("user details {}", userDetails);
            usersRepo.save(userDetails);
            dto.setStatus(200);
            dto.setMessage("user details has been updated");
            return new ResponseEntity<AdminRolesResDto>(dto, HttpStatus.OK);
        }
        }catch (Exception e) {
            e.printStackTrace();
        }
        return new ResponseEntity<>( HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @Override
    public ResponseEntity<AdminRolesResDto> updatePassword(Map<String, String> requestMap) {
       try {
           AdminRolesResDto dto = new AdminRolesResDto();
           logger.info("email {}", requestMap.get("email"));
           AdminUsers user = usersRepo.findByEmail(requestMap.get("email"));
           logger.info("user {}", user);
           if(Objects.isNull(user)) {
               dto.setMessage("user has not found");
               dto.setStatus(404);
               return new ResponseEntity<AdminRolesResDto>(dto,HttpStatus.NOT_FOUND);
           }else {
               String password = requestMap.get("password");
               String encodedPassword = passwordEncoder.encode(password);
               usersRepo.updatePasswordByEmail(requestMap.get("email"), encodedPassword);
               dto.setMessage("Password changed successfully");
               dto.setStatus(200);
               return new ResponseEntity<AdminRolesResDto>(dto,HttpStatus.OK);
           }

//           String randomPassword = PasswordGenerator.generateRandomPassword(8);
//           logger.info("generate password {}", randomPassword);
//           String encodedPassword = passwordEncoder.encode(randomPassword);
//           user.setPassword(encodedPassword);
//           logger.info("encode {}",encodedPassword );
//           usersRepo.updatePasswordByEmail(requestMap.get("email"), encodedPassword);
//           return new ResponseEntity<>("updated", HttpStatus.OK);
       }
       catch (Exception e) {
           e.printStackTrace();
       }
        return new ResponseEntity<>( HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @Override
    public ResponseEntity<AdminRolesResDto> deleteUserById(int userId) {
        try{
            AdminRolesResDto dto = new AdminRolesResDto();
            String currentRole = jwtFilter.getRole();
            logger.info("role {}", currentRole);
            if("super_admin".equals(currentRole) || "admin".equals(currentRole)) {
                AdminUsers user = usersRepo.findById(userId).orElse(null);
                if(user == null ){
                    dto.setStatus(404);
                    dto.setMessage("User not found");
                    return new ResponseEntity<AdminRolesResDto>(dto, HttpStatus.NOT_FOUND);
                }else {
                    usersRepo.deleteById(userId);
                    dto.setStatus(200);
                    dto.setMessage("user has been deleted from the records");
                    return new ResponseEntity<AdminRolesResDto>(dto, HttpStatus.OK);
                }
            }else {
                dto.setStatus(401);
                dto.setMessage("Dont have enough access to delete the user");
                return new ResponseEntity<AdminRolesResDto>(dto, HttpStatus.UNAUTHORIZED);
            }

        }catch (Exception e) {
            e.printStackTrace();
        }
        return new ResponseEntity<>( HttpStatus.INTERNAL_SERVER_ERROR);
    }

//    @Override
//    public ResponseEntity<AdminRolesResDto> updateUser(int userId, UsersReqDto reqBody) {
//        try {
//            AdminUsers userDetails = usersRepo.findById(userId).orElse(null);
//            AdminRolesResDto dto = new AdminRolesResDto();
//            String roleName = reqBody.getRole();
//            AdminRoles roles = adminRolesRepo.findByName(roleName).orElse(null);
//            if(userDetails == null && roles ==null) {
//                dto.setStatus(404);
//                dto.setMessage("user or roles didn't find");
//                return new ResponseEntity<AdminRolesResDto>(dto, HttpStatus.NOT_FOUND);
//            }else {
//                AdminUsers user = new AdminUsers();
//                user.setEmail(reqBody.getEmail());
//                user.setFullName(reqBody.getName());
//                user.setStatus(reqBody.getStatus());
//                user.setId(reqBody.getId());
//                user.setAdminRoles(roles);
//                usersRepo.findB
//                dto.setStatus(200);
//                dto.setMessage("user details has been updated");
//                return new ResponseEntity<AdminRolesResDto>(dto, HttpStatus.OK);
//            }
//        }catch (Exception e){
//            e.printStackTrace();
//        }
//        return new ResponseEntity<>( HttpStatus.INTERNAL_SERVER_ERROR);
//    }

//    private static String extractRoleName(String role) {
//        // Find the index of the role name within the string
//        int nameIndex = role.indexOf("name=");
//
//        // Find the start index of the role name
//        int startIndex = nameIndex + 5; // "name=".length()
//
//        // Find the end index of the role name (assuming it ends with a comma)
//        int endIndex = role.indexOf(",", startIndex);
//
//        // Extract the role name substring
//        String roleName = role.substring(startIndex, endIndex);
//
//        // Return the extracted role name
//        return roleName;
//    }
}
