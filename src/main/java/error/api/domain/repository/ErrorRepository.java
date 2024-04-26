package error.api.domain.repository;

import error.api.domain.entity.Error;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface ErrorRepository extends JpaRepository<Error, Long>, ErrorRepositoryCustom{
}
