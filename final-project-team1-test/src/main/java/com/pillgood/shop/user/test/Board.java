package com.pillgood.shop.user.test;

import java.util.Date;

import org.hibernate.annotations.CreationTimestamp;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
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
public class Board {
    
    @Id
    @SequenceGenerator(name = "board_no_seq", sequenceName = "board_no_seq", allocationSize = 1,initialValue = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "board_no_seq")
    private Long no;
    private String title;
    private String content;
    private Integer type;
    @CreationTimestamp
    private Date createdDate;
    
}