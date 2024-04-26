package error.api.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import error.api.dto.ErrorGetFindListDto;
import error.api.dto.ErrorRegisterDto;
import error.api.dto.RestResponse;
import error.api.service.ErrorService;
import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/v1/log")
public class ErrorApiController {

	private final ErrorService errorService;

	@GetMapping
	public List<ErrorGetFindListDto.FindListResponse> getFindListError(
		ErrorGetFindListDto.Request request,
		@PageableDefault(sort = "created_at", direction = Sort.Direction.DESC) Pageable pageable
	) {
		return errorService.getFindError(request, pageable);
	}

	@PostMapping
	public RestResponse errorRegister(
		@Valid @RequestBody ErrorRegisterDto.request request
	) {
		return errorService.errorRegister(request);
	}
}
