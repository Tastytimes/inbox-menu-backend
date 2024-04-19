package com.rs.listing.Restuarant.listing.repo;

import com.rs.listing.Restuarant.listing.pojo.AdminRoles;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AdminRolesRepo extends JpaRepository<AdminRoles, String> {
    Optional<AdminRoles> findByName(String name);
}
