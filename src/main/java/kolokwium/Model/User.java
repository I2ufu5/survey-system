package kolokwium.Model;


import javax.persistence.*;

@Entity
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    private Integer albumNumber;

    private String name;

    private String password;

    private Float testResult;

    @ManyToOne(fetch = FetchType.EAGER, optional = false)
    @JoinColumn(name = "idRole",nullable = false)
    private Role role;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
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

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    public User(Integer albumNumber, String name, String password, Role role) {
        this.albumNumber = albumNumber;
        this.name = name;
        this.password = password;
        this.role = role;
    }

    public User() {
    }
}
