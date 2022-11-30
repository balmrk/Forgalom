package hu.gamf;

import javax.persistence.*;

@Entity
@Table(name = "aru")
public class AruClass {
    @Id @GeneratedValue
    @Column(name = "aru_kod")
    public int aru_kod;
    @Column(name = "kat_kod")
    public int kat_kod;
    @Column(name = "nev")
    public String nev;
    @Column(name = "egyseg")
    public String egyseg;
    @Column(name = "ar")
    public int ar;

    public int getAru_kod() {
        return aru_kod;
    }

    public void setAru_kod(int aru_kod) {
        this.aru_kod = aru_kod;
    }

    public int getKat_kod() {
        return kat_kod;
    }

    public void setAt_kod(int kat_kod) {
        this.kat_kod = kat_kod;
    }

    public String getNev() {
        return nev;
    }

    public void setNev(String nev) {
        this.nev = nev;
    }

    public String getEgyseg() {
        return egyseg;
    }

    public void setEgyseg(String egyseg) {
        this.egyseg = egyseg;
    }

    public int getAr() {
        return ar;
    }

    public void setAr(int ar) {
        this.ar = ar;
    }

    public AruClass() {
    }

    public AruClass(int kat_kod, String nev, String egyseg, int ar) {
        //this.aru_kod = aru_kod; //kivenni
        this.kat_kod = kat_kod;
        this.nev = nev;
        this.egyseg = egyseg;
        this.ar = ar;
    }
}
