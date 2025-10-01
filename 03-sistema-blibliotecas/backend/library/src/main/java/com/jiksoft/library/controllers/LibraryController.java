package com.jiksoft.library.controllers;


import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.jiksoft.library.bo.LibraryBO;
import com.jiksoft.library.controllers.docs.LibraryControllerDocs;
import com.jiksoft.library.dto.ApiResponseDTO;
import com.jiksoft.library.dto.request.LibraryDTO;
import com.jiksoft.library.dto.responses.ResponseLibraryDTO;
import com.jiksoft.library.mappers.LibraryMapper;
import com.jiksoft.library.services.LibraryService;

import jakarta.validation.Valid;


@RestController
@Slf4j
@RequestMapping(value = "/v1/libraries")
@AllArgsConstructor
public class LibraryController implements LibraryControllerDocs {

    private final LibraryService service;
    private final LibraryMapper mapper;

    @Override
    @GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<ApiResponseDTO<ResponseLibraryDTO>> findById(@PathVariable String id) {
        LibraryBO responseBo = service.findById(id);
        ResponseLibraryDTO responseDto = mapper.fromBoToResponseDto(responseBo);

        return ResponseEntity.status(HttpStatus.OK)
            .body(ApiResponseDTO.<ResponseLibraryDTO>builder()
                .statusCode(HttpStatus.OK.value())
                .message("Library retrieved successfully.")
                .body(responseDto)
                .build());
    }

    @Override
    @GetMapping(value = "/list", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<ApiResponseDTO<List<ResponseLibraryDTO>>> findAll() {
        
        List<LibraryBO> responseList = service.findAll();
        List<ResponseLibraryDTO> dtoList = mapper.fromListBoToListResponseDto(responseList);

        return ResponseEntity.status(HttpStatus.OK)
            .body(ApiResponseDTO.<List<ResponseLibraryDTO>>builder()
                .statusCode(HttpStatus.OK.value())
                .message("Libraries list retrieved successfully.")
                .body(dtoList)
                .build());
    }

    @Override
    @PostMapping(value = "", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<ApiResponseDTO<ResponseLibraryDTO>> create(@Valid @RequestBody LibraryDTO createDto) {

        LibraryBO createBo = mapper.fromDtoToResponseBo(createDto);

        LibraryBO responseBo = service.create(createBo);
        ResponseLibraryDTO responseDto = mapper.fromBoToResponseDto(responseBo);

        return ResponseEntity.status(HttpStatus.CREATED)
            .body(ApiResponseDTO.<ResponseLibraryDTO>builder()
                .statusCode(HttpStatus.CREATED.value())
                .message("Library created successfully.")
                .body(responseDto)
                .build());
    }

    @Override
    @PutMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<ApiResponseDTO<ResponseLibraryDTO>> update(@PathVariable String id,
                                                                     @Valid LibraryDTO updateDto) {

        LibraryBO updateBo = mapper.fromDtoToResponseBo(updateDto);                                                                
        LibraryBO responseBo = service.update(id, updateBo);
        ResponseLibraryDTO responseDto = mapper.fromBoToResponseDto(responseBo);

        return ResponseEntity.status(HttpStatus.OK)
            .body(ApiResponseDTO.<ResponseLibraryDTO>builder()
                .statusCode(HttpStatus.OK.value())
                .message("Library updated successfully.")
                .body(responseDto)
                .build());
    }

    @Override
    @DeleteMapping(value = "/{id}")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<ApiResponseDTO<Void>> delete(@PathVariable String id) {
        service.delete(id);
        return ResponseEntity.status(HttpStatus.OK)
            .body(ApiResponseDTO.<Void>builder()
                .statusCode(HttpStatus.OK.value())
                .message("Library deleted successfully.")
                .build());
    }
}