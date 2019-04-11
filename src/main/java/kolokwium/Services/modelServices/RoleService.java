package kolokwium.Services.modelServices;

import kolokwium.Model.Role;
import kolokwium.Model.RoleName;
import kolokwium.Repositories.RoleDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RoleService {

    @Autowired
    RoleDAO roleDAO;

    public void addRole(String name){
        roleDAO.save(new Role(RoleName.valueOf(name)));
    }
}
