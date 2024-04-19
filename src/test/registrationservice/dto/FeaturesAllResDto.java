package com.hms.registrationservice.dto;

import com.hms.registrationservice.pojo.Features;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class FeaturesAllResDto extends FeaturesResDto {

    private List<Features> featuresData;


}
