package com.associate.skill.entity;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToOne;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@JsonInclude(value = Include.NON_NULL)
@NoArgsConstructor
public class PersonalDetails {
	@Id
	@GeneratedValue
	@JsonIgnore
	private long id;
	private String name;
	private String email;
	private String role;
	private long employeeId;
	private boolean admin = false;
	@OneToOne(fetch = FetchType.LAZY)
	@JsonIgnore
	private Associate associate;
}
