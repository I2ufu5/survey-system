package kolokwium.Security;

import kolokwium.Model.User;
import kolokwium.Repo.UsersDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserDetailsService implements org.springframework.security.core.userdetails.UserDetailsService {

    private final UsersDAO usersDAO;

    @Autowired
    public UserDetailsService(UsersDAO usersDAO) {
        this.usersDAO = usersDAO;
    }

    @Override
    public UserDetails loadUserByUsername(String albumNumber) throws UsernameNotFoundException {
        User user = usersDAO.findUserByAlbumNumber(Integer.parseInt(albumNumber));
        if(user == null)
            throw new UsernameNotFoundException(albumNumber);

        org.springframework.security.core.userdetails.User.UserBuilder builder;
        builder = org.springframework.security.core.userdetails.User.withUsername(user.getName());
        builder.password(user.getPassword());
        builder.roles(user.getRole().getName());

        return builder.build();
    }
}
