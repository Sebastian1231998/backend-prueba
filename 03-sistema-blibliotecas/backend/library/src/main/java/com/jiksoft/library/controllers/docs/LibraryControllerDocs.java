package com.jiksoft.library.controllers.docs;

import java.util.List;

import org.springframework.http.ResponseEntity;

import com.jiksoft.library.dto.ApiResponseDTO;
import com.jiksoft.library.dto.request.LibraryDTO;
import com.jiksoft.library.dto.responses.ResponseLibraryDTO;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.tags.Tag;

@Tag(name = "Library", description = "Library API")
public interface LibraryControllerDocs {

    @Operation(summary = "Find library by Id.")
    @ApiResponses(value = {
        @ApiResponse(
            responseCode = "200",
            description = "Library retrieved successfully.",
            content = @Content(mediaType = "application/json",
                               schema = @Schema(implementation = ResponseLibraryDTO.class))
        ),
        @ApiResponse(responseCode = "404", description = "Library not found.")
    })
    ResponseEntity<ApiResponseDTO<ResponseLibraryDTO>> findById(
        @Parameter(name = "id", description = "Library ID", required = true)
        String id
    );

    @Operation(summary = "List all libraries.")
    @ApiResponses(value = {
        @ApiResponse(
            responseCode = "200",
            description = "List of libraries retrieved successfully.",
            content = @Content(mediaType = "application/json",
                               schema = @Schema(implementation = ResponseLibraryDTO.class))
        )
    })
    ResponseEntity<ApiResponseDTO<List<ResponseLibraryDTO>>> findAll();

    @Operation(summary = "Create a new library.")
    @ApiResponses(value = {
        @ApiResponse(
            responseCode = "201",
            description = "Library created successfully.",
            content = @Content(mediaType = "application/json",
                               schema = @Schema(implementation = ResponseLibraryDTO.class))
        )
    })
    ResponseEntity<ApiResponseDTO<ResponseLibraryDTO>> create(
        @Parameter(name = "body", description = "Library data to create.", required = true)
        LibraryDTO createBo
    );

    @Operation(summary = "Update an existing library.")
    @ApiResponses(value = {
        @ApiResponse(
            responseCode = "200",
            description = "Library updated successfully.",
            content = @Content(mediaType = "application/json",
                               schema = @Schema(implementation = LibraryDTO.class))
        ),
        @ApiResponse(responseCode = "404", description = "Library not found.")
    })
    ResponseEntity<ApiResponseDTO<ResponseLibraryDTO>> update(
        @Parameter(name = "id", description = "Library ID", required = true)
        String id,
        @Parameter(name = "body", description = "Library data to update.", required = true)
        LibraryDTO updateBo
    );

    @Operation(summary = "Delete library by Id.")
    @ApiResponses(value = {
        @ApiResponse(
            responseCode = "200",
            description = "Library deleted successfully.",
            content = @Content(mediaType = "application/json",
                               schema = @Schema(implementation = ApiResponseDTO.class))
        ),
        @ApiResponse(responseCode = "404", description = "Library not found.")
    })
    ResponseEntity<ApiResponseDTO<Void>> delete(
        @Parameter(name = "id", description = "Library ID", required = true)
        String id
    );
}