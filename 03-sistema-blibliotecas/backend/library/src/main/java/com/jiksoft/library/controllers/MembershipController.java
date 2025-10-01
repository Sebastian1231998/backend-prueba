package com.jiksoft.library.controllers;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.jiksoft.library.bo.MembershipBO;
import com.jiksoft.library.controllers.docs.MembershipControllerDocs;
import com.jiksoft.library.dto.ApiResponseDTO;
import com.jiksoft.library.dto.request.MembershipDTO;
import com.jiksoft.library.dto.responses.ResponseMembershipDTO;
import com.jiksoft.library.mappers.MembershipMapper;
import com.jiksoft.library.services.MembershipService;

import jakarta.validation.Valid;

import java.util.List;

@RestController
@Slf4j
@RequestMapping(value = "/v1/memberships")
@AllArgsConstructor
public class MembershipController implements MembershipControllerDocs{

    private final MembershipService membershipService;
    private final MembershipMapper membershipMapper;

    @Override
    @GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<ApiResponseDTO<ResponseMembershipDTO>> findById(@PathVariable String id) {
        MembershipBO bo = membershipService.findById(id);
        ResponseMembershipDTO dto = membershipMapper.fromBoToResponseDto(bo);
        return ResponseEntity.status(HttpStatus.OK)
                .body(ApiResponseDTO.<ResponseMembershipDTO>builder()
                        .statusCode(HttpStatus.OK.value())
                        .message("Membership retrieved successfully.")
                        .body(dto)
                        .build());
    }

    @Override
    @GetMapping(value = "/list", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<ApiResponseDTO<List<ResponseMembershipDTO>>> findAll() {
        List<MembershipBO> bos = membershipService.findAll();
        List<ResponseMembershipDTO> dtos = membershipMapper.fromListBoToListResponseDto(bos);
        return ResponseEntity.status(HttpStatus.OK)
                .body(ApiResponseDTO.<List<ResponseMembershipDTO>>builder()
                        .statusCode(HttpStatus.OK.value())
                        .message("Membership list retrieved successfully.")
                        .body(dtos)
                        .build());
    }

    @Override
    @PostMapping(value = "", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<ApiResponseDTO<ResponseMembershipDTO>> create(@Valid @RequestBody MembershipDTO createDto) {

        MembershipBO createBo = membershipMapper.fromDtoToBo(createDto);  
        MembershipBO bo = membershipService.create(createBo);
        ResponseMembershipDTO dto = membershipMapper.fromBoToResponseDto(bo);
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(ApiResponseDTO.<ResponseMembershipDTO>builder()
                        .statusCode(HttpStatus.CREATED.value())
                        .message("Membership created successfully.")
                        .body(dto)
                        .build());
    }


    @Override
    @DeleteMapping(value = "/{id}")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<ApiResponseDTO<Void>> delete(@PathVariable String id) {
        membershipService.delete(id);
        return ResponseEntity.status(HttpStatus.OK)
                .body(ApiResponseDTO.<Void>builder()
                        .statusCode(HttpStatus.OK.value())
                        .message("Membership deleted successfully.")
                        .build());
    }

}
