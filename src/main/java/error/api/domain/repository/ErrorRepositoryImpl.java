package error.api.domain.repository;


import com.querydsl.core.BooleanBuilder;
import com.querydsl.core.types.Projections;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.core.types.dsl.Expressions;
import com.querydsl.core.types.dsl.StringExpression;
import com.querydsl.jpa.impl.JPAQueryFactory;
import error.api.domain.entity.QError;
import error.api.dto.ErrorGetFindListDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.RequestEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

@RequiredArgsConstructor
public class ErrorRepositoryImpl implements ErrorRepositoryCustom {

    private final JPAQueryFactory jpaQueryFactory;

    QError qe = QError.error;

    @Override
    public List<ErrorGetFindListDto.FindListResponse> findErrorList(ErrorGetFindListDto.request request) {

        return jpaQueryFactory.select(
            Projections.constructor(ErrorGetFindListDto.FindListResponse.class,
                qe.userUuid,
                qe.projectName,
                qe.code,
                qe.message,
                qe.createDateTime
        )).from(qe)
                .where(
                        userUuidEq(request.getUser_uuid()),
                        codeEq(request.getCode()),
                        projectNameEq(request.getProject_name()),
                        messageEq(request.getMessage()),
                        between(request.getStart_date(), request.getEnd_date()))
                .orderBy(qe.id.desc())
                .fetch();
    }

    private BooleanExpression between(String start, String end) {


        if(start != null || end != null) {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyyMMdd");
            LocalDate date = LocalDate.parse(start, formatter);
            LocalDateTime dateTime = date.atTime(00,00,00);

            LocalDateTime dateTime1 = LocalDate.parse(end, formatter).atTime(LocalTime.MAX);

            return qe.createDateTime.between(dateTime, dateTime1);
        }

        return null;
    }

    private BooleanExpression projectNameEq(String projectName) {
        return StringUtils.hasText(projectName) ? qe.projectName.eq(projectName) : null;
    }

    private BooleanExpression userUuidEq(String userUuid) {
        return StringUtils.hasText(userUuid) ? qe.userUuid.eq(userUuid) : null;
    }

    private BooleanExpression codeEq(String code) {
        return StringUtils.hasText(code) ? qe.code.eq(code) : null;
    }

    private BooleanExpression messageEq(String message) {
        return StringUtils.hasText(message) ? qe.message.eq(message) : null;
    }

}
