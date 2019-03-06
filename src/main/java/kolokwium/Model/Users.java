package kolokwium.Model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Users {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    private Integer numerAlbumu;

    private String nazwa;

    private String haslo;

    private Integer rola;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getNumerAlbumu() {
        return numerAlbumu;
    }

    public void setNumerAlbumu(Integer numerAlbumu) {
        this.numerAlbumu = numerAlbumu;
    }

    public String getNazwa() {
        return nazwa;
    }

    public void setNazwa(String nazwa) {
        this.nazwa = nazwa;
    }

    public String getHaslo() {
        return haslo;
    }

    public void setHaslo(String haslo) {
        this.haslo = haslo;
    }

    public Integer getRola() {
        return rola;
    }

    public void setRola(Integer rola) {
        this.rola = rola;
    }
}
