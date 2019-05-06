package kolokwium.Services.modelServices;

import kolokwium.Model.Role;
import kolokwium.Model.RoleName;
import kolokwium.Model.User;
import kolokwium.Repositories.UsersDAO;
import kolokwium.Services.UserPrinciple;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;

@Service
public class UserService implements org.springframework.security.core.userdetails.UserDetailsService {
    @Autowired
    private UsersDAO usersDAO;

    @Autowired
    private RoleService roleService;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    public UserService(UsersDAO usersDAO){
        this.usersDAO = usersDAO;
    }

    public User createUser(String email, String name, String password){
        return usersDAO.save(new User(email,name,passwordEncoder.encode(password)));
    }

    public User createUser(User user){
        return usersDAO.save(user);
    }

    public void changeUserPassword(String email, String newPassword){
        User user = usersDAO.findUserByEmail(email).orElseThrow(()-> new UsernameNotFoundException(email));
        user.setPassword(passwordEncoder.encode(newPassword));
        usersDAO.save(user);
    }

    public User findByEmail(String email){
        return usersDAO.findUserByEmail(email).orElseThrow(()-> new UsernameNotFoundException(email));
    }

    public void setRole(String email, RoleName roleName){
        User user = usersDAO.findUserByEmail(email)
                .orElseThrow(()-> new UsernameNotFoundException(email));
        user.getRoles().add(roleService.findByName(roleName)
                .orElseThrow(()-> new RuntimeException("User Role not found.")));
        usersDAO.save(user);
    }

    public void setRole(String email, Set<Role> roles){
        User user = usersDAO.findUserByEmail(email).orElseThrow(()-> new UsernameNotFoundException(email));
        user.setRoles(roleService.attachRolesFromRoleSet(roles));
        usersDAO.save(user);
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        User user = usersDAO.findUserByEmail(email).orElseThrow(
                () -> new UsernameNotFoundException("User Not Found with -> username or email : " + email));

        return UserPrinciple.build(user);
    }

    public boolean checkExistingByEmail(String email){
        return usersDAO.existsByEmail(email);
    }

    public List<User> getAllUsers(){
        return usersDAO.findAll();
    }

    public boolean deleteUser(Long id){
        if(usersDAO.existsByUserId(id)) {
            usersDAO.delete(usersDAO.findByUserId(id).orElseThrow(
                    () -> new UsernameNotFoundException("User Not Found with -> username or email : " + id)));
            return true;
        }
        else
            return false;
    }

    public void submitResult(String email, Integer result){
        User user = usersDAO.findUserByEmail(email).orElseThrow(() -> new UsernameNotFoundException(email));
        user.setUserResult(result);
        usersDAO.save(user);
    }

    public Integer getUserResult(String email){
        User user = usersDAO.findUserByEmail(email)
                .orElseThrow(()-> new UsernameNotFoundException(email));
        return user.getUserResult();
    }
}


