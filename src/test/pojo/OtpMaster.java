package com.rs.listing.Restuarant.listing.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.redis.core.RedisHash;

@Data
@NoArgsConstructor
@AllArgsConstructor
@RedisHash("OtpMaster")
public class OtpMaster {
    @Id
    private int id;
    private int otp;
    private String otpType;

}
