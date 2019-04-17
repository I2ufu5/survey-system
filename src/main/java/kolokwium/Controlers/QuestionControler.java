package kolokwium.Controlers;

import kolokwium.Controlers.Messages.QuizMessages.QuestionMessage;
import kolokwium.Services.modelServices.AnswersService;
import kolokwium.Services.modelServices.QuestionService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import java.util.List;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/questions")
public class QuestionControler {


    Logger logger = LoggerFactory.getLogger(QuestionControler.class);

    @Autowired
    QuestionService questionService;

    @Autowired
    AnswersService answersService;

    @GetMapping
    public ResponseEntity<?> getAllQuestions() {

        List<QuestionMessage> questionList;
        questionList = questionService.getAllQuestionsAsMessages();
        return ResponseEntity.ok(questionList);
    }

    @PostMapping("/add")
    public ResponseEntity<?> addSingleQuestion(@Valid @RequestBody QuestionMessage questionMessage){
        logger.error(questionMessage.answer + " " + questionMessage.question);
        questionService.addSingleQuestionFromMessage(questionMessage);
        return ResponseEntity.ok(HttpStatus.OK);
    }
}
