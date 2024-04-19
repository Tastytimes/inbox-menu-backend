package com.hms.registrationservice.dto;

import com.hms.registrationservice.pojo.Features;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Optional;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class FeaturesReqDto {

    private int id;
    private String name;
    private Boolean status;


}
