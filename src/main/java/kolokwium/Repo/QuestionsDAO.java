package kolokwium.Repo;

import kolokwium.Model.Question;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface QuestionsDAO extends JpaRepository<Question,Long> {
    Question findQuestionsById(Long id);
}
