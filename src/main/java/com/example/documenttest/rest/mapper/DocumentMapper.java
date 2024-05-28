package com.example.documenttest.rest.mapper;

import com.example.documenttest.rest.dto.request.DocumentRequestDTO;
import com.example.documenttest.rest.dto.response.DocumentResponseDTO;
import com.example.documenttest.store.entity.Documents;

import org.mapstruct.Mapper;

@Mapper(componentModel = "spring"
)
public interface DocumentMapper {
    DocumentResponseDTO toDto(Documents documents);

    Documents toEntity(DocumentRequestDTO documentRequestDTO);

}
