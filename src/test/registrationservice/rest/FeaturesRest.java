package com.hms.registrationservice.rest;

import com.hms.registrationservice.dto.FeaturesAllResDto;
import com.hms.registrationservice.dto.FeaturesReqDto;
import com.hms.registrationservice.dto.FeaturesResDto;
import com.hms.registrationservice.pojo.Features;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Validated
@RequestMapping(path = "/api-v1/features")
public interface FeaturesRest {

    @PostMapping(path = "/add")
    public ResponseEntity<FeaturesResDto> add(@Valid @RequestBody FeaturesReqDto features);

    @GetMapping(path = "/{featureId}")
    public ResponseEntity<FeaturesResDto> getFeaturesById(@PathVariable int featureId);

    @GetMapping(path = "/all-features")
    public ResponseEntity<FeaturesAllResDto> getFeatures();

    @PutMapping(path = "/{featureId}")
    public ResponseEntity<FeaturesResDto> updateFeature(@PathVariable int featureId, @RequestBody FeaturesReqDto features);

    @DeleteMapping(path = "/{featureId}")
    public ResponseEntity<FeaturesResDto> deleteFeature(@PathVariable int featureId);
}
