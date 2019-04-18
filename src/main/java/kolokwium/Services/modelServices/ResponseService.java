package kolokwium.Services.modelServices;

import kolokwium.Model.Response;
import kolokwium.Repositories.ResponseDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Set;

@Service
public class ResponseService {

    @Autowired
    private ResponseDAO responseDAO;

    @Autowired
    UserService userService;

//    public int calculateResult(String email)throws UsernameNotFoundException {
//        if(userService.findByEmail(email) == null)
//            throw new UsernameNotFoundException(email.toString());
//
//        int correctCount = 0;
//        Set<Response> responses = responseDAO.findAllByUser_Email(email);
//        for(Response response:responses){
//            if(response.getAnswer().isCorrect())
//                correctCount++;
//
//        }
//
//        userService.submitResult(email,correctCount);
//        return correctCount;
//    }
}
