package com.itwill.jpa.entity;

import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.Entity;
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
@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Survey {
	@Id
	@SequenceGenerator(name = "survey_no_seq",sequenceName = "survey_no_seq",initialValue = 1, allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE,generator = "survey_no_seq")
	private Long no;
	private Long height;
	private Long weight;
	@ManyToOne
	@ToString.Exclude
	@JoinColumn(name = "user_no")
	private User user;
	@Builder.Default
	@OneToMany(mappedBy = "survey")
	private List<Symptom> symptom = new ArrayList<>();

}
