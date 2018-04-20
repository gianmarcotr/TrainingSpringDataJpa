package com.troiano.springmvc.converter;

import com.troiano.springmvc.model.Skill;
import com.troiano.springmvc.service.SkillService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;


/**
 * A converter class used in views to map id's to actual Skill objects.
 */
@Component
public class SkillConverter implements Converter<Object, Skill>{

    @Autowired
    SkillService skillService;

    /**
     * Gets Skill by Id
     */
    public Skill convert(Object element) {
        Integer id = Integer.parseInt((String)element);
        Skill skill = skillService.findById(id);
        return skill;
    }

}