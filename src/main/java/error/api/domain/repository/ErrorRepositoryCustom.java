package error.api.domain.repository;

import error.api.dto.ErrorGetFindListDto;

import java.util.List;

public interface ErrorRepositoryCustom {

    List<ErrorGetFindListDto.FindListResponse> findErrorList(ErrorGetFindListDto.request request);
}
