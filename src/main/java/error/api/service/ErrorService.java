package error.api.service;


import error.api.domain.entity.Error;
import error.api.domain.repository.ErrorRepository;
import error.api.dto.ErrorGetFindListDto;
import error.api.dto.ErrorRegisterDto;
import error.api.dto.RestResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class ErrorService {

    private final ErrorRepository errorRepository;

    public List<ErrorGetFindListDto.FindListResponse> getFindError(ErrorGetFindListDto.request request) {

        return errorRepository.findErrorList(request);
    }

    @Transactional
    public RestResponse errorRegister(ErrorRegisterDto.request request) {

        errorRepository.save(request.toEntity());

        return  new RestResponse(true, null);
    }
}
