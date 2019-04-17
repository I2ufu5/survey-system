package kolokwium.Repositories;

import kolokwium.Model.Question;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import javax.swing.text.html.Option;
import java.util.Optional;

@Repository
public interface QuestionsDAO extends JpaRepository<Question,Long> {
    Question findQuestionsByQuestionId(Long id);
}
