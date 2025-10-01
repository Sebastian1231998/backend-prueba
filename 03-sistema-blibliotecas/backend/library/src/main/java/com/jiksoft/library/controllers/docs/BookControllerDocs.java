package com.jiksoft.library.controllers.docs;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.jiksoft.library.dto.ApiResponseDTO;
import com.jiksoft.library.dto.request.BookDTO;
import com.jiksoft.library.dto.responses.ResponseBookDTO;
import com.jiksoft.library.exceptions.BusinessRulesListExceptions.LibraryManagementServiceException;


import java.util.List;

@Tag(name = "Book", description = "Book API")
public interface BookControllerDocs {

    @Operation(summary = "Find book by Id.")
    @ApiResponses(value = {
        @ApiResponse(
            responseCode = "200",
            description = "Book found successfully.",
            content = @Content(mediaType = APPLICATION_JSON_VALUE,
                               schema = @Schema(implementation = ResponseBookDTO.class))
        ),
        @ApiResponse(responseCode = "404", description = "Book not found.")
    })
    ResponseEntity<ApiResponseDTO<ResponseBookDTO>> findById(
        @Parameter(name = "id", description = "Book ID", required = true)
        String id
    ) throws JsonProcessingException;

    @Operation(summary = "List books by library ID.")
    @ApiResponses(value = {
        @ApiResponse(
            responseCode = "200",
            description = "List of books retrieved successfully.",
            content = @Content(mediaType = APPLICATION_JSON_VALUE,
                               schema = @Schema(implementation = ResponseBookDTO.class))
        )
    })
    ResponseEntity<ApiResponseDTO<List<ResponseBookDTO>>> listByLibraryId(
        @PathVariable("library_id")
        @Parameter(name = "library_id", description = "Library ID", required = true)
        String libraryId
    ) throws LibraryManagementServiceException;

    @Operation(summary = "Create a new book.")
    @ApiResponses(value = {
        @ApiResponse(
            responseCode = "201",
            description = "Book created successfully.",
            content = @Content(mediaType = APPLICATION_JSON_VALUE,
                               schema = @Schema(implementation = ResponseBookDTO.class))
        )
    })
    ResponseEntity<ApiResponseDTO<ResponseBookDTO>> create(
        @Parameter(name = "body", description = "Book data to create.", required = true)
        BookDTO createDto
    ) throws JsonProcessingException, LibraryManagementServiceException;


    @Operation(summary = "Delete book by Id.")
    @ApiResponses(value = {
        @ApiResponse(
            responseCode = "200",
            description = "Book deleted successfully.",
            content = @Content(mediaType = APPLICATION_JSON_VALUE,
                               schema = @Schema(implementation = ApiResponseDTO.class))
        ),
        @ApiResponse(responseCode = "404", description = "Book not found.")
    })
    ResponseEntity<ApiResponseDTO<Void>> delete(
        String id
    ) throws JsonProcessingException;
}