package com.example.documenttest.rest.dto.request;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.Date;

@Setter
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Schema(description = "Входное dto документа")
public class DocumentRequestDTO {

    @NotBlank(message = "Тип не может быть пустым")
    @Schema(description = "Тип")
    private String type;

    @NotNull(message = "Контент не может быть пустым")
    @Schema(description = "Контент")
    private String content;

    @NotNull(message = "Номер не может быть пустым")
    @Schema(description = "Номер")
    private String number;

    @NotNull(message = "Дата не может быть пустым")
    @Schema(description = "Дата")
    private Date date;

}
