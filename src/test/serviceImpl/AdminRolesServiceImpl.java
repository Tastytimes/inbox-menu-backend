package com.rs.listing.Restuarant.listing.serviceImpl;

import com.rs.listing.Restuarant.listing.DTO.AdminRolesReqDto;
import com.rs.listing.Restuarant.listing.DTO.AdminRolesResDto;
import com.rs.listing.Restuarant.listing.DTO.AdminRolesWrapper;
import com.rs.listing.Restuarant.listing.DTO.RolesWrapper;
import com.rs.listing.Restuarant.listing.pojo.AdminRoles;
import com.rs.listing.Restuarant.listing.repo.AdminRolesRepo;
import com.rs.listing.Restuarant.listing.service.AdminRolesService;
import com.sun.net.httpserver.Headers;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
@Slf4j
public class AdminRolesServiceImpl implements AdminRolesService {
    @Autowired
    AdminRolesRepo adminRolesRepo;
    Logger logger = LoggerFactory.getLogger(AdminRolesServiceImpl.class);
    @Override
    public ResponseEntity<AdminRolesResDto> addRoles(AdminRolesReqDto req) {
        try {
            AdminRolesResDto respDto = new AdminRolesResDto();
            logger.info("req {}", req);
            Optional<AdminRoles> adminDetail = adminRolesRepo.findByName(req.getName());
            logger.info("presented1 role {}", adminDetail   );
            logger.info("presented role {}", adminDetail.isPresent());
            if(adminDetail.isPresent()) {
                respDto.setStatus(404);
                respDto.setMessage("already role has bee added, please add another one");
                return new ResponseEntity<AdminRolesResDto>(respDto, HttpStatus.BAD_REQUEST);
            }else {
                respDto.setStatus(201);
                respDto.setMessage("Roles addedd successfully");
                AdminRoles roles = new AdminRoles();
                roles.setName(req.getName());
                roles.setStatus(req.getStatus());
                AdminRoles obj = adminRolesRepo.save(roles);
                logger.info("role {}", obj);
                return new ResponseEntity<AdminRolesResDto>(respDto, HttpStatus.OK);
            }
        }catch (Exception e) {
            e.printStackTrace();
        }
        return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @Override
    public ResponseEntity<AdminRolesWrapper> getAllRoles() {
       try {
            List<AdminRoles> roles = adminRolesRepo.findAll();
           AdminRolesWrapper wrap = new AdminRolesWrapper();
            wrap.setData(roles);
           wrap.setStatus(200);
           wrap.setMessage("data has fetched from db");
           return new ResponseEntity<AdminRolesWrapper>(wrap, HttpStatus.OK);
       }catch (Exception e) {
            e.printStackTrace();
       }
        return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @Override
    public ResponseEntity<RolesWrapper> getRole(int id) {
        try {
            logger.info("id {}",id );
            AdminRoles role = adminRolesRepo.findById(String.valueOf(id)).orElse(null);
            if(role !=null) {
                RolesWrapper wrap = new RolesWrapper();
                wrap.setStatus(200);
                wrap.setMessage("details fetched from db");
                wrap.setDetails(role);
                logger.info("details {}", role);
                logger.info("details {}", wrap);
                return new ResponseEntity<RolesWrapper>(wrap, HttpStatus.OK);
            }else {
                RolesWrapper wrap = new RolesWrapper();
                wrap.setStatus(404);
                wrap.setMessage("Role has not found");
                wrap.setDetails(null);
                return new ResponseEntity<RolesWrapper>(wrap, HttpStatus.NOT_FOUND);
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @Override
    public ResponseEntity<AdminRolesResDto> updateRole(int id, AdminRolesReqDto req) {
        try {
            AdminRoles role = adminRolesRepo.findById(String.valueOf(id)).orElse(null);
            AdminRolesResDto dto = new AdminRolesResDto();
            if(role !=null) {
                AdminRoles roles = new AdminRoles();
                roles.setName(req.getName());
                roles.setStatus(req.getStatus());
                roles.setId(id);
                logger.info("role {}", roles);
                adminRolesRepo.save(roles);
                dto.setStatus(200);
                dto.setMessage("roles has been updated successfully");
                return new ResponseEntity<AdminRolesResDto>(dto, HttpStatus.OK);
            }else {
                dto.setStatus(404);
                dto.setMessage("No roles found");
                return new ResponseEntity<AdminRolesResDto>(dto, HttpStatus.NOT_FOUND);
            }

        }catch (Exception e){
            e.printStackTrace();
        }
        return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @Override
    public ResponseEntity<AdminRolesResDto> deleteRole(int id) {
        try {
            AdminRoles role = adminRolesRepo.findById(String.valueOf(id)).orElse(null);
            AdminRolesResDto dto = new AdminRolesResDto();
            if(role !=null) {
                adminRolesRepo.deleteById(String.valueOf(id));
                dto.setStatus(200);
                dto.setMessage("Role has been deleted succefully");
                return new ResponseEntity<AdminRolesResDto>(dto, HttpStatus.OK);
            }else {
                dto.setMessage("record has not found");
                dto.setStatus(404);
                return new ResponseEntity<AdminRolesResDto>(dto, HttpStatus.NOT_FOUND);
            }

        }catch (Exception e) {
            e.printStackTrace();
        }
        return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
