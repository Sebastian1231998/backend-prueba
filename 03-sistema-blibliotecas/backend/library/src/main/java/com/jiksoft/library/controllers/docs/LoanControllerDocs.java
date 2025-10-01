package com.jiksoft.library.controllers.docs;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import com.jiksoft.library.dto.ApiResponseDTO;
import com.jiksoft.library.dto.responses.ResponseLoanDTO;
import com.jiksoft.library.exceptions.BusinessRulesListExceptions.LoanException;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import java.util.List;

@Tag(name = "Loan", description = "Loan API")
public interface LoanControllerDocs {

    @Operation(summary = "Borrow a book.")
    @ApiResponses(value = {
        @ApiResponse(
            responseCode = "201",
            description = "Book borrowed successfully.",
            content = @Content(mediaType = "application/json",
                               schema = @Schema(implementation = ResponseLoanDTO.class))
        ),
        @ApiResponse(responseCode = "404", description = "Book not found."),
        @ApiResponse(responseCode = "400", description = "Book is currently loaned by another user.")
    })
    ResponseEntity<ApiResponseDTO<ResponseLoanDTO>> borrowBook(
        @RequestParam
        @Parameter(name = "userId", description = "ID of the user borrowing the book", required = true)
        String userId,
        @RequestParam
        @Parameter(name = "bookId", description = "ID of the book to borrow", required = true)
        String bookId
    ) throws LoanException;

    @Operation(summary = "Return a borrowed book.")
    @ApiResponses(value = {
        @ApiResponse(
            responseCode = "200",
            description = "Book returned successfully.",
            content = @Content(mediaType = "application/json",
                               schema = @Schema(implementation = ResponseLoanDTO.class))
        ),
        @ApiResponse(responseCode = "404", description = "Loan not found or already returned.")
    })
    ResponseEntity<ApiResponseDTO<ResponseLoanDTO>> returnBook(
        @RequestParam
        @Parameter(name = "userId", description = "ID of the user returning the book", required = true)
        String userId,
        @RequestParam
        @Parameter(name = "bookId", description = "ID of the book to return", required = true)
        String bookId
    ) throws LoanException;

    @Operation(summary = "List loans of a user.")
    @ApiResponses(value = {
        @ApiResponse(
            responseCode = "200",
            description = "User loans retrieved successfully.",
            content = @Content(mediaType = "application/json",
                               schema = @Schema(implementation = ResponseLoanDTO.class))
        )
    })
    ResponseEntity<ApiResponseDTO<List<ResponseLoanDTO>>> listUserLoans(
        @PathVariable
        @Parameter(name = "userId", description = "ID of the user to retrieve loans for", required = true)
        String userId
    );
}