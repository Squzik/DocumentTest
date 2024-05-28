package com.example.documenttest.service;

import com.example.documenttest.rest.dto.request.DocumentRequestDTO;
import com.example.documenttest.rest.dto.response.DocumentResponseDTO;

import java.util.List;
import java.util.UUID;

public interface DocumentService {

    DocumentResponseDTO createDocument(DocumentRequestDTO documentRequestDTO);
    DocumentResponseDTO getDocumentById(UUID id);
    List<DocumentResponseDTO> getAllDocuments();
    List<DocumentResponseDTO> searchDocuments(DocumentRequestDTO request);
    DocumentResponseDTO updateDocument(UUID id, DocumentRequestDTO documentRequestDTO);
    void deleteDocument(UUID id);
}
