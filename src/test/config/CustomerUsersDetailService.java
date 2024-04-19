package com.rs.listing.Restuarant.listing.config;

import com.rs.listing.Restuarant.listing.pojo.AdminUsers;
import com.rs.listing.Restuarant.listing.repo.UsersRepo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Objects;

@Slf4j
@Service
public class CustomerUsersDetailService implements UserDetailsService {

    @Autowired
    UsersRepo usersRepo;

    private com.rs.listing.Restuarant.listing.pojo.AdminUsers userDetails;
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        userDetails = usersRepo.findByEmail(username);
        if(!Objects.isNull(userDetails)){
            return new User(userDetails.getEmail(), userDetails.getPassword(), new ArrayList<>());
        }else{
            throw new UsernameNotFoundException("user not found");
        }
    }

    public com.rs.listing.Restuarant.listing.pojo.AdminUsers getUserDetails() {return userDetails;}
}
