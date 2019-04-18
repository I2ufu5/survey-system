package kolokwium.Services.modelServices;

import kolokwium.Controlers.Messages.QuizMessages.QuestionMessage;
import kolokwium.Model.Question;
import kolokwium.Repositories.AnswersDAO;
import kolokwium.Repositories.QuestionsDAO;
import org.springframework.beans.NullValueInNestedPathException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.support.NullValue;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.NoSuchElementException;

@Service
public class QuestionService {
    @Autowired
    QuestionsDAO questionsDAO;

    @Autowired
    AnswersDAO answersDAO;

    @Autowired
    AnswersService answersService;

    public ArrayList<QuestionMessage> getAllQuestionsAsMessages(){
        ArrayList<QuestionMessage> questionList = new ArrayList<>();

        for(Question q : questionsDAO.findAll()){
            questionList.add(new QuestionMessage(q.getQuestionId(),q,answersService.findByQuestionId(q.getQuestionId())));
        }

        return questionList;
    }

    public Question getQuestionById(Long id){
        return questionsDAO.findQuestionsByQuestionId(id);
    }

    public void addSingleQuestionFromMessage(QuestionMessage questionMessage){
        Question question = questionsDAO.save(new Question(questionMessage.question));
        answersService.addAnswersFromMessage(questionMessage,question.getQuestionId());
    }
}
