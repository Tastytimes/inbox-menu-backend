package com.hms.registrationservice.pojo;

import com.netflix.discovery.provider.ISerializer;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
public class Features implements Serializable {

    private static final long seerialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @NotEmpty
    private String name;
    @NotNull
    private Boolean status;

    @OneToMany(mappedBy="features", cascade = CascadeType.ALL,fetch = FetchType.LAZY)
    private List<Subscription> subscriptionList = new ArrayList<>();


}
