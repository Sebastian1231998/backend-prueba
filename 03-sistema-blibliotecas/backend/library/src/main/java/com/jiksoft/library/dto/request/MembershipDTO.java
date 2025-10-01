package com.jiksoft.library.dto.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class MembershipDTO {

    @Schema(name = "user_id", example = "550e8400-e29b-41d4-a716-446655440000")
    @JsonProperty("user_id")
    private String userId;

    @Schema(name = "library_id", example = "550e8400-e29b-41d4-a716-446655440000")
    @JsonProperty("library_id")
    private String libraryId;

}