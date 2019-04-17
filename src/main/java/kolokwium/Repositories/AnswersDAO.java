package kolokwium.Repositories;

import kolokwium.Model.Answer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AnswersDAO extends JpaRepository<Answer, Long> {
    //Answer findAnswersByAnswerIdAndQuestion_QuestionId(Long answerId, Long questionId);
    Answer findAnswersByAnswerId(Long id);
    List<Answer> findAllByQuestion_QuestionId(Long questionId);
}
