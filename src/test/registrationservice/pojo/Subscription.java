package com.hms.registrationservice.pojo;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Subscription implements Serializable {

    private static final long seerialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String name;
    private double price;
    private Boolean status;

    @ManyToOne( fetch = FetchType.LAZY)
    @JoinColumn(name = "feature_id")
    private Features features;
}
