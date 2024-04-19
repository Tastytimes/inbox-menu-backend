package com.hms.registrationservice.service;

import com.hms.registrationservice.dto.FeaturesAllResDto;
import com.hms.registrationservice.dto.FeaturesReqDto;
import com.hms.registrationservice.dto.FeaturesResDto;
import com.hms.registrationservice.pojo.Features;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;

public interface FeaturesService {
    ResponseEntity<FeaturesResDto> add(FeaturesReqDto feature);
    ResponseEntity<FeaturesResDto> getFeatureById(int featureId);
    ResponseEntity<FeaturesAllResDto> getFeatures();

    ResponseEntity<FeaturesResDto> update(int featureId,FeaturesReqDto feature);
    ResponseEntity<FeaturesResDto> deleteFeature(@PathVariable int featureId);
}
