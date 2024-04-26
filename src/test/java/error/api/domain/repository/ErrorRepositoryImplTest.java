package error.api.domain.repository;

import error.api.domain.entity.Error;
import error.api.dto.ErrorGetFindListDto;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.tuple;
import static org.junit.jupiter.api.Assertions.*;


@SpringBootTest
@Transactional
class ErrorRepositoryImplTest {

    @Autowired
    private ErrorRepository errorRepository;

    @Test
    @DisplayName("프로젝트네임 파라미터를 추가하여 로그 리스트를 보여주도록 한다.")
    void findErrorList() throws Exception {
        //given
        Error log1 = createErrorLog("프로젝트11", "메신져11");
        Error log2 = createErrorLog("프로젝트11", "메신져22");
        Error log3 = createErrorLog("프로젝트22", "메신져11");
        errorRepository.saveAll(List.of(log1, log2, log3));

        ErrorGetFindListDto.request request = new ErrorGetFindListDto.request();
        request.setProject_name("프로젝트11");

        //when
        List<ErrorGetFindListDto.FindListResponse> errorList = errorRepository.findErrorList(request);
        //then
        assertThat(errorList).hasSize(2)
                .extracting("projectName", "message")
                .containsExactlyInAnyOrder(
                        tuple("프로젝트11", "메신져11"),
                        tuple("프로젝트11", "메신져22")
                );
    }

    @Test
    @DisplayName("날짜 파라미터를 추가하여 로그 리스트를 보여주도록 한다.")
    void findErrorDateList() throws Exception {



        //given
        Error log1 = createErrorLog("프로젝트11", "메신져11");
        Error log2 = createErrorLog("프로젝트11", "메신져22");
        Error log3 = createErrorLog("프로젝트22", "메신져11");
        errorRepository.saveAll(List.of(log1, log2, log3));

        ErrorGetFindListDto.request request = new ErrorGetFindListDto.request();

        String formatter = today();
        request.setUser_uuid("생성");
        request.setStart_date(formatter);
        request.setEnd_date(formatter);

        //when
        List<ErrorGetFindListDto.FindListResponse> errorList = errorRepository.findErrorList(request);

        //then
        assertThat(errorList).hasSize(3)
                .extracting("projectName", "message")
                .containsExactlyInAnyOrder(
                        tuple("프로젝트11", "메신져11"),
                        tuple("프로젝트11", "메신져22"),
                        tuple("프로젝트22", "메신져11")
                );
    }

    private String today() {
        LocalDate localDate = LocalDate.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyyMMdd");
        return formatter.format(localDate);
    }


    private Error createErrorLog(String projectName, String message) {
       return Error.builder()
                .projectName(projectName)
                .userUuid("생성")
                .code("코드")
                .message(message)
                .build();
    }

}