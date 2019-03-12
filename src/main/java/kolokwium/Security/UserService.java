package kolokwium.Security;

import kolokwium.Model.User;
import kolokwium.Repo.RoleDAO;
import kolokwium.Repo.UsersDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService  {
    @Autowired
    private UsersDAO usersDAO;

    @Autowired
    private RoleDAO roleDAO;

    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    public void save(User user){
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        user.setRole(roleDAO.findRoleByName("user"));
    }

    public User findByAlbumNumber(Integer albumNumber){
        return usersDAO.findUserByAlbumNumber(albumNumber);
    }
}
