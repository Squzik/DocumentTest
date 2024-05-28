package com.example.documenttest.rest.dto.response;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;
import java.util.UUID;

@Setter
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class DocumentResponseDTO {

    @Schema(description = "Id в базе данных")
    private UUID id;

    @Schema(description = "Тип")
    private String type;

    @Schema(description = "Контент")
    private String content;

    @Schema(description = "Дата")
    private Date date;

    @Schema(description = "Номер")
    private String number;

}
