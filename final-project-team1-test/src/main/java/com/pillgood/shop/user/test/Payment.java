package com.pillgood.shop.user.test;


import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToOne;
import jakarta.persistence.SequenceGenerator;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@Builder
public class Payment {
    
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "payment_no_seq")
    @SequenceGenerator(name = "payment_no_seq", sequenceName = "payment_no_seq", allocationSize = 1,initialValue = 1)
    private Long no;
    private String paymentKey;
    private Integer orderNumber;
    private Integer orderName;
    private String method;
    private Integer totalAmount;
    private String status;
    private String requestedAt;
    private String approvedAt;
    private String type;
    @OneToOne(mappedBy = "payment" ,fetch = FetchType.LAZY,cascade = CascadeType.ALL)
    private Order order;
    
}