package com.itwill.jpa.entity;


import jakarta.persistence.Entity;
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

@NoArgsConstructor
@Data
@Entity
@AllArgsConstructor
@Builder
public class OrderItem {
    
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "order_item_no_seq")
    @SequenceGenerator(name = "order_item_no_seq", sequenceName = "order_item_no_SEQ", allocationSize = 1)
    private Long no;
    private Integer qty;
    @ManyToOne
    @JoinColumn(name = "item_no")
    private Item item;
    @ManyToOne
    @JoinColumn(name = "order_no")
    private Order order;
}
