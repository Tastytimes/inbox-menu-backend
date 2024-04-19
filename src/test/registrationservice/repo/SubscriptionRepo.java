package com.hms.registrationservice.repo;

import com.hms.registrationservice.pojo.Subscription;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SubscriptionRepo extends JpaRepository<Subscription, Integer> {
}
