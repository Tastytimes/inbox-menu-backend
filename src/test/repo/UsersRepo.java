package com.rs.listing.Restuarant.listing.repo;

import com.rs.listing.Restuarant.listing.pojo.AdminUsers;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestBody;

@Repository
public interface UsersRepo extends JpaRepository<AdminUsers, Integer> {

    AdminUsers findByEmail(String email);

     @Transactional
    @Modifying
    @Query(value = "UPDATE admin_users u SET u.password = :password WHERE u.email = :email", nativeQuery = true)
    void updatePasswordByEmail( String email,  String password);




    //    void updatePassword1(@Param("email") String email, @Param("password") String password,@Param("isFirstTimeLogin") boolean isFirstTimeLogin);



}
