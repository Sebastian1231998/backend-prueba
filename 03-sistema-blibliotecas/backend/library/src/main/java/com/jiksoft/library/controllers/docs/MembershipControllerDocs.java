package com.jiksoft.library.controllers.docs;

import java.util.List;

import org.springframework.http.ResponseEntity;



import com.jiksoft.library.dto.ApiResponseDTO;
import com.jiksoft.library.dto.request.MembershipDTO;
import com.jiksoft.library.dto.responses.ResponseMembershipDTO;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;

@Tag(name = "Membership", description = "Membership API")
public interface MembershipControllerDocs {

    @Operation(summary = "Find membership by Id.")
    @ApiResponses(value = {
        @ApiResponse(
            responseCode = "200",
            description = "Membership retrieved successfully.",
            content = @Content(mediaType = "application/json",
                               schema = @Schema(implementation = ResponseMembershipDTO.class))
        ),
        @ApiResponse(responseCode = "404", description = "Membership not found.")
    })
    ResponseEntity<ApiResponseDTO<ResponseMembershipDTO>> findById(
        @Parameter(name = "id", description = "Membership ID", required = true)
        String id
    );

    @Operation(summary = "List all memberships.")
    @ApiResponses(value = {
        @ApiResponse(
            responseCode = "200",
            description = "Membership list retrieved successfully.",
            content = @Content(mediaType = "application/json",
                               schema = @Schema(implementation = ResponseMembershipDTO.class))
        )
    })
    ResponseEntity<ApiResponseDTO<List<ResponseMembershipDTO>>> findAll();

    @Operation(summary = "Create a new membership.")
    @ApiResponses(value = {
        @ApiResponse(
            responseCode = "201",
            description = "Membership created successfully.",
            content = @Content(mediaType = "application/json",
                               schema = @Schema(implementation = ResponseMembershipDTO.class))
        )
    })
    ResponseEntity<ApiResponseDTO<ResponseMembershipDTO>> create(
        @Parameter(name = "body", description = "Membership data to create", required = true)
        MembershipDTO createBo
    );

    @Operation(summary = "Delete membership by Id.")
    @ApiResponses(value = {
        @ApiResponse(
            responseCode = "200",
            description = "Membership deleted successfully.",
            content = @Content(mediaType = "application/json",
                               schema = @Schema(implementation = ApiResponseDTO.class))
        ),
        @ApiResponse(responseCode = "404", description = "Membership not found.")
    })
    ResponseEntity<ApiResponseDTO<Void>> delete(
        @Parameter(name = "id", description = "Membership ID", required = true)
        String id
    );
}
