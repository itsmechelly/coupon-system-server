package com.couponsystem.beans;

import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "companies")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Company {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(length = 45, unique = true, nullable = true)
    private String name;
    @Column(length = 45, unique = true, nullable = true)
    private String email;
    @Column(nullable = true)
    private String password;
    //TODO
//	@JsonIgnore
    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.EAGER)
//	@OneToMany(mappedBy = "company", fetch = FetchType.LAZY , cascade = {CascadeType.DETACH,CascadeType.MERGE,CascadeType.REFRESH,CascadeType.REMOVE})
    private List<Coupon> coupons = new ArrayList<>();
}
