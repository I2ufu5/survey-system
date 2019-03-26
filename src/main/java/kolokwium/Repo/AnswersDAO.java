package kolokwium.Repo;

import kolokwium.Model.Answer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AnswersDAO extends JpaRepository<Answer, Long> {
    List<Answer> findAnswersByQuestion_QuestionId(Long questionId);
    //Answer findAnswersByAnswerIdAndQuestion_QuestionId(Long answerId, Long questionId);
    Answer findAnswersByAnswerId(Long id);
}
