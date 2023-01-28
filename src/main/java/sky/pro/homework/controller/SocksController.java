package sky.pro.homework.controller;

import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import sky.pro.homework.dto.SocksDto;
import sky.pro.homework.exception.WrongRequestParametersException;
import sky.pro.homework.model.EnumOperations;
import sky.pro.homework.service.SocksService;

@RestController
@Slf4j
@RequestMapping("api/socks")
@RequiredArgsConstructor
public class SocksController {
    private final SocksService socksService;
    /**
     * Add socks to DB
     * Use method of service {@link SocksService#addSocks(SocksDto)}
     *
     * @return updated socks
     */
    @ApiResponses({

            @ApiResponse(
                    responseCode = "200",
                    description = "Income of socks is successful",
                    content = {@Content(
                            mediaType = MediaType.APPLICATION_JSON_VALUE,
                            schema = @Schema(implementation = SocksDto.class)
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

    @PostMapping("income")
    public ResponseEntity<SocksDto> addAds(@RequestBody SocksDto socksDto) {
        if (socksDto.getQuantity() < 0 || socksDto.getCottonPart() < 0 || socksDto.getCottonPart() > 100) {
            log.warn("Wrong input parameters!");
            throw new WrongRequestParametersException();
        }
        return new ResponseEntity<>(socksService.addSocks(socksDto), HttpStatus.OK);
    }

    /**
     * Exclude socks from DB
     * Use method of service {@link SocksService#removeSocks(SocksDto)}
     *
     * @return updated socks
     */
    @ApiResponses({

            @ApiResponse(
                    responseCode = "200",
                    description = "Outcome of socks is successful",
                    content = {@Content(
                            mediaType = MediaType.APPLICATION_JSON_VALUE,
                            schema = @Schema(implementation = SocksDto.class)
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

    @PostMapping("outcome")
    public ResponseEntity<SocksDto> removeAds(@RequestBody SocksDto socksDto) {
        if (socksDto.getQuantity() < 0 || socksDto.getCottonPart() < 0 || socksDto.getCottonPart() > 100) {
            log.warn("Wrong input parameters!");
            throw new WrongRequestParametersException();
        }
        return new ResponseEntity<>(socksService.removeSocks(socksDto), HttpStatus.OK);
    }



    /**
     * Return total quantity of socks
     * Use method of service {@link SocksService#getSocks(String, EnumOperations, int)}
     *
     * @return Created ads
     */
    @ApiResponses({
            @ApiResponse(
                    responseCode = "200",
                    description = "Income of socks is successful",
                    content = {@Content(
                            mediaType = MediaType.APPLICATION_JSON_VALUE,
                            schema = @Schema(implementation = SocksDto.class)
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
    @GetMapping
    public ResponseEntity<Integer> getSocks(@RequestParam String color,
                                           @RequestParam EnumOperations operation,
                                           @RequestParam int cottonPart) {
        if (cottonPart < 0 || cottonPart > 100) {
            log.warn("Wrong input parameters!");
            throw new WrongRequestParametersException();
        }
        return new ResponseEntity<>(socksService.getSocks(color, operation, cottonPart), HttpStatus.OK);
    }

}
