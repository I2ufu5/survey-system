package kolokwium.Controlers;

import kolokwium.Controlers.Messages.UserAccountMessages.*;
import kolokwium.Model.User;
import kolokwium.Services.UserPrinciple;
import kolokwium.Services.modelServices.RoleService;
import kolokwium.Services.modelServices.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/users")
public class UsersControler {

    @Autowired
    UserService userService;

    @Autowired
    RoleService roleService;

    @Autowired
    AuthenticationManager authenticationManager;

    @Autowired
    PasswordEncoder encoder;

    Logger logger = LoggerFactory.getLogger(UsersControler.class);

    @PutMapping("/role")
    public ResponseEntity<?> changeUserRole(@Valid @RequestBody User user){
        userService.setRole(user.getEmail(),user.getRoles());
        return new ResponseEntity<>(new ResponseMessage("Role nadane pomyślnie"), HttpStatus.OK);
    }

    @PutMapping("/pass")
    public ResponseEntity<?> changeUserPassword(@Valid @RequestBody ChangePasswordForm loginForm){
        User user = userService.findByEmail(loginForm.getEmail());

        if(encoder.matches(loginForm.getOldPassword(),user.getPassword())){
            userService.changeUserPassword(loginForm.getEmail(),loginForm.getNewPassword());
            return ResponseEntity.ok("Hasło zmienione pomyślnie");
        }else
            return ResponseEntity.ok("Hasło nie zostało zmienione");
    }

    @DeleteMapping("/")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<?> deleteUser(@RequestBody DeleteUserMessage message){
        String email = message.getEmail();
        if(userService.deleteUser(email))
            return new ResponseEntity<>(new ResponseMessage("User usunięty"),HttpStatus.OK);
        else
            return new ResponseEntity<>(new ResponseMessage("Błąd podczas usuwania usera"),HttpStatus.CONFLICT);
    }

    @GetMapping("/")
    @PreAuthorize("hasRole('ADMIN')")
    public List<User> gelAllUsers(){
        return userService.getAllUsers();
    }


}
