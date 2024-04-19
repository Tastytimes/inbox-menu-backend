package com.hms.registrationservice.dto;

import com.hms.registrationservice.pojo.Features;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Optional;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class FeaturesResDto {
    private String message;
    private int status;
    private Optional<Features> data;

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

    public Optional<Features> getData() {
        return data;
    }

    public void setData(Features data) {
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
