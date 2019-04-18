package kolokwium.Controlers;


import kolokwium.Controlers.Messages.UserAccountMessages.JwtResponse;
import kolokwium.Controlers.Messages.UserAccountMessages.LoginForm;
import kolokwium.Controlers.Messages.UserAccountMessages.ResponseMessage;
import kolokwium.Controlers.Messages.UserAccountMessages.SignUpForm;
import kolokwium.Model.Role;
import kolokwium.Model.RoleName;
import kolokwium.Model.User;
import kolokwium.Repositories.RoleDAO;
import kolokwium.Repositories.UsersDAO;
import kolokwium.Services.UserPrinciple;
import kolokwium.Services.jwtServices.JwtProvider;
import kolokwium.Services.modelServices.RoleService;
import kolokwium.Services.modelServices.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.HashSet;
import java.util.Set;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/auth")
public class AuthControler {

    @Autowired
    AuthenticationManager authenticationManager;

    @Autowired
    UserService userService;

    @Autowired
    RoleService roleService;

    @Autowired
    PasswordEncoder encoder;

    @Autowired
    JwtProvider jwtProvider;

    @PostMapping("/signin")
    public ResponseEntity<?> authenticateUser(@Valid @RequestBody LoginForm loginRequest) {

        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(loginRequest.getUsername(), loginRequest.getPassword()));

        SecurityContextHolder.getContext().setAuthentication(authentication);

        String jwt = jwtProvider.generateJwtToken(authentication);
        UserPrinciple userDetails = (UserPrinciple) authentication.getPrincipal();

        return ResponseEntity.ok(new JwtResponse(jwt, userDetails.getEmail(), userDetails.getAuthorities()));
    }

    @PostMapping("/signup")
    public ResponseEntity<?> registerUser(@Valid @RequestBody SignUpForm signUpRequest) {

        if (userService.checkExistingByEmail(signUpRequest.getEmail())) {
            return new ResponseEntity<>(new ResponseMessage("Fail -> Email jest już użyty!"),
                    HttpStatus.BAD_REQUEST);
        }

        User user = new User( signUpRequest.getEmail(),signUpRequest.getName(),
                encoder.encode(signUpRequest.getPassword()));

        user.setRoles(roleService.attachRolesFromStringSet(signUpRequest.getRole()));
        userService.createUser(user);

        return new ResponseEntity<>(new ResponseMessage("User registered successfully!"), HttpStatus.OK);
    }

}
