package com.tempter.edms_rest_api.service;

import com.tempter.edms_rest_api.dto.DocumentDTO;
import com.tempter.edms_rest_api.dto.SignatureDTO;
import com.tempter.edms_rest_api.entity.Document;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

public interface DocumentService {

    List<DocumentDTO> getAllDocuments();

    DocumentDTO getDocumentById(int documentId);

    List<DocumentDTO> findAllDocumentByPublisherId(int userId);

    List<DocumentDTO> findAllApprovalPendingDocuments();

    List<DocumentDTO> getDocumentsPendingForSigning(int userId);

    DocumentDTO rejectDocument(int documentId);

    public DocumentDTO approveDocument(int documentId);

    List<DocumentDTO> getAssignedDocumentsOfUser(int userId);

    List<DocumentDTO> getSignedDocumentsOfUser(int userId);

    Document downloadDocument(int documentId);

    DocumentDTO createDocument(int userId, MultipartFile file, int[] assignedUsersId) throws IOException;

    SignatureDTO signDocument(int userId, int documentId);

    void deleteDocument(int documentId);
}
