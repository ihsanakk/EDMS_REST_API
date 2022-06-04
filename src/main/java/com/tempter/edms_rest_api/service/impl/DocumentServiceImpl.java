package com.tempter.edms_rest_api.service.impl;

import com.tempter.edms_rest_api.controllerAdvice.customException.EntityNotFoundException;
import com.tempter.edms_rest_api.controllerAdvice.customException.SignatureException;
import com.tempter.edms_rest_api.controllerAdvice.customException.UnsupportedMediaException;
import com.tempter.edms_rest_api.dao.DocumentDAO;
import com.tempter.edms_rest_api.dao.SignatureDAO;
import com.tempter.edms_rest_api.dao.UserDAO;
import com.tempter.edms_rest_api.dto.DocumentDTO;
import com.tempter.edms_rest_api.dto.SignatureDTO;
import com.tempter.edms_rest_api.entity.Document;
import com.tempter.edms_rest_api.entity.Signature;
import com.tempter.edms_rest_api.entity.User;
import com.tempter.edms_rest_api.entity.enums.DocumentStatus;
import com.tempter.edms_rest_api.service.DocumentService;
import com.tempter.edms_rest_api.util.SignatureUtility;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.*;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class DocumentServiceImpl implements DocumentService {

    private final UserDAO userDAO;
    private final DocumentDAO documentDAO;
    private final SignatureDAO signatureDAO;
    private final ModelMapper modelMapper;

    @Override
    public List<DocumentDTO> findAllDocumentByPublisherId(int userId) {
        return Arrays.asList(modelMapper.map(documentDAO.findByPublisherUser_Id(userId), DocumentDTO[].class));
    }
    @Override
    public List<DocumentDTO> findAllApprovalPendingDocuments() {
        return Arrays.asList(modelMapper.map(documentDAO.findByDocumentStatus(DocumentStatus.SIGNED),DocumentDTO[].class));
    }

    @Override
    public List<DocumentDTO> getDocumentsPendingForSigning(int userId) {
        User user = userDAO.findById(userId).orElseThrow(() -> new RuntimeException("User not found by id: " + userId));
        Set<Document> pendingSet = user.getAssignedDocuments();
        pendingSet.removeAll(user.getSignedDocuments());
        return Arrays.asList(modelMapper.map(pendingSet,DocumentDTO[].class));
    }

    @Override
    public DocumentDTO rejectDocument(int documentId) {
        Document document = documentDAO.findById(documentId).orElseThrow(()-> new EntityNotFoundException("Document not found by id: "+ documentId));
        document.setDocumentStatus(DocumentStatus.REJECTED);
        documentDAO.save(document);
        return modelMapper.map(document,DocumentDTO.class);
    }

    @Override
    public DocumentDTO approveDocument(int documentId) {
        Document document = documentDAO.findById(documentId).orElseThrow(()-> new EntityNotFoundException("Document not found by id: "+ documentId));
        document.setDocumentStatus(DocumentStatus.APPROVED);
        documentDAO.save(document);
        return modelMapper.map(document,DocumentDTO.class);
    }

    @Override
    public List<DocumentDTO> getAssignedDocumentsOfUser(int userId) {
        return Arrays.asList(modelMapper.map(userDAO.findById(userId).orElseThrow(()-> new EntityNotFoundException("User not found by id: "+ userId)).getAssignedDocuments(),DocumentDTO[].class));
    }

    @Override
    public List<DocumentDTO> getSignedDocumentsOfUser(int userId) {
        return Arrays.asList(modelMapper.map(userDAO.findById(userId).orElseThrow(()-> new EntityNotFoundException("User not found by id: "+ userId)).getSignedDocuments(),DocumentDTO[].class));
    }

    @Override
    public Document downloadDocument(int documentId) {
        return documentDAO.findById(documentId).orElseThrow(() -> new EntityNotFoundException("Document not found by id: "+ documentId));
    }

    @Override
    public List<DocumentDTO> getAllDocuments() {
        return Arrays.asList(modelMapper.map(documentDAO.findAll(),DocumentDTO[].class));
    }

    @Override
    public DocumentDTO getDocumentById(int documentId) {
        return modelMapper.map(documentDAO.
                findById(documentId)
                .orElseThrow(() -> new EntityNotFoundException("Document not found by id: "+ documentId)), DocumentDTO.class);
    }

    @Override
    public DocumentDTO createDocument(int userId, MultipartFile file, int[] assignedUsersId) throws IOException {

        if(!StringUtils.getFilenameExtension(file.getOriginalFilename()).equalsIgnoreCase("pdf")) {
            throw new UnsupportedMediaException("Unsupported media type. File must be application/pdf");
        }

        User user = userDAO.findById(userId).orElseThrow(() -> new RuntimeException("User not found by id: " + userId));
        Document document = new Document();
        document.setPublisherUser(user);
        document.setDocumentStatus(DocumentStatus.IN_PROGRESS);
        document.setDocument(file.getBytes());
        document.setCreatedAt(new Date(System.currentTimeMillis()));
        document.setDocumentName(StringUtils.cleanPath(Objects.requireNonNull(file.getOriginalFilename())));

        Set<User> assignedUsers = Arrays.stream(assignedUsersId)
                .mapToObj(id -> userDAO.findById(id)
                        .orElseThrow(() -> new RuntimeException("User not found by id: " + id)))
                .collect(Collectors.toSet());

        document.setAssignedUsers(assignedUsers);
        documentDAO.save(document);

        return modelMapper.map(document,DocumentDTO.class);

    }

    @Override
    public SignatureDTO signDocument(int userId, int documentId) {

        User user  = userDAO
                .findById(userId)
                .orElseThrow(() -> new EntityNotFoundException("User not found by id: "+ userId));

        Document document = documentDAO.findById(documentId).orElseThrow(() -> new EntityNotFoundException("Document not found by id: "+ documentId));

        Set<User> assignedUsers = document.getAssignedUsers();
        if (!assignedUsers.contains(user))
            throw new SignatureException("This user is not assigned to sign this document");

        Set<Signature> signatureSet = document.getSignatureSet();
        document.getSignatureSet().forEach(e -> {
            if (e.getSignatureOwner().getId()==userId) {
                throw new SignatureException("This user has already signed the document. Document Id: " + documentId + " User Id: " + userId);
            }
        });
        Signature signature = new Signature();
        signature.setSignatureOwner(user);
        signature.setDocument(document);
        signature.setSignedAt(new Date());
        signature.setSignatureText(SignatureUtility.createSignatureForDocument(document,signature));
        signatureSet.add(signature);
        document.setSignatureSet(signatureSet);
        document.setDocumentStatus(DocumentStatus.SIGNING);
        document.setCurrentDepartment(user.getDepartment());

        Set<User> signedUsers = document.getSignedUsers();
        signedUsers.add(user);
        document.setSignedUsers(signedUsers);

        if (document.getAssignedUsers().size()==document.getSignedUsers().size()) {
            document.setDocumentStatus(DocumentStatus.SIGNED);
        }

        signatureDAO.save(signature);
        documentDAO.save(document);

        return modelMapper.map(signature, SignatureDTO.class);
    }

    @Override
    public void deleteDocument(int documentId) {
        documentDAO.deleteById(documentId);
    }




}
