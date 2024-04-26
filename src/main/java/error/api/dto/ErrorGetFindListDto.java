package error.api.dto;

import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
public class ErrorGetFindListDto {

	@Data
	public static class Request {

		private String userUuid;

		private String code;

		private String message;

		private String projectName;

		private String startDate;

		private String endDate;
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
