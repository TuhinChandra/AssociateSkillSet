package com.associate.skill.controller;

import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.associate.skill.entity.Associate;
import com.associate.skill.model.AssociateSkillMasterData;
import com.associate.skill.service.AssociateSkillSetService;
import com.associate.skill.util.CsvUtils;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;

@RestController
public class AssociateSkillSetController {

	@Autowired
	private AssociateSkillSetService associateSkillSetService;

	@RequestMapping(value = "/upload", method = RequestMethod.POST, produces = "application/json", consumes = "multipart/form-data")
	@ResponseBody
	public List<Associate> uploadUsers(@RequestParam("file") final MultipartFile file) throws IOException {
		return associateSkillSetService
				.loadAssociateSkillSet(CsvUtils.read(AssociateSkillMasterData.class, file.getInputStream()));
	}

	@GetMapping(value = "/associate/{employeeId}", produces = "application/json")
	public Associate getAssociate(@PathVariable final long employeeId)
			throws JsonParseException, JsonMappingException, IOException {
		return associateSkillSetService.getAssociate(employeeId);
	}

	@GetMapping(value = "/associate/", produces = "application/json")
	public List<Associate> getAssociate() throws JsonParseException, JsonMappingException, IOException {
		return associateSkillSetService.getAllAssociates();
	}
}
