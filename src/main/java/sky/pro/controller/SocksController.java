package sky.pro.controller;

import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import sky.pro.dto.SocksDto;
import sky.pro.service.SocksService;
import io.swagger.v3.oas.annotations.*

@RestController
@RequestMapping("/api/socks")
@RequiredArgsConstructor
public class SocksController {
    private final SocksService socksService;
    /**
     * Create new ads<br>
     * Use method of service {@link AdsService#createAds(CreateAdsDto, MultipartFile[])}
     *
     * @return Created ads
     */
    @ApiResponses({

            @ApiResponse(
                    responseCode = "200",
                    description = "Income of socks is successful",
                    content = {@Content(
                            mediaType = MediaType.APPLICATION_JSON_VALUE,
                            schema = @Schema(implementation = AdsDto.class)
                    )}
            ),
            @ApiResponse(
                    responseCode = "400",
                    description = "Parameters of request are absent or wrong request format"
            ),
            @ApiResponse(
                    responseCode = "500",
                    description = "Server error"
            )
    })

    @PostMapping("/income")
    public ResponseEntity<SocksDto> addAds(@RequestBody SocksDto socksDto) {
        return new ResponseEntity<>(socksService.addSocks(socksDto), HttpStatus.OK);
    }

}
