package com.jiksoft.library.dto.responses;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.jiksoft.library.dto.AbstractFoundationDTO;

import io.swagger.v3.oas.annotations.media.Schema;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;



import java.io.Serial;
import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
@SuperBuilder
@EqualsAndHashCode(callSuper = true)
public class ResponseLoanDTO extends AbstractFoundationDTO {

    @Serial
    private static final long serialVersionUID = 1L;

    @Schema(name = "loan_id", example = "550e8400-e29b-41d4-a716-446655440000")
    @JsonProperty("loan_id")
    private String id;

    @Schema(name = "user_id", example = "123e4567-e89b-12d3-a456-426614174000")
    @JsonProperty("user_id")
    private String userId;

    @Schema(name = "book", description = "Book details")
    @JsonProperty("book")
    private ResponseBookDTO book;

    @Schema(name = "loan_date", example = "2025-10-01")
    @JsonProperty("loan_date")
    private LocalDate loanDate;

    @Schema(name = "return_date", example = "2025-10-15")
    @JsonProperty("return_date")
    private LocalDate returnDate;
}
