package com.troiano.springmvc.repository;

import com.troiano.springmvc.model.Document;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DocumentRepository extends JpaRepository<Document, Integer> {
}
