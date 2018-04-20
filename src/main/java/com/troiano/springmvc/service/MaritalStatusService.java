package com.troiano.springmvc.service;

import com.troiano.springmvc.model.MaritalStatus;

import java.util.Iterator;
import java.util.List;

public interface MaritalStatusService {

    List<MaritalStatus> findAllMaritalStatus();
    MaritalStatus findById(int id);

}
