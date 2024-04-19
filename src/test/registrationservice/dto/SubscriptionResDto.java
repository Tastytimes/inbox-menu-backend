package com.hms.registrationservice.dto;

import com.hms.registrationservice.pojo.Features;
import com.hms.registrationservice.pojo.Subscription;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Optional;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SubscriptionResDto {
    private String message;
    private int status;
    private Optional<SubscriptionDetailsDto> data;

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public Optional<SubscriptionDetailsDto> getData() {
        return data;
    }

    public void setData(SubscriptionDetailsDto data) {
        this.data = Optional.ofNullable(data);
    }

    @Override
    public String toString() {
        return "FeaturesResDto{" +
                "message='" + message + '\'' +
                ", status=" + status +
                ", data=" + data +
                '}';
    }
}
