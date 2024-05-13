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
import lombok.ToString;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Address {
	@Id
	@SequenceGenerator(name = "address_no_seq",sequenceName = "address_no_seq",initialValue = 1, allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE,generator = "address_no_seq")
	private Long no;
	private String name;
	private String phone;
	private Long zipcode;
	private String address;
	private String addressDetail;
	private String request;
	private boolean selected;
	@ManyToOne
    @JoinColumn(name = "no")
	@ToString.Exclude
	private User user;
	
}
