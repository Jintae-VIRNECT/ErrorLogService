package error.api.domain.entity;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Index;
import javax.persistence.Table;

import org.hibernate.annotations.CreationTimestamp;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "error", indexes = {
	@Index(name = "idx_error_user_uuid", columnList = "userUuid"),
	@Index(name = "idx_error_project_name", columnList = "projectName"),
	@Index(name = "idx_error_code", columnList = "code"),
	@Index(name = "idx_error_message", columnList = "message"),
	@Index(name = "idx_error_create_date_time", columnList = "createDateTime")})
public class Error {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(nullable = false)
	private String userUuid;

	@Column(nullable = false)
	private String projectName;

	@Column(nullable = false)
	private String code;

	@Column(nullable = false, length = 5000)
	private String message;

	@CreationTimestamp
	@Column(updatable = false, columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP")
	private LocalDateTime createDateTime;

	@Builder
	public Error(String userUuid, String code, String message, String projectName) {
		this.userUuid = userUuid;
		this.code = code;
		this.message = message;
		this.projectName = projectName;
	}
}
