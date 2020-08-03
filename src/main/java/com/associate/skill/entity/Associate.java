package com.associate.skill.entity;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

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
public class Associate {
	@Id
	private long id;
	@OneToOne(mappedBy = "associate", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	private PersonalDetails personalDetails;
	@OneToMany(mappedBy = "associate", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	private Set<SkillSet> primarySkills = new HashSet<>();
	@OneToMany(mappedBy = "associate", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	private Set<SkillSet> secondarySkills = new HashSet<>();
	@OneToMany(mappedBy = "associate", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	private Set<SkillSet> aspirations = new HashSet<>();
}
