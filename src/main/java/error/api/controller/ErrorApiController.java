package error.api.controller;


import error.api.dto.ErrorGetFindListDto;
import error.api.dto.ErrorRegisterDto;
import error.api.dto.RestResponse;
import error.api.service.ErrorService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/v1/log")
public class ErrorApiController {

    private final ErrorService errorService;

    @GetMapping
    public List<ErrorGetFindListDto.FindListResponse> getFindListError(ErrorGetFindListDto.request request) {

       return errorService.getFindError(request);
    }

    @PostMapping
    public RestResponse errorRegister(
            @Valid @RequestBody ErrorRegisterDto.request request
    ) {
        return errorService.errorRegister(request);
    }
}
