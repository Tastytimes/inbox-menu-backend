package com.hms.registrationservice.rest;

import com.hms.registrationservice.dto.SubscriptionReqDto;
import com.hms.registrationservice.dto.SubscriptionResDto;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@Validated
@RequestMapping("/api-v1/subscription")
public interface SubscriptionRest {

    @PostMapping(path = "/add")
    public ResponseEntity<SubscriptionResDto> add(@Valid @RequestBody SubscriptionReqDto data);

    @GetMapping(path = "/{subscriptionId}")
    public ResponseEntity<SubscriptionResDto> getSubscription(@PathVariable int subscriptionId);

    @PutMapping(path = "/{subscriptionId}")
    public ResponseEntity<SubscriptionResDto> updateSubscription(@PathVariable int subscriptionId, @Valid @RequestBody SubscriptionReqDto reqBody);

    @DeleteMapping(path = "/{subscriptionId}")
    public ResponseEntity<SubscriptionResDto> deleteSubscription(@PathVariable int subscriptionId);
}
