package kolokwium.Services.modelServices;

import kolokwium.Controlers.Messages.QuizMessages.QuestionMessage;
import kolokwium.Model.Answer;
import kolokwium.Model.Question;
import kolokwium.Repositories.AnswersDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;

@Service
public class AnswersService {

    @Autowired
    AnswersDAO answersDAO;


    @Autowired
    QuestionService questionService;


    public void addAnswers(String a,String b, String c, String d, Long questionId, String isCorrect){
        Question question = questionService.getQuestionById(questionId);

        answersDAO.save(new Answer(a,question,isCorrect.equals("a")));
        answersDAO.save(new Answer(b,question,isCorrect.equals("b")));
        answersDAO.save(new Answer(c,question,isCorrect.equals("c")));
        answersDAO.save(new Answer(d,question,isCorrect.equals("d")));

    }

    public void addAnswersFromMessage(QuestionMessage questionMessage, Long questionId){
        Question question = questionService.getQuestionById(questionId);

        if(questionMessage.answer == null)
            throw new NoSuchElementException("PUSTA ODPOWIEDX");
        answersDAO.save(new Answer(questionMessage.a,question,questionMessage.answer.equals("a")));
        answersDAO.save(new Answer(questionMessage.b,question,questionMessage.answer.equals("b")));
        answersDAO.save(new Answer(questionMessage.c,question,questionMessage.answer.equals("c")));
        answersDAO.save(new Answer(questionMessage.d,question,questionMessage.answer.equals("d")));

    }

    public List<Answer> findByQuestionId(Long questionId){
        return answersDAO.findAllByQuestion_QuestionId(questionId);
    }


}
