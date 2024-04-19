package com.hms.registrationservice.serviceImpl;

import com.hms.registrationservice.dto.FeaturesResDto;
import com.hms.registrationservice.dto.SubscriptionDetailsDto;
import com.hms.registrationservice.dto.SubscriptionReqDto;
import com.hms.registrationservice.dto.SubscriptionResDto;
import com.hms.registrationservice.pojo.Features;
import com.hms.registrationservice.pojo.Subscription;
import com.hms.registrationservice.repo.FeaturesRepo;
import com.hms.registrationservice.repo.SubscriptionRepo;
import com.hms.registrationservice.service.SubscriptionService;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class SubscriptionServiceImpl implements SubscriptionService {

    @Autowired
    SubscriptionRepo repo;

    @Autowired
    FeaturesRepo featuresRepo;
    Logger logger = LoggerFactory.getLogger(SubscriptionServiceImpl.class);
    @Override
    public ResponseEntity<SubscriptionResDto> add(SubscriptionReqDto data) {
       try {
           Features feature = featuresRepo.findById(data.getFeatureId()).orElse(null);
           SubscriptionResDto dto = new SubscriptionResDto();
           if(feature == null) {
               dto.setMessage("Feature didn't find");
               dto.setStatus(400);
               return new ResponseEntity<>(dto, HttpStatus.BAD_REQUEST);
           }else{
               Subscription subscription = new Subscription();
               subscription.setName(data.getName());
               subscription.setStatus(data.getStatus());
               subscription.setFeatures(feature);
               subscription.setPrice(data.getPrice());
               repo.save(subscription);
               dto.setMessage("Subscription has been added");
               dto.setStatus(201);
               return new ResponseEntity<>(dto, HttpStatus.CREATED);
           }

       }catch (Exception e){
           e.printStackTrace();
       }
        return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @Override
    public ResponseEntity<SubscriptionResDto> getSubscription(int subscriptionId) {
        try {
            Subscription res = repo.findById(subscriptionId).orElse(null);
            SubscriptionResDto dto = new SubscriptionResDto();
            SubscriptionDetailsDto details = new SubscriptionDetailsDto();
            if(res == null){
                dto.setMessage("subscription Not found");
                dto.setStatus(404);
                return new ResponseEntity<SubscriptionResDto>(dto, HttpStatus.NOT_FOUND);
            }else {
                Features features = featuresRepo.findById(res.getId()).orElse(null);
                if(features !=null){
                    details.setName(res.getName());
                    details.setStatus(res.getStatus());
                    details.setPrice(res.getPrice());
                    details.setFeature(features.getName());
                    dto.setStatus(200);
                    dto.setMessage("data fetched successfully");
                    dto.setData(details);
                    return new ResponseEntity<SubscriptionResDto>(dto, HttpStatus.OK);
                }else{
                    dto.setStatus(404);
                    dto.setMessage("Something went wrong!");
                    return new ResponseEntity<SubscriptionResDto>(dto, HttpStatus.BAD_REQUEST);
                }

            }

        }catch (Exception e) {
            e.printStackTrace();
        }
        return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @Override
    public ResponseEntity<SubscriptionResDto> updateSubscription(int subscriptionId, SubscriptionReqDto reqBody) {
        try {
            Subscription res = repo.findById(subscriptionId).orElse(null);
            SubscriptionResDto dto = new SubscriptionResDto();
            SubscriptionReqDto req = new SubscriptionReqDto();
            if(res ==null){
                dto.setMessage("subscription Not found");
                dto.setStatus(404);
                return new ResponseEntity<SubscriptionResDto>(dto, HttpStatus.NOT_FOUND);
            }else{
                Features features = featuresRepo.findById(reqBody.getFeatureId()).orElse(null);
                if(features != null){
                    Subscription subscription = new Subscription();
                    subscription.setName(reqBody.getName());
                    subscription.setId(subscriptionId);
                    subscription.setStatus(reqBody.getStatus());
                    subscription.setFeatures(features);
                    subscription.setPrice(reqBody.getPrice());
                    repo.save(subscription);
                    dto.setStatus(200);
                    dto.setMessage("data fetched successfully");
                    return new ResponseEntity<SubscriptionResDto>(dto, HttpStatus.OK);
                }else{
                    dto.setStatus(404);
                    dto.setMessage("Something went wrong!");
                    return new ResponseEntity<SubscriptionResDto>(dto, HttpStatus.BAD_REQUEST);
                }
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @Override
    public ResponseEntity<SubscriptionResDto> deleteSubscription(int subscriptionId) {
        try {
            Subscription res = repo.findById(subscriptionId).orElse(null);
            SubscriptionResDto dto = new SubscriptionResDto();
            if(res != null){
                repo.deleteById(subscriptionId);
                dto.setStatus(200);
                dto.setMessage("subscription has been deleted successfully");
                return new ResponseEntity<SubscriptionResDto>(dto, HttpStatus.OK);
            }else{
                dto.setMessage("subscription Not found");
                dto.setStatus(404);
                return new ResponseEntity<SubscriptionResDto>(dto, HttpStatus.NOT_FOUND);
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
