package com.hms.registrationservice.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class SubscriptionDetailsDto {
    private int id;
    private String name;
    private double price;
    private Boolean status;
    private String feature;
}
