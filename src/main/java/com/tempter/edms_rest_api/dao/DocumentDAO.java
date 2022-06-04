package com.tempter.edms_rest_api.dao;

import com.tempter.edms_rest_api.entity.Document;
import com.tempter.edms_rest_api.entity.enums.DocumentStatus;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface DocumentDAO extends JpaRepository<Document,Integer> {

    List<Document> findByPublisherUser_Id(int userId);

    List<Document> findByDocumentStatus(DocumentStatus documentStatus);


}
