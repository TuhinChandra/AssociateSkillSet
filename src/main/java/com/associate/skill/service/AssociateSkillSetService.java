package com.associate.skill.service;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import com.associate.skill.entity.Associate;
import com.associate.skill.entity.PersonalDetails;
import com.associate.skill.entity.SkillSet;
import com.associate.skill.entity.SkillType;
import com.associate.skill.model.AssociateSkillMasterData;
import com.associate.skill.repository.AssociateSkillSetRepository;

@Service
public class AssociateSkillSetService {
	private static final String SEPARATOR_CHAR = ";";
	@Autowired
	private AssociateSkillSetRepository associateSkillSetRepository;

	public Associate getAssociate(final long employeeId) {
		return associateSkillSetRepository.findById(employeeId).orElse(null);
	}

	public List<Associate> getAllAssociates() {
		final List<Associate> associates = associateSkillSetRepository.findAll();
		return associates;
	}

	public List<Associate> loadAssociateSkillSet(final List<AssociateSkillMasterData> skillSetMasterData) {
		List<Associate> associates = null;
		if (!CollectionUtils.isEmpty(skillSetMasterData)) {
			associates = new ArrayList<>();
			for (final AssociateSkillMasterData eachSkillSetData : skillSetMasterData) {
				final long employeeId = Long.parseLong(eachSkillSetData.getEmployeeId());
				Associate associate = getAssociate(employeeId);
				if (null == associate) {
					associate = new Associate();
					associate.setId(employeeId);
				}
				updatePersonalDetails(associate, eachSkillSetData);
				updateSkillSet(associate, eachSkillSetData);
				associates.add(associateSkillSetRepository.save(associate));
			}
		}
		return associates;
	}

	private void updatePersonalDetails(final Associate associate, final AssociateSkillMasterData eachSkillSetData) {
		PersonalDetails personalDetails = associate.getPersonalDetails();
		if (null == personalDetails) {
			personalDetails = new PersonalDetails();
		}
		personalDetails.setAssociate(associate);
		personalDetails.setEmployeeId(Long.parseLong(eachSkillSetData.getEmployeeId()));
		personalDetails.setRole(eachSkillSetData.getRole());
		personalDetails.setName(eachSkillSetData.getName());
		personalDetails.setEmail(eachSkillSetData.getEmail());
		associate.setPersonalDetails(personalDetails);
	}

	private void updateSkillSet(final Associate associate, final AssociateSkillMasterData eachSkillSetData) {
		createSkillSet(associate,
				StringUtils.split(eachSkillSetData.getPrimarySkills(), AssociateSkillSetService.SEPARATOR_CHAR),
				SkillType.PRIMARY);
		createSkillSet(associate,
				StringUtils.split(eachSkillSetData.getSecondarySkills1(), AssociateSkillSetService.SEPARATOR_CHAR),
				SkillType.SECONDARY);
		createSkillSet(associate,
				StringUtils.split(eachSkillSetData.getSecondarySkills2(), AssociateSkillSetService.SEPARATOR_CHAR),
				SkillType.SECONDARY);
		createSkillSet(associate,
				StringUtils.split(eachSkillSetData.getAspiredSkills(), AssociateSkillSetService.SEPARATOR_CHAR),
				SkillType.ASPIRATION);
	}

	private void createSkillSet(final Associate associate, final String[] primarySkills, final SkillType type) {
		if (null != primarySkills && primarySkills.length > 0) {
			final Set<SkillSet> skillSets = new HashSet<>();
			for (final String skill : primarySkills) {
				skillSets.add(new SkillSet(skill, type, null, associate));
			}
			switch (type) {
			case PRIMARY:
				associate.getPrimarySkills().addAll(skillSets);
				break;
			case SECONDARY:
				associate.getSecondarySkills().addAll(skillSets);
				break;
			case ASPIRATION:
				associate.getAspirations().addAll(skillSets);
				break;
			default:
				break;
			}
		}

	}

}
