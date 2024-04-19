package com.hms.registrationservice.serviceImpl;

import com.hms.registrationservice.dto.FeaturesAllResDto;
import com.hms.registrationservice.dto.FeaturesReqDto;
import com.hms.registrationservice.dto.FeaturesResDto;
import com.hms.registrationservice.pojo.Features;
import com.hms.registrationservice.repo.FeaturesRepo;
import com.hms.registrationservice.service.FeaturesService;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@Slf4j
public class FeaturesServiceImpl implements FeaturesService {

    Logger logger = LoggerFactory.getLogger(FeaturesServiceImpl.class);
    @Autowired
    FeaturesRepo repo;

    @Override
    public ResponseEntity<FeaturesResDto> add(FeaturesReqDto feature) {
        try{
            Features reqBody = new Features();
            FeaturesResDto dto = new FeaturesResDto();
            reqBody.setName(feature.getName());
            reqBody.setStatus(feature.getStatus());
            repo.save(reqBody);
            dto.setMessage("features added successfully");
            dto.setStatus(201);
            return new ResponseEntity<FeaturesResDto>(dto, HttpStatus.CREATED);
        }
        catch (Exception e){
            e.printStackTrace();
        }
        return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @Override
    public ResponseEntity<FeaturesResDto> getFeatureById(int featureId) {
        try{
            FeaturesResDto dto = new FeaturesResDto();
            Features details = repo.findById(featureId).orElse(null);;
            if(details!=null){
                dto.setMessage("data fetched successfully");
                dto.setStatus(200);
                dto.setData(details);
                return new ResponseEntity<FeaturesResDto>(dto, HttpStatus.OK);
            }else{
                dto.setMessage("features Not found");
                dto.setStatus(404);
                return new ResponseEntity<FeaturesResDto>(dto, HttpStatus.NOT_FOUND);
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @Override
    public ResponseEntity<FeaturesAllResDto> getFeatures() {
        try {
            List<Features> feature = repo.findAll();
            logger.info("data {}", feature);
            FeaturesAllResDto dto = new FeaturesAllResDto();
            dto.setMessage("All features has successfully fetched");
            dto.setStatus(200);
            dto.setFeaturesData(feature);
            return new ResponseEntity<FeaturesAllResDto>(dto, HttpStatus.OK);
        }catch (Exception e) {
            e.printStackTrace();
        }
        return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @Override
    public ResponseEntity<FeaturesResDto> update(int featureId, FeaturesReqDto feature) {
        try {
            Features features = repo.findById(featureId).orElse(null);
            FeaturesResDto dto = new FeaturesResDto();
            if(features != null) {
                Features req = new Features();
                req.setName(feature.getName());
                req.setStatus(feature.getStatus());
                req.setId(featureId);
                repo.save(req);
                dto.setMessage("Fetaure has updated sucessfully");
                dto.setStatus(200);
                return new ResponseEntity<FeaturesResDto>(dto, HttpStatus.OK);
            }else {
                dto.setMessage("features Not found");
                dto.setStatus(404);
                return new ResponseEntity<FeaturesResDto>(dto, HttpStatus.NOT_FOUND);
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @Override
    public ResponseEntity<FeaturesResDto> deleteFeature(int featureId) {
        try {
            Features features = repo.findById(featureId).orElse(null);
            FeaturesResDto dto = new FeaturesResDto();
            if(features !=null) {
                repo.deleteById(featureId);
                dto.setMessage("Fetaure has been deleted");
                dto.setStatus(200);
                return new ResponseEntity<FeaturesResDto>(dto, HttpStatus.OK);
            }else{
                dto.setMessage("features Not found");
                dto.setStatus(404);
                return new ResponseEntity<FeaturesResDto>(dto, HttpStatus.NOT_FOUND);
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
