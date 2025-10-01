package com.jiksoft.library.dto.request;

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
public class BookDTO extends AbstractFoundationDTO {

    @Serial
    private static final long serialVersionUID = 1L;

    @Schema(name = "name", example = "The Lord of the Rings")
    @JsonProperty("name")
    private String name;

    @Schema(name = "author", example = "J.R.R. Tolkien")
    @JsonProperty("author")
    private String author;

    @Schema(name = "available", example = "true")
    @JsonProperty("available")
    private Boolean available;

    @Schema(name = "library_id", example = "a1b2c3d4-e5f6-7890-abcd-1234567890ef")
    @JsonProperty("library_id")
    private String libraryId;
}
