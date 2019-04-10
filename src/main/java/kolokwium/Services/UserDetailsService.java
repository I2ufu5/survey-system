package kolokwium.Services;

import kolokwium.Model.User;
import kolokwium.Repo.UsersDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserDetailsService implements org.springframework.security.core.userdetails.UserDetailsService {
    @Autowired
    private UsersDAO usersDAO;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    public UserDetailsService(UsersDAO usersDAO){
        this.usersDAO = usersDAO;
    }

    public boolean createUser(User user){
        if(findByAlbumNumber(user.getAlbumNumber()) == null){
            usersDAO.save(user);
            return true;
        }else return false;
    }

    public boolean createUser(Integer albumNumber,String name, String password){
        if(findByAlbumNumber(albumNumber) == null){
            usersDAO.save(new User(albumNumber,name,passwordEncoder.encode(password)));
            return true;
        }else return false;
    }

    public void changeUserPassword(Integer indexNumber, String newPassword){
        User user = usersDAO.findUserByAlbumNumber(indexNumber);
        user.setPassword(newPassword);
        usersDAO.save(user);
    }

//    public boolean setAdmin(Integer indexNumber, boolean set){
//        User user = findByAlbumNumber(indexNumber);
//        if(user == null)
//            return false;
//        else {
//            user.setAdmin(set);
//            usersDAO.save(user);
//            return true;
//        }
//    }

    public User findByAlbumNumber(Integer albumNumber){
        return usersDAO.findUserByAlbumNumber(albumNumber);
    }

    public void submitResult(int albumNumber, int result){
        User user = usersDAO.findUserByAlbumNumber(albumNumber);
        user.setTestResult(Float.valueOf(result));
        usersDAO.save(user);
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
