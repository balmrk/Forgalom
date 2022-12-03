package hu.gamf;

import java.util.ArrayList;

public class rest2DTO {
    private int id;
    private String nev;
    private String tipus;
    private boolean dijazott;
    ArrayList< Object > ar = new ArrayList < Object > ();
    ArrayList < Object > tartalom = new ArrayList < Object > ();

    public rest2DTO(int id, String nev, String tipus, boolean dijazott) {
        this.id = id;
        this.nev = nev;
        this.tipus = tipus;
        this.dijazott = dijazott;
        this.ar = new ArrayList<Object>();
        this.tartalom = new ArrayList<Object>();
    }

    public rest2DTO() {
    }

    public boolean isDijazott() {
        return dijazott;
    }

    public int getId() {
        return id;
    }

    public String getNev() {
        return nev;
    }

    public String getTipus() {
        return tipus;
    }

    public boolean getDijazott() {
        return dijazott;
    }

    // Setter Methods

    public void setId(int id) {
        this.id = id;
    }

    public void setNev(String nev) {
        this.nev = nev;
    }

    public void setTipus(String tipus) {
        this.tipus = tipus;
    }

    public void setDijazott(boolean dijazott) {
        this.dijazott = dijazott;
    }
}
