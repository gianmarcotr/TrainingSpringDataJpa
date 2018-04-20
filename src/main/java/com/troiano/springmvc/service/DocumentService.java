package com.troiano.springmvc.service;

import com.troiano.springmvc.model.Document;

import java.util.List;

public interface DocumentService {

    List<Document> findAllDocument();
    void saveDocument(Document doc);
    Document findById(int id);
    void deleteById(int id);
}
