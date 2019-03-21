package kolokwium.Services;

import kolokwium.Model.User;
import kolokwium.Repo.RoleDAO;
import kolokwium.Repo.UsersDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import javax.persistence.criteria.CriteriaBuilder;

@Service
public class UserDetailsService implements org.springframework.security.core.userdetails.UserDetailsService {
    @Autowired
    private UsersDAO usersDAO;

    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

//    @Autowired
//    private RoleDAO roleDAO;

    @Autowired
    public UserDetailsService(UsersDAO usersDAO){
        this.usersDAO = usersDAO;
    }

    public void save(User user){
        user.setPassword(passwordEncoder.encode(user.getPassword()));
    }
    public User findByAlbumNumber(Integer albumNumber){
        return usersDAO.findUserByAlbumNumber(albumNumber);
    }

    @Override
    public UserDetails loadUserByUsername(String albumNumber) throws UsernameNotFoundException {
        User user = usersDAO.findUserByAlbumNumber(Integer.parseInt(albumNumber));
        if(user == null)
            throw new UsernameNotFoundException(albumNumber);
        org.springframework.security.core.userdetails.User.UserBuilder builder;
        builder = org.springframework.security.core.userdetails.User.withUsername(user.getName());
        builder.password(user.getPassword());
        builder.roles("USER");
        return builder.build();
    }
}
