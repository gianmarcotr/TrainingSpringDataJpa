package com.troiano.springmvc.service;

import com.troiano.springmvc.model.MaritalStatus;
import com.troiano.springmvc.repository.MaritalStatusRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service("MaritalStatusService")
@Transactional
public class MaritalStatusServiceImpl implements MaritalStatusService {

    @Autowired
    private MaritalStatusRepository maritalStatusRepository;

    public List<MaritalStatus> findAllMaritalStatus(){
        return maritalStatusRepository.findAll();
    }

    @Override
    public MaritalStatus findById(int id) {
        return maritalStatusRepository.findOne(id);
    }


}
