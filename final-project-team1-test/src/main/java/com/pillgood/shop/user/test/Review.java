package com.pillgood.shop.user.test;

import java.util.Date;

import org.hibernate.annotations.CreationTimestamp;

import com.pillgood.shop.user.entity.User;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.SequenceGenerator;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@NoArgsConstructor
@Entity
@Builder
@AllArgsConstructor
public class Review {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "review_no_seq")
    @SequenceGenerator(name = "review_no_seq", sequenceName = "review_no_seq", allocationSize = 1,initialValue = 1)
    private Long no;
    private String content;
    private Integer star;
    private Integer type;
    @CreationTimestamp
    private Date createdDate;
    @ManyToOne(fetch = FetchType.LAZY,cascade = CascadeType.ALL)
    @JoinColumn(name = "user_no")
    @ToString.Exclude
    private User user;
    @ManyToOne(fetch = FetchType.LAZY,cascade = CascadeType.ALL)
    @JoinColumn(name = "item_no")
    private Item item;

}