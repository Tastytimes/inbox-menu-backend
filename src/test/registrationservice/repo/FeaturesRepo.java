package com.hms.registrationservice.repo;

import com.hms.registrationservice.pojo.Features;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FeaturesRepo extends JpaRepository<Features, Integer> {

}
