package kolokwium.Model;


import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long userId;

    private Integer albumNumber;

    private String name;

    private String password;

    private Float testResult;

    //private boolean isAdmin = false;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "user_roles",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "role_id"))
    private Set<Role> roles = new HashSet<>();

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Integer getAlbumNumber() {
        return albumNumber;
    }

    public void setAlbumNumber(Integer albumNumber) {
        this.albumNumber = albumNumber;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Float getTestResult() {
        return testResult;
    }

    public void setTestResult(Float testResult) {
        this.testResult = testResult;
    }

//    public boolean isAdmin() {
//        return isAdmin;
//    }

//    public User setAdmin(boolean set) {
//        this.isAdmin = set;
//        return this;
//    }

    public Set<Role> getRoles() {
        return roles;
    }

    public void setRoles(Set<Role> roles) {
        this.roles = roles;
    }

    public User(Integer albumNumber, String name, String password) {
        this.albumNumber = albumNumber;
        this.name = name;
        this.password = password;
    }

    public User() {
    }
}
