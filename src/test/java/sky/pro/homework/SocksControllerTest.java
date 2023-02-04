package sky.pro.homework;

import com.fasterxml.jackson.databind.ObjectMapper;
import net.minidev.json.JSONObject;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.mock.mockito.SpyBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.web.context.WebApplicationContext;
import sky.pro.homework.controller.SocksController;
import sky.pro.homework.dto.SocksDto;
import sky.pro.homework.mapper.SocksMapper;
import sky.pro.homework.model.EnumOperations;
import sky.pro.homework.model.Socks;
import sky.pro.homework.repository.SocksRepository;
import sky.pro.homework.service.impl.SocksServiceImpl;

import java.util.ArrayList;
import java.util.List;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static sky.pro.homework.ConstantsForTests.*;

@WebMvcTest(SocksController.class)
@ExtendWith(SpringExtension.class)
@AutoConfigureMockMvc(addFilters = false)
public class SocksControllerTest {
    Socks socks = new Socks();
    List<Socks> socksList = new ArrayList<>();
    SocksDto socksDto = new SocksDto();
    String defaultCottonpart;
    String wrongCottonpart;
    JSONObject jsonObject = new JSONObject();
    JSONObject jsonObjectForGet = new JSONObject();

    JSONObject jsonObjWithWrongCottonPart = new JSONObject();

    JSONObject jsonObjWithWrongQuantity = new JSONObject();
    EnumOperations enumOperations;

    @Autowired
    private WebApplicationContext webApplicationContext;
    @Autowired
    private ObjectMapper objectMapper;
    @Autowired
    private MockMvc mockMvc;
    @SpyBean
    private SocksServiceImpl socksService;

    @MockBean
    private SocksRepository socksRepository;
    @MockBean
    SocksMapper socksMapper;

    @BeforeEach
    void setUp() {
        socks.setId(DEFAULT_ID);
        socks.setColor(DEFAULT_COLOR);
        socks.setCottonPart(DEFAULT_COTTON_PART);
        socks.setQuantity(DEFAULT_QUANTITY);

        socksList.add(socks);

        socksDto.setColor(DEFAULT_COLOR);
        socksDto.setCottonPart(DEFAULT_COTTON_PART);
        socksDto.setQuantity(DEFAULT_QUANTITY);

        defaultCottonpart = Integer.valueOf(DEFAULT_COTTON_PART).toString();
        wrongCottonpart = Integer.valueOf(WRONG_COTTON_PART).toString();

        jsonObject.put("color", DEFAULT_COLOR);
        jsonObject.put("cottonPart", DEFAULT_COTTON_PART);
        jsonObject.put("quantity", DEFAULT_QUANTITY);

        jsonObjWithWrongCottonPart.put("color", DEFAULT_COLOR);
        jsonObjWithWrongCottonPart.put("cottonPart", WRONG_COTTON_PART);
        jsonObjWithWrongCottonPart.put("quantity", DEFAULT_QUANTITY);

        jsonObjWithWrongQuantity.put("color", DEFAULT_COLOR);
        jsonObjWithWrongQuantity.put("cottonPart", DEFAULT_COTTON_PART);
        jsonObjWithWrongQuantity.put("quantity", WRONG_QUANTITY);

        jsonObjectForGet.put("color", DEFAULT_COLOR);
        jsonObjectForGet.put("operation", DEFAULT_OPERATION);
        jsonObjectForGet.put("cottonPart", DEFAULT_COTTON_PART);
    }

    @Test
    public void testAddSocksStatusOk() throws Exception {
        when(socksMapper.toSocks(any())).thenReturn(socks);
        when(socksRepository.save(any())).thenReturn(socks);
        when(socksMapper.toDto(any())).thenReturn(socksDto);
        mockMvc.perform(MockMvcRequestBuilders
                        .post("/api/socks/income")
                        .content(jsonObject.toJSONString())
                        .contentType(MediaType.APPLICATION_JSON_VALUE)
                        .accept(MediaType.APPLICATION_JSON_VALUE))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.color").value(DEFAULT_COLOR))
                .andExpect(jsonPath("$.cottonPart").value(DEFAULT_COTTON_PART))
                .andExpect(jsonPath("$.quantity").value(DEFAULT_QUANTITY));
    }

