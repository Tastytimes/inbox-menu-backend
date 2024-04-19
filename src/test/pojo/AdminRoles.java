package com.rs.listing.Restuarant.listing.pojo;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class AdminRoles implements Serializable {
    private static final long seerialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @NotEmpty
    private String name;
    @NotNull
    private Boolean status;

    @OneToMany(mappedBy = "adminRoles", cascade = CascadeType.ALL,fetch = FetchType.LAZY)
    private List<AdminUsers> adminUsersList = new ArrayList<>();
}
