package com.jiksoft.library.dto.responses;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.jiksoft.library.dto.AbstractFoundationDTO;

import io.swagger.v3.oas.annotations.media.Schema;
import java.io.Serial;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@Data
@AllArgsConstructor
@NoArgsConstructor
@SuperBuilder
@EqualsAndHashCode(callSuper = true)
public class ResponseBookDTO extends AbstractFoundationDTO {

    @Serial
    private static final long serialVersionUID = 1L;

    @Schema(name = "name", example = "Clean Code")
    @JsonProperty("name")
    private String name;

    @Schema(name = "author", example = "Robert C. Martin")
    @JsonProperty("author")
    private String author;

    @Schema(name = "available", example = "true")
    @JsonProperty("available")
    private Boolean available;

    @Schema(name = "library_id", example = "550e8400-e29b-41d4-a716-446655440000")
    @JsonProperty("library_id")
    private String libraryId;
}