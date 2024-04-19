package com.rs.listing.Restuarant.listing.repo;

import com.rs.listing.Restuarant.listing.pojo.OtpMaster;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class OtpRepo {

    @Autowired
    private RedisTemplate template;

    public OtpMaster generateOtp(OtpMaster otp) {
        try {
            template.opsForHash().put("OtpMaster", String.valueOf(otp.getId()), otp);
            return otp;
        } catch (Exception e) {
            // Log the object being stored for debugging purposes
            System.out.println("Error storing object in Redis: " + otp);
            e.printStackTrace();
            throw e;
        }
    }

    public List<OtpMaster> getAllOtp(){
        return template.opsForHash().values("OtpMaster");
    }

    public OtpMaster getById(int id){
        return (OtpMaster) template.opsForHash().get("OtpMaster", id);
    }

    public String deleteOtp(int id) {
          template.opsForHash().delete("OtpMaster", id);
        return "Otp is deleted successfully";
    }
}
