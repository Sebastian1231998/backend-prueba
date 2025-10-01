package com.jiksoft.library.dto.responses;

import java.io.Serial;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.jiksoft.library.dto.AbstractFoundationDTO;

import io.swagger.v3.oas.annotations.media.Schema;
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
public class ResponseLibraryDTO extends AbstractFoundationDTO {

    @Serial
    private static final long serialVersionUID = 1L;

    @Schema(name = "name", example = "Central Library")
    @JsonProperty("name")
    private String name;
}