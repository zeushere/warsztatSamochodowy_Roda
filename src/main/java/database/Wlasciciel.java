package database;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

public class Wlasciciel {

    @Id
    @GeneratedValue
    @Column(name = "id_wlasciciela")
    private int id_wlasciciela;
    @Column(name = "imie_wlasciciela")
    private String imie_wlasciciela;
    @Column(name = "nazwisko_wlasciciela")
    private String nazwisko_wlasciciela;
    @Column(name = "id_naprawy")
    private String id_naprawy;

    public Wlasciciel(int id_wlasciciela, String imie_wlasciciela, String nazwisko_wlasciciela, String id_naprawy) {
        this.id_wlasciciela = id_wlasciciela;
        this.imie_wlasciciela = imie_wlasciciela;
        this.nazwisko_wlasciciela = nazwisko_wlasciciela;
        this.id_naprawy = id_naprawy;
    }

    public int getId_wlasciciela() {
        return id_wlasciciela;
    }

    public void setId_wlasciciela(int id_wlasciciela) {
        this.id_wlasciciela = id_wlasciciela;
    }

    public String getImie_wlasciciela() {
        return imie_wlasciciela;
    }

    public void setImie_wlasciciela(String imie_wlasciciela) {
        this.imie_wlasciciela = imie_wlasciciela;
    }

    public String getNazwisko_wlasciciela() {
        return nazwisko_wlasciciela;
    }

    public void setNazwisko_wlasciciela(String nazwisko_wlasciciela) {
        this.nazwisko_wlasciciela = nazwisko_wlasciciela;
    }

    public String getId_naprawy() {
        return id_naprawy;
    }

    public void setId_naprawy(String id_naprawy) {
        this.id_naprawy = id_naprawy;
    }

    @Override
    public String toString() {
        return "Wlasciciel{" +
                "id_wlasciciela=" + id_wlasciciela +
                ", imie_wlasciciela='" + imie_wlasciciela + '\'' +
                ", nazwisko_wlasciciela='" + nazwisko_wlasciciela + '\'' +
                ", id_naprawy='" + id_naprawy + '\'' +
                '}';
    }
}
