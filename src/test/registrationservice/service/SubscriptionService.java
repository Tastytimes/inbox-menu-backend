package com.hms.registrationservice.service;

import com.hms.registrationservice.dto.SubscriptionReqDto;
import com.hms.registrationservice.dto.SubscriptionResDto;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

public interface SubscriptionService {
    ResponseEntity<SubscriptionResDto> add(@Valid @RequestBody SubscriptionReqDto data);
    public ResponseEntity<SubscriptionResDto> getSubscription(@PathVariable int subscriptionId);
    public ResponseEntity<SubscriptionResDto> updateSubscription(@PathVariable int subscriptionId, @Valid @RequestBody SubscriptionReqDto reqBody);
    public ResponseEntity<SubscriptionResDto> deleteSubscription(@PathVariable int subscriptionId);
}
