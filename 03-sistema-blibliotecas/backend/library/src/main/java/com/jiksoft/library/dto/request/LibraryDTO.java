package com.jiksoft.library.dto.request;

import java.io.Serial;

import com.fasterxml.jackson.annotation.JsonProperty;


import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;

import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@Data
@AllArgsConstructor
@NoArgsConstructor
@SuperBuilder

public class LibraryDTO {

    @Serial
    private static final long serialVersionUID = 1L;

    @Schema(name = "name", example = "Central Library")
    @JsonProperty("name")
    private String name;
}
