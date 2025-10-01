package com.jiksoft.library.controllers;

import java.util.List;

import jakarta.validation.Valid;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.jiksoft.library.annotations.BasicLog;
import com.jiksoft.library.bo.BookBO;
import com.jiksoft.library.controllers.docs.BookControllerDocs;
import com.jiksoft.library.dto.ApiResponseDTO;
import com.jiksoft.library.dto.request.BookDTO;
import com.jiksoft.library.dto.responses.ResponseBookDTO;
import com.jiksoft.library.exceptions.BusinessRulesListExceptions.LibraryManagementServiceException;

import com.jiksoft.library.mappers.BookMapper;
import com.jiksoft.library.services.BookService;

@RestController
@Slf4j
@RequestMapping(value = "/v1/books")
@AllArgsConstructor
public class BookController implements BookControllerDocs{

    private final BookService service;

    private final BookMapper mapper;

    @Override
    @BasicLog
    @GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<ApiResponseDTO<ResponseBookDTO>> findById(@PathVariable String id) throws JsonProcessingException {
        BookBO responseBo = service.findById(id);
        ResponseBookDTO responseDto = mapper.fromBoToResponseDto(responseBo);

        return ResponseEntity.status(HttpStatus.OK)
            .body(ApiResponseDTO.<ResponseBookDTO>builder()
                .statusCode(HttpStatus.OK.value())
                .message("Object retrieved successfully.")
                .body(responseDto)
                .build());
    }

    @Override
    @BasicLog
    @GetMapping(value = "/list/{library_id}", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<ApiResponseDTO<List<ResponseBookDTO>>> listByLibraryId(@PathVariable("library_id") String id) throws LibraryManagementServiceException{
        List<BookBO> responseListBo = service.listByLibraryId(id);
        List<ResponseBookDTO> responseListDto = mapper.fromListBoToListResponseDto(
            responseListBo);

        return ResponseEntity.status(HttpStatus.OK)
            .body(ApiResponseDTO.<List<ResponseBookDTO>>builder()
                .statusCode(HttpStatus.OK.value())
                .message("List retrieved successfully.")
                .body(responseListDto)
                .build());
    }


    @Override
    @BasicLog
    @PostMapping(value = "", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<ApiResponseDTO<ResponseBookDTO>> create(@Valid @RequestBody BookDTO createDto)
        throws JsonProcessingException, LibraryManagementServiceException {
        BookBO requestBo = mapper.fromDtoToBo(createDto);
        BookBO responseBo = service.create(requestBo);
        ResponseBookDTO responseDto = mapper.fromBoToResponseDto(responseBo);

        return ResponseEntity.status(HttpStatus.CREATED)
            .body(ApiResponseDTO.<ResponseBookDTO>builder()
                .statusCode(HttpStatus.CREATED.value())
                .message("Object created successfully.")
                .body(responseDto)
                .build());
    }

    @Override
    @BasicLog
    @DeleteMapping(value = "/{id}")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<ApiResponseDTO<Void>> delete(@PathVariable String id) throws JsonProcessingException {
        service.delete(id);

        return ResponseEntity.status(HttpStatus.OK).body(
            ApiResponseDTO.<Void>builder()
                .statusCode(HttpStatus.OK.value())
                .message("Object deleted successfully")
                .build());
    }

}
