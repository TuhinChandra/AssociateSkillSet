package com.associate.skill.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.associate.skill.entity.Associate;

@Repository
public interface AssociateSkillSetRepository extends JpaRepository<Associate, Long> {

}
