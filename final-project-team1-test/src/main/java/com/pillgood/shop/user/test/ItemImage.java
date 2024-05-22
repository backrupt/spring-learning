package com.pillgood.shop.user.test;

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

@NoArgsConstructor
@Data
@Entity
@AllArgsConstructor
@Builder
public class ItemImage {
	@Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "item_image_no_seq")
    @SequenceGenerator(name = "item_image_no_seq", sequenceName = "item_image_no_seq", allocationSize = 1,initialValue = 1)
    private Long no;
    private String img;
    private Integer type;
    @ManyToOne(fetch = FetchType.LAZY,cascade = CascadeType.ALL)
    @JoinColumn(name = "item_no")
    @ToString.Exclude
    private Item item;

}