package com.associate.skill.entity;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

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
public class SkillSet {
	@Id
	@GeneratedValue
	@JsonIgnore
	private long id;

	private String skill;

	@Enumerated(EnumType.STRING)
	private SkillType type;

	private String experienceInYears;

	@Enumerated(EnumType.STRING)
	private Competency competency;

	@Enumerated(EnumType.STRING)
	private ExperienceType experienceType;

	@ManyToOne(fetch = FetchType.LAZY)
	@JsonIgnore
	private Associate associate;

	public SkillSet(final String skill, final SkillType type, final ExperienceType experienceType,
			final Associate associate) {
		super();
		this.skill = skill;
		this.type = type;
		this.experienceType = experienceType;
		this.associate = associate;
	}

}
