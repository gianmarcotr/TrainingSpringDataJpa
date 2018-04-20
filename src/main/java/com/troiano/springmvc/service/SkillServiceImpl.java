package com.troiano.springmvc.service;

import com.troiano.springmvc.model.Skill;
import com.troiano.springmvc.repository.SkillRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service("SkillService")
@Transactional
public class SkillServiceImpl implements SkillService {

    @Autowired
    SkillRepository skillRepository;

    @Override
    public List<Skill> findAllSkills() {
        return skillRepository.findAll();
    }

    @Override
    public Skill findById(int id) {
        return skillRepository.findOne(id);
    }
}
