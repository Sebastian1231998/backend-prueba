package com.jiksoft.library.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.io.Serial;
import java.io.Serializable;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@Data
@SuperBuilder
@NoArgsConstructor
public abstract class AbstractFoundationDTO implements Serializable {

    @Serial
    private static final long serialVersionUID = -893476274509876152L;

    @Schema(name = "id", example = "c56a4180-65aa-42ec-a945-5fd21dec0538")
    @JsonProperty("id")
    protected String id;
}