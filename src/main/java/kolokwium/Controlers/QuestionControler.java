package kolokwium.Controlers;

import kolokwium.Controlers.Messages.QuizMessages.QuestionMessage;
import kolokwium.Controlers.Messages.UserAccountMessages.DeleteUserMessage;
import kolokwium.Controlers.Messages.UserAccountMessages.UserResponseForm;
import kolokwium.Services.modelServices.AnswersService;
import kolokwium.Services.modelServices.QuestionService;
import kolokwium.Services.modelServices.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Set;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/questions")
public class QuestionControler {


    Logger logger = LoggerFactory.getLogger(QuestionControler.class);

    @Autowired
    QuestionService questionService;

    @Autowired
    AnswersService answersService;

    @Autowired
    UserService userService;


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

    @PostMapping("/response")
    public ResponseEntity<?> addResponseToQuestion(@RequestBody UserResponseForm userResponse){
         userService.submitResult(userResponse.getEmail(),Integer.parseInt(userResponse.getAnswer()));
         return ResponseEntity.ok(HttpStatus.OK);
    }

    @GetMapping("/response")
    public ResponseEntity<?> getUserResponse(@RequestBody DeleteUserMessage message){
        Integer result = userService.getUserResult(message.getEmail());
        if(result!=null)
            return ResponseEntity.ok(result);
        else
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
