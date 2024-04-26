package error.api.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDateTime;

public class ErrorGetFindListDto {

    @Data
    public static class request {

        private String user_uuid;

        private String code;

        private String message;

        private String project_name;

        private String start_date;

        private String end_date;
    }

    @Data
    @AllArgsConstructor
    @JsonNaming(PropertyNamingStrategy.SnakeCaseStrategy.class)
    public static class FindListResponse {

        private String userUuid;
        private String projectName;
        private String code;
        private String message;

        @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss", timezone = "Asia/Seoul")
        private LocalDateTime createDateTime;
    }
}
