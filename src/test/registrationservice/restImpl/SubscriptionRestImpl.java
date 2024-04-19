package com.hms.registrationservice.restImpl;

import com.hms.registrationservice.dto.SubscriptionReqDto;
import com.hms.registrationservice.dto.SubscriptionResDto;
import com.hms.registrationservice.rest.SubscriptionRest;
import com.hms.registrationservice.service.SubscriptionService;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Slf4j
public class SubscriptionRestImpl implements SubscriptionRest {
    Logger logger = LoggerFactory.getLogger(FeaturesRestImpl.class);

    @Autowired
    SubscriptionService subscriptionService;

    @Override
    public ResponseEntity<SubscriptionResDto> add(SubscriptionReqDto data) {
        try {
            return subscriptionService.add(data);
        }catch (Exception e) {
            e.printStackTrace();
        }
        return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @Override
    public ResponseEntity<SubscriptionResDto> getSubscription(int subscriptionId) {
        try {
            return subscriptionService.getSubscription(subscriptionId);
        }catch (Exception e){
            e.printStackTrace();
        }
        return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @Override
    public ResponseEntity<SubscriptionResDto> updateSubscription(int subscriptionId, SubscriptionReqDto reqBody) {
        try {
            return subscriptionService.updateSubscription(subscriptionId,reqBody);
        }catch (Exception e){
            e.printStackTrace();
        }
        return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @Override
    public ResponseEntity<SubscriptionResDto> deleteSubscription(int subscriptionId) {
        try {
            return subscriptionService.deleteSubscription(subscriptionId);
        }catch (Exception e){
            e.printStackTrace();
        }
        return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
