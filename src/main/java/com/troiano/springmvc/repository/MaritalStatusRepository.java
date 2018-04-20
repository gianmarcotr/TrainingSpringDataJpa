package com.troiano.springmvc.repository;

import com.troiano.springmvc.model.MaritalStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;

public interface MaritalStatusRepository extends JpaRepository<MaritalStatus, Integer>{


}
