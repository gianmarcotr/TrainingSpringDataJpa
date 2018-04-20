package com.troiano.springmvc.repository;

import com.troiano.springmvc.model.Skill;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;

public interface SkillRepository extends JpaRepository<Skill, Integer> {

}
