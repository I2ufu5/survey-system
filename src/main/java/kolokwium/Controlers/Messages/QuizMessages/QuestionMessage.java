package kolokwium.Controlers.Messages.QuizMessages;

import kolokwium.Model.Answer;
import kolokwium.Model.Question;

import javax.validation.constraints.NotBlank;
import java.util.List;
import java.util.Set;

public class QuestionMessage {

    public Long id;


    public String question;


    public String a;


    public String b;


    public String c;


    public String d;


    public String answer;

    public QuestionMessage(Long id, String question,  String a,  String b,  String c,  String d,  String answer) {
        this.id = id;
        this.question = question;
        this.a = a;
        this.b = b;
        this.c = c;
        this.d = d;
        this.answer = answer;
    }

    public QuestionMessage(Long id, Question question, List<Answer> answers) {
        this.id = id;
        this.question = question.getText();

        this.a = answers.get(0).getText();
        if(answers.get(0).isCorrect())
            this.answer = "a";
        this.b = answers.get(1).getText();
        if(answers.get(1).isCorrect())
            this.answer = "b";
        this.c = answers.get(2).getText();
        if(answers.get(2).isCorrect())
            this.answer = "c";
        this.d = answers.get(3).getText();
        if(answers.get(3).isCorrect())
            this.answer = "d";
    }

    public QuestionMessage(){

    }
}
