package error.api.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import error.api.domain.repository.ErrorRepository;
import error.api.dto.ErrorGetFindListDto;
import error.api.dto.ErrorRegisterDto;
import error.api.dto.RestResponse;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class ErrorService {

	private final ErrorRepository errorRepository;

	public List<ErrorGetFindListDto.FindListResponse> getFindError(ErrorGetFindListDto.Request request, Pageable pageable) {

		Page<ErrorGetFindListDto.FindListResponse> errorList = errorRepository.findErrorList(request, pageable);
		return errorList.getContent();
	}

	@Transactional
	public RestResponse errorRegister(ErrorRegisterDto.request request) {

		errorRepository.save(request.toEntity());

		return new RestResponse(true, null);
	}
}
