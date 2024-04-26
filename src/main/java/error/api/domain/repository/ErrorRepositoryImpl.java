package error.api.domain.repository;

import static error.api.domain.entity.QError.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.support.PageableExecutionUtils;

import com.querydsl.core.types.Projections;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQuery;
import com.querydsl.jpa.impl.JPAQueryFactory;

import error.api.dto.ErrorGetFindListDto;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class ErrorRepositoryImpl implements ErrorRepositoryCustom {

	private final JPAQueryFactory jpaQueryFactory;

	@Override
	public Page<ErrorGetFindListDto.FindListResponse> findErrorList(ErrorGetFindListDto.Request request, Pageable pageable) {

		List<ErrorGetFindListDto.FindListResponse> contents = jpaQueryFactory.select(
				Projections.constructor(
					ErrorGetFindListDto.FindListResponse.class,
					error.userUuid,
					error.projectName,
					error.code,
					error.message,
					error.createDateTime
				)).from(error)
			.where(
				userUuidEq(request.getUserUuid()),
				codeEq(request.getCode()),
				projectNameEq(request.getProjectName()),
				messageEq(request.getMessage()),
				between(request.getStartDate(), request.getEndDate())
			)
			.orderBy(error.id.desc())
			.offset(pageable.getOffset())
			.limit(pageable.getPageSize())
			.fetch();

		JPAQuery<Long> count = jpaQueryFactory.select(error.count())
			.from(error)
			.where(
				userUuidEq(request.getUserUuid()),
				codeEq(request.getCode()),
				projectNameEq(request.getProjectName()),
				messageEq(request.getMessage()),
				between(request.getStartDate(), request.getEndDate())
			);

		return PageableExecutionUtils.getPage(contents, pageable, count::fetchOne);
	}

	private BooleanExpression between(String start, String end) {

		if (StringUtils.isEmpty(start) && StringUtils.isEmpty(end)) {
			return null;
		}

		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyyMMdd");
		LocalDate date = LocalDate.parse(start, formatter);
		LocalDateTime dateTime = date.atTime(00, 00, 00);
		LocalDateTime dateTime1 = LocalDate.parse(end, formatter).atTime(LocalTime.MAX);

		return error.createDateTime.between(dateTime, dateTime1);
	}

	private BooleanExpression projectNameEq(String projectName) {
		return StringUtils.isEmpty(projectName) ? null : error.projectName.eq(projectName);
	}

	private BooleanExpression userUuidEq(String userUuid) {
		return StringUtils.isEmpty(userUuid) ? null : error.userUuid.eq(userUuid);
	}

	private BooleanExpression codeEq(String code) {
		return StringUtils.isEmpty(code) ? null : error.code.eq(code);
	}

	private BooleanExpression messageEq(String message) {
		return StringUtils.isEmpty(message) ? null : error.message.eq(message);
	}

}
