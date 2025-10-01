package com.jiksoft.library.dto.responses;


import java.io.Serial;
import java.time.LocalDate;

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
public class ResponseMembershipDTO extends AbstractFoundationDTO {

    @Serial
    private static final long serialVersionUID = 1L;

    @Schema(name = "id", example = "550e8400-e29b-41d4-a716-446655440000")
    @JsonProperty("id")
    private String id;

    @Schema(name = "user_id", example = "123e4567-e89b-12d3-a456-426614174000")
    @JsonProperty("user_id")
    private String userId;

    @Schema(name = "library", description = "Library info")
    @JsonProperty("library")
    private ResponseLibraryDTO library;

    @Schema(name = "type", example = "STANDARD")
    @JsonProperty("type")
    private String type;

    @Schema(name = "start_date", example = "2025-10-01")
    @JsonProperty("start_date")
    private LocalDate startDate;

    @Schema(name = "end_date", example = "2026-10-01")
    @JsonProperty("end_date")
    private LocalDate endDate;
}