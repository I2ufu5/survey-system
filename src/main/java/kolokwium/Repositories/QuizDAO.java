package kolokwium.Repositories;

import kolokwium.Model.Quiz;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

@Service
public interface QuizDAO extends JpaRepository<Quiz,Long> {

}
