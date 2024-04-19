package com.hms.registrationservice.restImpl;

import com.hms.registrationservice.dto.FeaturesAllResDto;
import com.hms.registrationservice.dto.FeaturesReqDto;
import com.hms.registrationservice.dto.FeaturesResDto;
import com.hms.registrationservice.pojo.Features;
import com.hms.registrationservice.rest.FeaturesRest;
import com.hms.registrationservice.service.FeaturesService;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@Slf4j
public class FeaturesRestImpl implements FeaturesRest {
    Logger logger = LoggerFactory.getLogger(FeaturesRestImpl.class);
    @Autowired
    FeaturesService featuresService;
    @Override
    public ResponseEntity<FeaturesResDto> add(FeaturesReqDto features) {
        try{
            return featuresService.add(features);
        }catch (Exception e) {
            e.printStackTrace();
        }
        return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @Override
    public ResponseEntity<FeaturesResDto> getFeaturesById(int featureId) {
        try {
            return featuresService.getFeatureById(featureId);
        }catch (Exception e){
            e.printStackTrace();
        }
        return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @Override
    public ResponseEntity<FeaturesAllResDto> getFeatures() {
        try {
            return featuresService.getFeatures();
        }catch (Exception e) {
            e.printStackTrace();
        }
        return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @Override
    public ResponseEntity<FeaturesResDto> updateFeature(int featureId, FeaturesReqDto features) {
        try {
            return featuresService.update(featureId,features);
        }catch (Exception e){
            e.printStackTrace();
        }
        return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @Override
    public ResponseEntity<FeaturesResDto> deleteFeature(int featureId) {
       try {
          return featuresService.deleteFeature(featureId);
       }catch (Exception e){
           e.printStackTrace();
       }
        return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
