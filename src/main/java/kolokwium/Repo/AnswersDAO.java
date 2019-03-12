package kolokwium.Repo;

import kolokwium.Model.Answer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AnswersDAO extends JpaRepository<Answer, Long> {
}