    @Test
    public void testAddSocksStatusBadRequestIfWrongCottonPart() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders
                        .post("/api/socks/income")
                        .content(jsonObjWithWrongCottonPart.toJSONString())
                        .contentType(MediaType.APPLICATION_JSON_VALUE)
                        .accept(MediaType.APPLICATION_JSON_VALUE))
                .andDo(print())
                .andExpect(status().isBadRequest());
    }

    @Test
    public void testAddSocksStatusBadRequestIfWrongQuantity() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders
                        .post("/api/socks/income")
                        .content(jsonObjWithWrongQuantity.toJSONString())
                        .contentType(MediaType.APPLICATION_JSON_VALUE)
                        .accept(MediaType.APPLICATION_JSON_VALUE))
                .andDo(print())
                .andExpect(status().isBadRequest());
    }

// -----------------------

    @Test
    public void testRemoveSocksStatusOk() throws Exception {
        when(socksMapper.toSocks(any())).thenReturn(socks);
        doNothing().when(socksRepository).delete(any());
        when(socksRepository.save(any())).thenReturn(socks);
        when(socksMapper.toDto(any())).thenReturn(socksDto);
        mockMvc.perform(MockMvcRequestBuilders
                        .post("/api/socks/income")
                        .content(jsonObject.toJSONString())
                        .contentType(MediaType.APPLICATION_JSON_VALUE)
                        .accept(MediaType.APPLICATION_JSON_VALUE))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.color").value(DEFAULT_COLOR))
                .andExpect(jsonPath("$.cottonPart").value(DEFAULT_COTTON_PART))
                .andExpect(jsonPath("$.quantity").value(DEFAULT_QUANTITY));
    }

    @Test
    public void testRemoveSocksStatusBadRequestIfWrongCottonPart() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders
                        .post("/api/socks/outcome")
                        .content(jsonObjWithWrongCottonPart.toJSONString())
                        .contentType(MediaType.APPLICATION_JSON_VALUE)
                        .accept(MediaType.APPLICATION_JSON_VALUE))
                .andDo(print())
                .andExpect(status().isBadRequest());
    }

    @Test
    public void testRemoveSocksStatusBadRequestIfWrongQuantity() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders
                        .post("/api/socks/outcome")
                        .content(jsonObjWithWrongQuantity.toJSONString())
                        .contentType(MediaType.APPLICATION_JSON_VALUE)
                        .accept(MediaType.APPLICATION_JSON_VALUE))
                .andDo(print())
                .andExpect(status().isBadRequest());
    }

    // -------------

    @Test
    public void testGetSocksStatusOk() throws Exception {
        when(socksRepository.findAllByColorIgnoreCaseAndCottonPart(DEFAULT_COLOR, DEFAULT_COTTON_PART))
                .thenReturn(socksList);
        when(socksMapper.toDto(any())).thenReturn(socksDto);
        mockMvc.perform(MockMvcRequestBuilders
                        .get("/api/socks")
                        .param("color", DEFAULT_COLOR)
                        .param("operation", DEFAULT_OPERATION)
                        .param("cottonPart", defaultCottonpart)
                        .contentType(MediaType.APPLICATION_JSON_VALUE)
                        .accept(MediaType.APPLICATION_JSON_VALUE))
                .andDo(print())
                .andExpect(status().isOk());
    }
    @Test
    public void testGetSocksStatusBadRequestIfWrongCottonPart() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders
                        .get("/api/socks")
                        .param("color", DEFAULT_COLOR)
                        .param("operation", DEFAULT_OPERATION)
                        .param("cottonPart", wrongCottonpart)
                        .contentType(MediaType.APPLICATION_JSON_VALUE)
                        .accept(MediaType.APPLICATION_JSON_VALUE))
                .andDo(print())
                .andExpect(status().isBadRequest());
    }


}
