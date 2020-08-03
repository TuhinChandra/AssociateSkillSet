package com.associate.skill.entity;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

import org.hibernate.annotations.Where;

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
	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	@JoinColumn(name = "ASSOCIATE_ID")
	@Where(clause = "TYPE = 'PRIMARY'")
	private Set<SkillSet> primarySkills = new HashSet<>();
	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	@JoinColumn(name = "ASSOCIATE_ID")
	@Where(clause = "TYPE = 'SECONDARY'")
	private Set<SkillSet> secondarySkills = new HashSet<>();
	@JoinColumn(name = "ASSOCIATE_ID")
	@Where(clause = "TYPE = 'ASPIRATION'")
	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	private Set<SkillSet> aspirations = new HashSet<>();
}
