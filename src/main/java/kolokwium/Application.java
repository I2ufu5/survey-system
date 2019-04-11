package kolokwium;

import kolokwium.Model.*;
import kolokwium.Repositories.*;
import kolokwium.Services.modelServices.ResponseService;
import kolokwium.Services.modelServices.RoleService;
import kolokwium.Services.modelServices.UserService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@SpringBootApplication
public class Application extends SpringBootServletInitializer {

    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
        return application.sources(Application.class);
    }


    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

    @Bean
    public CommandLineRunner DatabaseInit(
            UserService userService,
            QuestionsDAO questionsDAO,
            AnswersDAO answersDAO,
            ResponseDAO responseDAO,
            ResponseService responseService,
            RoleService roleService){

        PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

        return (args) -> {

            roleService.addRole("ROLE_USER");
            roleService.addRole("ROLE_ADMIN");
            roleService.addRole("ROLE_PM");

            userService.createUser("145203@stud.prz.edu.pl","Kowalski", "haslo11");
            userService.setRole("145203@stud.prz.edu.pl",RoleName.ROLE_ADMIN);
            userService.setRole("145203@stud.prz.edu.pl",RoleName.ROLE_USER);
            userService.setRole("145203@stud.prz.edu.pl",RoleName.ROLE_PM);
            userService.createUser("132940@stud.prz.edu.pl", "Kowalski","ABCD11");
            userService.createUser("153421@stud.prz.edu.pl","Duda","qwerty");

            questionsDAO.save(new Question("Pytanie1"));
            questionsDAO.save(new Question("Pytanie2"));

            answersDAO.save(new Answer("Odpowiedz1", questionsDAO.findQuestionsByQuestionId(1L),true));
            answersDAO.save(new Answer("Odpowiedz2", questionsDAO.findQuestionsByQuestionId(1L),false));
            answersDAO.save(new Answer("Odpowiedz3", questionsDAO.findQuestionsByQuestionId(1L),false));

            answersDAO.save(new Answer("Odpowiedz1", questionsDAO.findQuestionsByQuestionId(2L),false));
            answersDAO.save(new Answer("Odpowiedz2", questionsDAO.findQuestionsByQuestionId(2L),false));
            answersDAO.save(new Answer("Odpowiedz3", questionsDAO.findQuestionsByQuestionId(2L),true));

            responseDAO.save(new Response(
                    userService.findByEmail("145203@stud.prz.edu.pl"),
                    questionsDAO.findQuestionsByQuestionId(1L),
                    answersDAO.findAnswersByQuestion_QuestionId(1L).get(0)
            ));

            responseDAO.save(new Response(
                    userService.findByEmail("145203@stud.prz.edu.pl"),
                    questionsDAO.findQuestionsByQuestionId(2L),
                    answersDAO.findAnswersByQuestion_QuestionId(2L).get(1)
            ));

            responseService.calculateResult("145203@stud.prz.edu.pl");

        };
    }

}
