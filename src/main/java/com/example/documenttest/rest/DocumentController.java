package com.example.documenttest.rest;

import com.example.documenttest.rest.dto.request.DocumentRequestDTO;
import com.example.documenttest.rest.dto.response.DocumentResponseDTO;
import com.example.documenttest.service.DocumentService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/documents")
@Tag(name = "Documents", description = "Documents API")
@RequiredArgsConstructor
public class DocumentController {

    private final DocumentService documentService;

    @PostMapping
    @Operation(summary = "Создать документ", description = "Создает новый документ.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Документ создан"),
            @ApiResponse(responseCode = "400", description = "Неверные данные"),
            @ApiResponse(responseCode = "409", description = "Документ с такими реквизитами уже существует")
    })
    public ResponseEntity<DocumentResponseDTO> createDocument(
            @Valid @RequestBody DocumentRequestDTO documentRequestDTO) {
        DocumentResponseDTO documentResponseDTO = documentService.createDocument(documentRequestDTO);
        return new ResponseEntity<>(documentResponseDTO, HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    @Operation(summary = "Получить документ по ID", description = "Возвращает документ по указанному ID.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Документ найден"),
            @ApiResponse(responseCode = "404", description = "Документ не найден")
    })
    public ResponseEntity<DocumentResponseDTO> getDocumentById(@PathVariable UUID id) {
        DocumentResponseDTO documentResponseDTO = documentService.getDocumentById(id);
        if (documentResponseDTO != null) {
            return new ResponseEntity<>(documentResponseDTO, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping
    @Operation(summary = "Получить все документы", description = "Возвращает список всех документов.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Документы найдены")
    })
    public ResponseEntity<List<DocumentResponseDTO>> getAllDocuments() {
        List<DocumentResponseDTO> documentResponseDTOs = documentService.getAllDocuments();
        return new ResponseEntity<>(documentResponseDTOs, HttpStatus.OK);
    }

    @PostMapping("/search")
    @Operation(summary = "Поиск документов", description = "Поиск документов по критериям.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Документы найдены")
    })
    public ResponseEntity<List<DocumentResponseDTO>> searchDocuments(
            @RequestBody DocumentRequestDTO request) {
        List<DocumentResponseDTO> documentResponseDTOs = documentService.searchDocuments(request);
        return new ResponseEntity<>(documentResponseDTOs, HttpStatus.OK);
    }

    @PutMapping("/{id}")
    @Operation(summary = "Обновить документ", description = "Обновляет документ по указанному ID.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Документ обновлен"),
            @ApiResponse(responseCode = "404", description = "Документ не найден")
    })
    public ResponseEntity<DocumentResponseDTO> updateDocument(
            @PathVariable UUID id, @Valid @RequestBody DocumentRequestDTO documentRequestDTO) {
        DocumentResponseDTO updatedDocument = documentService.updateDocument(id, documentRequestDTO);
        if (updatedDocument != null) {
            return new ResponseEntity<>(updatedDocument, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Удалить документ", description = "Удаляет документ по указанному ID.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Документ удален"),
            @ApiResponse(responseCode = "404", description = "Документ не найден")
    })
    public ResponseEntity<Void> deleteDocument(@PathVariable UUID id) {
        documentService.deleteDocument(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}