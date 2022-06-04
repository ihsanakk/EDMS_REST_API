package com.tempter.edms_rest_api.controller;

import com.tempter.edms_rest_api.dto.DocumentDTO;
import com.tempter.edms_rest_api.dto.DocumentUploadRequest;
import com.tempter.edms_rest_api.dto.MessageResponse;
import com.tempter.edms_rest_api.entity.Document;
import com.tempter.edms_rest_api.service.DocumentService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;

import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.List;

@CrossOrigin
@RequiredArgsConstructor
@RestController
@RequestMapping("/api/document")
public class DocumentController {

//    private final UserService userService;
    private final DocumentService documentService;

    @PutMapping("/approve/{documentId}")
    public ResponseEntity<MessageResponse> approveDocument(@PathVariable int documentId) {
        documentService.approveDocument(documentId);
        return ResponseEntity.ok().body(new MessageResponse("Document Approved"));
    }

    @GetMapping("/sign-pending-docs")
    public ResponseEntity<List<DocumentDTO>> getSignPendingDocs(int userId) {
        return ResponseEntity.ok().body(documentService.getDocumentsPendingForSigning(userId));
    }

    @PutMapping("/reject/{documentId}")
    public ResponseEntity<MessageResponse> rejectDocument(@PathVariable int documentId) {
        documentService.rejectDocument(documentId);
        return ResponseEntity.ok().body(new MessageResponse("Document Rejected"));
    }

    @GetMapping("/approval-pending-docs")
    public ResponseEntity<List<DocumentDTO>> approvalPendingDocument() {
        return ResponseEntity.ok().body(documentService.findAllApprovalPendingDocuments());
    }

    @GetMapping("/download/{documentId}")
    public ResponseEntity<byte[]> download(@PathVariable int documentId, HttpServletRequest httpServletRequest) {
        Document document = documentService.downloadDocument(documentId);
        String filename = document.getDocumentName();
        String mimeType = httpServletRequest.getServletContext().getMimeType(filename);
        return ResponseEntity.ok()
                .contentType(MediaType.parseMediaType(mimeType))
                .header(HttpHeaders.CONTENT_DISPOSITION, "inline;filename=" + document.getDocumentName())
                .body(document.getDocument());
    }

    @GetMapping("/render/{documentId}")
    public ResponseEntity<byte[]> renderFile(@PathVariable int documentId, HttpServletRequest httpServletRequest) {
        DocumentDTO document = documentService.getDocumentById(documentId);
        String documentName = document.getDocumentName();
        String mimeType = httpServletRequest.getServletContext().getMimeType(documentName);
        return ResponseEntity.ok()
                .contentType(MediaType.parseMediaType(mimeType))
                .header(HttpHeaders.CONTENT_DISPOSITION, "inline;filename=" + document.getDocumentName())
                .body(document.getDocument());
    }

    @GetMapping("/assignedDocs")
    public ResponseEntity<List<DocumentDTO>> getAssignedDocs(int userId) {
        return ResponseEntity.ok().body(documentService.getAssignedDocumentsOfUser(userId));
    }

    @GetMapping("/signedDocs")
    public ResponseEntity<List<DocumentDTO>> getSignedDocs(int userId) {
        return ResponseEntity.ok().body(documentService.getSignedDocumentsOfUser(userId));
    }

    @GetMapping("/{documentId}")
    public ResponseEntity<DocumentDTO> getDocumentById(@PathVariable int documentId) {
        return ResponseEntity.ok().body(documentService.getDocumentById(documentId));
    }

    @GetMapping("/all")
    public ResponseEntity<List<DocumentDTO>> getAll() {
        return ResponseEntity.ok().body(documentService.getAllDocuments());
    }

    @PostMapping(value = "/create-document")
    public ResponseEntity<MessageResponse> createDocument(int userId,
                                                     @RequestPart("file") MultipartFile file,
                                                     @RequestPart("json") DocumentUploadRequest documentUploadRequest)
            throws IOException {

        documentService.createDocument(userId, file, documentUploadRequest.getUserIds());
        return ResponseEntity.ok().body(new MessageResponse("Document has been uploaded successfully"));
    }

    @PutMapping("/sign-document")
    public ResponseEntity<MessageResponse> signDocument(int signingUserId, int documentId) {
        documentService.signDocument(signingUserId,documentId);
        return ResponseEntity.ok().body(new MessageResponse("Signature has been applied successfully"));
    }

    @DeleteMapping("/{documentId}")
    public void delete(@PathVariable int documentId) {
        documentService
                .deleteDocument(documentId);
    }

    @GetMapping("/user-docs")
    public ResponseEntity<List<DocumentDTO>> getDocumentByUserId(int userId) {
        return ResponseEntity.ok().body(documentService.findAllDocumentByPublisherId(userId));
    }

}
