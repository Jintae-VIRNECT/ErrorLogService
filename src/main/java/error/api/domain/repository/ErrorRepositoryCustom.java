package error.api.domain.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import error.api.dto.ErrorGetFindListDto;

public interface ErrorRepositoryCustom {

	Page<ErrorGetFindListDto.FindListResponse> findErrorList(ErrorGetFindListDto.Request request, Pageable pageable);
}
