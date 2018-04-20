package com.troiano.springmvc.service;

import com.troiano.springmvc.model.Document;
import com.troiano.springmvc.repository.DocumentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service("DocumentService")
@Transactional
public class DocumentServiceImpl implements DocumentService{

    @Autowired
    DocumentRepository documentRepository;

    @Override
    public List<Document> findAllDocument() {
        return documentRepository.findAll();
    }

    @Override
    public void saveDocument(Document doc) {
        documentRepository.save(doc);
    }

    @Override
    public Document findById(int id) {
        return documentRepository.findOne(id);
    }

    @Override
    public void deleteById(int id) {
        documentRepository.delete(id);
    }
}
