package com.pillgood.shop.user.test;

import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.SequenceGenerator;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@NoArgsConstructor
@Data
@AllArgsConstructor
@Entity
@Builder
public class Item {
    
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "item_no_seq")
    @SequenceGenerator(name = "item_no_seq", sequenceName = "item_no_seq", allocationSize = 1,initialValue = 1)
    private Long itemNo;
    private String itemBrand;
    private String itemName;
    private Integer itemPrice;
    private String itemDesc;
    @OneToMany(mappedBy = "item", cascade = CascadeType.ALL)
    @Builder.Default
    @ToString.Exclude
    private List<Category> category=new ArrayList<>();
    
}