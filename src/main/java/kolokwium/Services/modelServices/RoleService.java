package kolokwium.Services.modelServices;

import kolokwium.Model.Role;
import kolokwium.Model.RoleName;
import kolokwium.Model.User;
import kolokwium.Repositories.RoleDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.management.relation.RoleNotFoundException;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

@Service
public class RoleService {

    @Autowired
    RoleDAO roleDAO;

    public void addRole(String name){
        roleDAO.save(new Role(RoleName.valueOf(name)));
    }

    public Optional<Role> findByName(RoleName roleName){
        return roleDAO.findByName(roleName);
    }

    public Set<Role> attachRolesFromStringSet(Set<String> strRoles){

        Set<Role> roles = new HashSet<>();

        if(!strRoles.isEmpty())
        strRoles.forEach(role -> {
            switch (role) {
                case "admin":
                    Role adminRole = roleDAO.findByName(RoleName.ROLE_ADMIN)
                            .orElseThrow(RuntimeException::new);
                    roles.add(adminRole);

                    break;
                case "pm":
                    Role pmRole = roleDAO.findByName(RoleName.ROLE_PM)
                            .orElseThrow(RuntimeException::new);
                    roles.add(pmRole);

                    break;
                default:
                    Role userRole = roleDAO.findByName(RoleName.ROLE_USER)
                            .orElseThrow(RuntimeException::new);
                    roles.add(userRole);
            }
        });
        else roles.add(roleDAO.findByName(RoleName.ROLE_USER).orElseThrow(RuntimeException::new));

        return roles;

    }

    public Set<Role> attachRolesFromRoleSet(Set<Role> strRoles){

        Set<Role> roles = new HashSet<>();
        if(!strRoles.isEmpty())
        strRoles.forEach(role -> {
            switch (role.getName().toString()) {
                case "ROLE_ADMIN":
                    Role adminRole = roleDAO.findByName(RoleName.ROLE_ADMIN)
                            .orElseThrow(RuntimeException::new);
                    roles.add(adminRole);

                    break;
                case "ROLE_PM":
                    Role pmRole = roleDAO.findByName(RoleName.ROLE_PM)
                            .orElseThrow(RuntimeException::new);
                    roles.add(pmRole);

                    break;
                default:
                    Role userRole = roleDAO.findByName(RoleName.ROLE_USER)
                            .orElseThrow(RuntimeException::new);
                    roles.add(userRole);
            }
        });
        else roles.add(roleDAO.findByName(RoleName.ROLE_USER).orElseThrow(RuntimeException::new));

        return roles;
    }


}
