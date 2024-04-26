package error.api.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import error.api.dto.ErrorRegisterDto;
import error.api.service.ErrorService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.client.match.MockRestRequestMatchers;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@WebMvcTest(controllers = ErrorApiController.class)
class ErrorApiControllerTest {

    @MockBean
    private ErrorService errorService;

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    @DisplayName("로그를 저장한다.")
    void createLog() throws Exception {
        //given
        ErrorRegisterDto.request request = ErrorRegisterDto.request.builder()
                .userUuid("22")
                .projectName("44")
                .code("3333")
                .message("ㅇㅇㅇㅇㅇ")
                .build();

        //when then
        mockMvc.perform(post("/v1/log")
                        .content(objectMapper.writeValueAsString(request))
                        .contentType(MediaType.APPLICATION_JSON)
        )
                .andDo(print())
                .andExpect(status().isOk());

    }

    @Test
    @DisplayName("로그에 대한 예외처리가 동작되는지 체크 한다.")
    void createFieldVaildLog() throws Exception {
        //given
        ErrorRegisterDto.request request = ErrorRegisterDto.request.builder()
                .projectName("44")
                .code("3333")
                .message("ㅇㅇㅇㅇㅇ")
                .build();


        //when then
        mockMvc.perform(post("/v1/log")
                        .content(objectMapper.writeValueAsString(request))
                        .contentType(MediaType.APPLICATION_JSON)
                )
                .andDo(print())
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.success").value("false"))
                .andExpect(jsonPath("$.error_message").value("유효성 검사 실패 : must not be blank"));


    }
}