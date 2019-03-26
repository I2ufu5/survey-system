package kolokwium.Services;

import kolokwium.Model.Answer;
import kolokwium.Model.Response;
import kolokwium.Repo.ResponseDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.concurrent.atomic.AtomicInteger;

@Service
public class ResponseService {

    @Autowired
    private ResponseDAO responseDAO;

    @Autowired
    UserDetailsService userDetailsService;

    public int calculateResult(Integer albumNumber)throws UsernameNotFoundException {
        if(userDetailsService.findByAlbumNumber(albumNumber) == null)
            throw new UsernameNotFoundException(albumNumber.toString());

        int correctCount = 0;
        Set<Response> responses = responseDAO.findAllByUser_AlbumNumber(albumNumber);
        for(Response response:responses){
            if(response.getAnswer().isCorrect())
                correctCount++;

        }

        userDetailsService.submitResult(albumNumber,correctCount);
        return correctCount;
    }
}
