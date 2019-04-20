package kolokwium;

import kolokwium.Model.*;
import kolokwium.Repositories.*;
import kolokwium.Services.modelServices.AnswersService;
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
            AnswersService answersService,
            RoleService roleService,
            QuizDAO quizDAO){

        PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

        return (args) -> {

            roleService.addRole("ROLE_USER");
            roleService.addRole("ROLE_ADMIN");
            roleService.addRole("ROLE_PM");

            userService.createUser("145203@stud.prz.edu.pl","Kowalski", "haslo11");
            userService.setRole("145203@stud.prz.edu.pl",RoleName.ROLE_ADMIN);
            userService.setRole("145203@stud.prz.edu.pl",RoleName.ROLE_USER);
            userService.setRole("145203@stud.prz.edu.pl",RoleName.ROLE_PM);
            userService.submitResult("145203@stud.prz.edu.pl",70);

            userService.createUser("132940@stud.prz.edu.pl", "Kaczyński","ABCD11");
            userService.createUser("153421@stud.prz.edu.pl","Duda","qwerty");

            quizDAO.save(new Quiz());

            questionsDAO.save(new Question("Pytanie1",quizDAO.getOne(1L)));
            questionsDAO.save(new Question("Pytanie2",quizDAO.getOne(1L)));

            answersService.addAnswers("Odp1","Odp2","Odp3","Odp4",1L,"c");
            answersService.addAnswers("Odp21","Odp22","Odp23","Odp24",2L,"a");

//
//            responseDAO.save(new Response(
//                    userService.findByEmail("145203@stud.prz.edu.pl"),
//                    questionsDAO.findQuestionsByQuestionId(1L),
//                    answersDAO.findAnswersByQuestion_QuestionId(1L).get(0)
//            ));
//
//            responseDAO.save(new Response(
//                    userService.findByEmail("145203@stud.prz.edu.pl"),
//                    questionsDAO.findQuestionsByQuestionId(2L),
//                    answersDAO.findAnswersByQuestion_QuestionId(2L).get(1)
//            ));
//
//            responseService.calculateResult("145203@stud.prz.edu.pl");

        };
    }

}
