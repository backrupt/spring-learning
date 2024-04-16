package com.itwill.jpa.relation.entity;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.SequenceGenerator;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
public class Tutor{
	@Id
	@SequenceGenerator(name = "TUTOR_TUTOR_ID_SEQ",sequenceName = "TUTOR_TUTOR_ID_SEQ",initialValue = 1,allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE,generator = "TUTOR_TUTOR_ID_SEQ")
	private Long tutorId;
	private String name;
	private String email;
	private String phone;
	private LocalDateTime dob;
	
	@OneToMany(mappedBy = "tutor",fetch = FetchType.LAZY,cascade = CascadeType.PERSIST)
	@Builder.Default
	@ToString.Exclude
	private List<Course> courses = new ArrayList<>();
	
	@ManyToOne
	@JoinColumn(name = "addr_id")
	@ToString.Exclude
	private Address address;
}
