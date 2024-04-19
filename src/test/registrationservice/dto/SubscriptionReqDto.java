package com.hms.registrationservice.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class SubscriptionReqDto {
    private String name;
    private Boolean status;
    private double price;
    private int featureId;
}
