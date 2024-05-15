package com.khanhnq.cards.controller;

import com.khanhnq.cards.constants.CardsConstants;
import com.khanhnq.cards.dto.CardsContactInfoDto;
import com.khanhnq.cards.dto.CardsDto;
import com.khanhnq.cards.dto.ErrorResponseDto;
import com.khanhnq.cards.dto.ResponseDto;
import com.khanhnq.cards.service.ICardsService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Pattern;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@Tag(
        name = "CRUD REST APIs for Cards",
        description = "CRUD REST APIs to CREATE, UPDATE, FETCH AND DELETE card details"
)
@RestController
@RequestMapping(path="/api", produces = {MediaType.APPLICATION_JSON_VALUE})
@Validated
public class CardsController {

    private ICardsService cardsService;

    public CardsController(ICardsService cardsService) {
        this.cardsService = cardsService;
    }

    @Autowired
    private CardsContactInfoDto cardsContactInfo;

    @Operation(
            summary = "Create Card REST API",
            description = "REST API to create new Card"
    )
    @ApiResponses({
            @ApiResponse(
                    responseCode = "201",
                    description = "HTTP Status CREATED"
            ),
            @ApiResponse(
                    responseCode = "500",
                    description = "HTTP Status Internal Server Error",
                    content = @Content(
                            schema = @Schema(implementation = ErrorResponseDto.class)
                    )
            )
    }
    )
    @PostMapping("/create")
    public ResponseEntity<ResponseDto> createCard(@RequestParam @Pattern(regexp="(^$|[0-9]{10})",message = "Mobile number must be 10 digits") String mobileNumber) {
        cardsService.createCard(mobileNumber);
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(ResponseDto.builder().statusCode(CardsConstants.STATUS_201).statusMsg(CardsConstants.MESSAGE_201).build());
    }

    @Operation(
            summary = "Fetch Card Details REST API",
            description = "REST API to fetch card details based on a mobile number"
    )
    @ApiResponses({
            @ApiResponse(
                    responseCode = "200",
                    description = "HTTP Status OK"
            ),
            @ApiResponse(
                    responseCode = "500",
                    description = "HTTP Status Internal Server Error",
                    content = @Content(
                            schema = @Schema(implementation = ErrorResponseDto.class)
                    )
            )
    })
    @GetMapping("/fetch")
    public ResponseEntity<CardsDto> fetchCardDetails(@RequestParam @Pattern(regexp="(^$|[0-9]{10})",message = "Mobile number must be 10 digits") String mobileNumber) {
        CardsDto cardsDto = cardsService.fetchCard(mobileNumber);
        return ResponseEntity.status(HttpStatus.OK).body(cardsDto);
    }

    @Operation(
            summary = "Update Card Details REST API",
            description = "REST API to update card details based on a card number"
    )
    @ApiResponses({
            @ApiResponse(
                    responseCode = "200",
                    description = "HTTP Status OK"
            ),
            @ApiResponse(
                    responseCode = "417",
                    description = "Expectation Failed"
            ),
            @ApiResponse(
                    responseCode = "500",
                    description = "HTTP Status Internal Server Error",
                    content = @Content(
                            schema = @Schema(implementation = ErrorResponseDto.class)
                    )
            )
    })
    @PostMapping("/update")
    public ResponseEntity<ResponseDto> updateCard(@Valid @RequestBody CardsDto cardsDto) {
        boolean isUpdated = cardsService.updateCard(cardsDto);
        ResponseDto responseDto;
        if (isUpdated) {
            responseDto = ResponseDto.builder()
                    .statusCode(CardsConstants.STATUS_200)
                    .statusMsg(CardsConstants.MESSAGE_200)
                    .build();
            return new ResponseEntity<>(responseDto, HttpStatus.OK);
        }
        responseDto = ResponseDto.builder()
                .statusCode(CardsConstants.STATUS_417)
                .statusMsg(CardsConstants.MESSAGE_417_UPDATE)
                .build();
        return new ResponseEntity<>(responseDto, HttpStatus.EXPECTATION_FAILED);
    }

    @Operation(
            summary = "Delete Card Details REST API",
            description = "REST API to delete Card details based on a mobile number"
    )
    @ApiResponses({
            @ApiResponse(
                    responseCode = "200",
                    description = "HTTP Status OK"
            ),
            @ApiResponse(
                    responseCode = "417",
                    description = "Expectation Failed"
            ),
            @ApiResponse(
                    responseCode = "500",
                    description = "HTTP Status Internal Server Error",
                    content = @Content(
                            schema = @Schema(implementation = ErrorResponseDto.class)
                    )
            )
    })
    @DeleteMapping("/delete")
    public ResponseEntity<ResponseDto> deleteCard(@RequestParam @Pattern(regexp="(^$|[0-9]{10})",message = "Mobile number must be 10 digits") String mobileNumber) {
        boolean isUpdated = cardsService.deleteCard(mobileNumber);
        ResponseDto responseDto;
        if (isUpdated) {
            responseDto = ResponseDto.builder()
                    .statusCode(CardsConstants.STATUS_200)
                    .statusMsg(CardsConstants.MESSAGE_200)
                    .build();
            return new ResponseEntity<>(responseDto, HttpStatus.OK);
        }
        responseDto = ResponseDto.builder()
                .statusCode(CardsConstants.STATUS_417)
                .statusMsg(CardsConstants.MESSAGE_417_DELETE)
                .build();
        return new ResponseEntity<>(responseDto, HttpStatus.EXPECTATION_FAILED);
    }

    @Operation(
            summary = "Fetch Cards Contact Information REST API",
            description = "REST API to fetch Cards Contact Information"
    )
    @ApiResponses({
            @ApiResponse(
                    responseCode = "200",
                    description = "Http Status OK"
            ),
            @ApiResponse(
                    responseCode = "500",
                    description = "HTTP Status Internal Server Error",
                    content = @Content(
                            schema = @Schema(implementation = ErrorResponseDto.class)
                    )
            )
    })
    @GetMapping("/contact-info")
    public ResponseEntity<CardsContactInfoDto> getContactInfo() {
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(cardsContactInfo);
    }
}
