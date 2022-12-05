package hu.gamf;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="kategoria")
public class KategoriaClass {
    @Id
    @Column(name = "kat_kod")
    public int kat_kod;
    @Column(name = "kat_nev")
    public String kat_nev;

    public int getKat_kod() {
        return kat_kod;
    }

    public void setKat_kod(int kat_kod) {
        this.kat_kod = kat_kod;
    }

    public String getKat_nev() {
        return kat_nev;
    }

    public void setKat_nev(String kat_nev) {
        this.kat_nev = kat_nev;
    }

    public KategoriaClass() {
    }

    public KategoriaClass(int kat_kod, String kat_nev) {
        this.kat_kod = kat_kod;
        this.kat_nev = kat_nev;
    }
}
