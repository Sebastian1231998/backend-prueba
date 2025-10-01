package com.jiksoft.library.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

import io.swagger.v3.oas.annotations.media.Schema;

import java.io.Serial;
import java.io.Serializable;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Schema(name = "ApiResponseDTO")
public class ApiResponseDTO<T> implements Serializable {

    @Serial
    private static final long serialVersionUID = -8097821240224209802L;

    @Schema(example = "false")
    @JsonProperty(value = "is_base_64_encoded")
    private Boolean isBase64Encoded;

    @Schema(example = "200")
    @JsonProperty(value = "status_code")
    private Integer statusCode;

    @Schema(example = "message")
    @JsonProperty(value = "message")
    private String message;

    @Schema(example = "Error processing data")
    @JsonProperty(value = "error")
    private List<String> error;

    @JsonProperty(value = "body")
    private transient T body;
}
