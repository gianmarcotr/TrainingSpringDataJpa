package com.troiano.springmvc.service;

import com.troiano.springmvc.model.Skill;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;


public interface SkillService {

    List<Skill> findAllSkills();
    Skill findById(int id);
}
