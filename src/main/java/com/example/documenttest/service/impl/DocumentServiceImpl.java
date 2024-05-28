package com.example.documenttest.service.impl;


import com.example.documenttest.rest.dto.request.DocumentRequestDTO;
import com.example.documenttest.rest.dto.response.DocumentResponseDTO;
import com.example.documenttest.rest.mapper.DocumentMapper;
import com.example.documenttest.service.DocumentService;
import com.example.documenttest.store.entity.Documents;
import com.example.documenttest.store.repository.DocumentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class DocumentServiceImpl implements DocumentService {

    private final DocumentRepository documentRepository;

    private final DocumentMapper documentMapper;

    @Override
    public DocumentResponseDTO createDocument(DocumentRequestDTO documentRequestDTO) {
        Optional<Documents> existingDocument = documentRepository.findByTypeAndNumberAndDate(
                documentRequestDTO.getType(),
                documentRequestDTO.getNumber(),
                documentRequestDTO.getDate());

        if (existingDocument.isPresent()) {
            throw new IllegalArgumentException("Документ с такими реквизитами уже существует");
        }

        Documents document = documentMapper.toEntity(documentRequestDTO);
        Documents savedDocument = documentRepository.save(document);
        return documentMapper.toDto(savedDocument);
    }

    @Override
    public DocumentResponseDTO getDocumentById(UUID id) {
        Optional<Documents> optionalDocument = documentRepository.findById(id);
        return optionalDocument.map(documentMapper::toDto).orElse(null);
    }

    @Override
    public List<DocumentResponseDTO> getAllDocuments() {
        return documentRepository.findAll().stream()
                .map(documentMapper::toDto)
                .collect(Collectors.toList());
    }

    @Override
    public List<DocumentResponseDTO> searchDocuments(DocumentRequestDTO request) {
        List<Documents> documents = documentRepository.findByTypeOrNumberOrDate(
                request.getType(),
                request.getNumber(),
                request.getDate());

        return documents.stream()
                .map(documentMapper::toDto)
                .collect(Collectors.toList());
    }

    @Override
    public DocumentResponseDTO updateDocument(UUID id, DocumentRequestDTO documentRequestDTO) {
        Optional<Documents> existingDocument = documentRepository.findById(id);

        if (existingDocument.isPresent()) {
            Documents document = existingDocument.get();
            document.setType(documentRequestDTO.getType());
            document.setContent(documentRequestDTO.getContent());
            document.setNumber(documentRequestDTO.getNumber());
            document.setDate(documentRequestDTO.getDate());

            Documents updatedDocument = documentRepository.save(document);
            return documentMapper.toDto(updatedDocument);
        } else {
            throw new IllegalArgumentException("Документ с таким ID не найден");
        }
    }

    @Override
    public void deleteDocument(UUID id) {
        documentRepository.deleteById(id);
    }
}
