package error.api.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import error.api.domain.entity.Error;
import lombok.Builder;
import lombok.Data;


import javax.validation.constraints.NotBlank;
import java.time.LocalDateTime;

public class ErrorRegisterDto {

    @Data
    @JsonNaming(PropertyNamingStrategy.SnakeCaseStrategy.class)
    public static class request {

        @NotBlank
        private String userUuid;
        @NotBlank
        private String code;
        @NotBlank
        private String projectName;
        @NotBlank
        private String message;

        @Builder
        public request(String userUuid, String code, String message, String projectName) {
            this.userUuid = userUuid;
            this.code = code;
            this.message = message;
            this.projectName = projectName;
        }

        public Error toEntity() {
            return Error.builder()
                    .userUuid(userUuid)
                    .code(code)
                    .message(message)
                    .projectName(projectName)
                    .build();
        }
    }

    @Data
    @JsonNaming(PropertyNamingStrategy.SnakeCaseStrategy.class)
    public static class ErrorResponse {
        private String userUuid;
        private String code;
        private String message;

        @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss", timezone = "Asia/Seoul")
        private LocalDateTime createDateTime;


        @Builder
        public ErrorResponse(Error error) {
            this.userUuid = error.getUserUuid();
            this.code = error.getCode();
            this.message = error.getMessage();
            this.createDateTime = error.getCreateDateTime();
        }
    }
}
