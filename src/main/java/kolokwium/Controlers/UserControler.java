package kolokwium.Controlers;

import kolokwium.Model.User;
import kolokwium.Repo.UsersDAO;
import kolokwium.Services.UserDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.security.Principal;
import java.util.ArrayList;
import java.util.Base64;

@Controller
public class UserControler {

    @Autowired
    private UsersDAO usersDAO;

    @Autowired
    private UserDetailsService userService;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    @RequestMapping("/login")
    @ResponseBody
    public User login() {
        return usersDAO.findUserByAlbumNumber();

    }

    @RequestMapping("/user")
    @ResponseBody
    public User user(@RequestBody User user) {
        return userService.findByAlbumNumber(user.getAlbumNumber());
    }

    @RequestMapping("/users")
    @ResponseBody
    public ArrayList<User> listUsers(){
        return (ArrayList)usersDAO.findAll();
    }
}
