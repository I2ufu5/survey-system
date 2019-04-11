package kolokwium.Services.modelServices;

import kolokwium.Model.Role;
import kolokwium.Model.RoleName;
import kolokwium.Model.User;
import kolokwium.Repositories.RoleDAO;
import kolokwium.Repositories.UsersDAO;
import kolokwium.Services.UserPrinciple;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService implements org.springframework.security.core.userdetails.UserDetailsService {
    @Autowired
    private UsersDAO usersDAO;

    @Autowired
    private RoleDAO roleDAO;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    public UserService(UsersDAO usersDAO){
        this.usersDAO = usersDAO;
    }

    public boolean createUser(String email, String name, String password){
        usersDAO.save(new User(email,name,passwordEncoder.encode(password)));
        return true;
    }

    public void changeUserPassword(String email, String newPassword){
        User user = usersDAO.findUserByEmail(email).orElseThrow(()-> new UsernameNotFoundException(email));
        user.setPassword(newPassword);
        usersDAO.save(user);
    }

    public User findByEmail(String email){
        return usersDAO.findUserByEmail(email).orElseThrow(()-> new UsernameNotFoundException(email));
    }

    public void submitResult(String email, int result){
        User user = usersDAO.findUserByEmail(email).orElseThrow(()-> new UsernameNotFoundException(email));
        user.setTestResult(Float.valueOf(result));
        usersDAO.save(user);
    }

    public boolean setRole(String email, RoleName roleName){
        User user = usersDAO.findUserByEmail(email).orElseThrow(()-> new UsernameNotFoundException(email));
        if(user == null)
            throw new UsernameNotFoundException(email);
        user.getRoles().add(roleDAO.findByName(roleName)
                .orElseThrow(()-> new RuntimeException("Fail! -> Cause: User Role not find.")));
        usersDAO.save(user);
        return true;
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        User user = usersDAO.findUserByEmail(email).orElseThrow(
                () -> new UsernameNotFoundException("User Not Found with -> username or email : " + email));

        return UserPrinciple.build(user);
    }
}
