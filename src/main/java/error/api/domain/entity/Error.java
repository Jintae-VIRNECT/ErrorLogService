package error.api.domain.entity;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
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
