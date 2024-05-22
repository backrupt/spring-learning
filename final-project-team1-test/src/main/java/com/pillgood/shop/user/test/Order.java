package com.pillgood.shop.user.test;

import java.util.Date;

import com.pillgood.shop.user.entity.User;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@AllArgsConstructor
@Data
@NoArgsConstructor
@Entity
@Table(name = "orders")
public class Order{
    
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "orders_no_seq")
    @SequenceGenerator(name = "orders_no_seq", sequenceName = "orders_no_SEQ", allocationSize = 1,initialValue = 1)
    private Long no;
    private String name;
    private String phone;
    private Integer zipcode;
    private String address;
    private String addressDetail;
    private Date createdDate;
    private Integer price;
    @ManyToOne(fetch = FetchType.LAZY,cascade = CascadeType.ALL)
    @JoinColumn(name = "user_no")
    private User user;
    @OneToOne(fetch = FetchType.LAZY,cascade = CascadeType.ALL)
    @JoinColumn(name = "payment_no")
    private Payment payment;
    
}