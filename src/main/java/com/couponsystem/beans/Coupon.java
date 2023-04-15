package com.couponsystem.beans;

import java.sql.Date;
import java.util.List;

import com.couponsystem.enums.CouponCategory;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Entity
@Table(name = "coupons")
@Data
@ToString(exclude = "customers")
@NoArgsConstructor
@AllArgsConstructor
public class Coupon {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(nullable = true)
    private int companyId;
    //	@ManyToOne(cascade = { CascadeType.DETACH, CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH })
//	@JoinColumn(name = "company_id")
//	private Company company;
    @Enumerated(EnumType.STRING)
    @Column(nullable = true)
    private CouponCategory category;
    @Column(nullable = true)
    private String title;
    @Column(nullable = true)
    private String description;
    @Column(nullable = true)
    private Date startDate;
    @Column(nullable = true)
    private Date endDate;
    @Column(nullable = true)
    private int amount;
    @Column(nullable = true)
    private double price;
    @Column(nullable = true)
    private String image;
    @ManyToMany(fetch = FetchType.LAZY, cascade = {CascadeType.DETACH, CascadeType.MERGE, CascadeType.REFRESH})
    @JoinTable(name = "customers_vs_coupons", joinColumns = @JoinColumn(name = "coupon_id"), inverseJoinColumns = @JoinColumn(name = "customer_id"))
    private List<Customer> customers;
}
