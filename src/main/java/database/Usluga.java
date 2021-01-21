package database;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

public class Usluga {

    @Id
    @GeneratedValue
    @Column(name = "id_uslugi")
    private int id_uslugi;
    @Column(name = "nazwa_uslugi")
    private String nazwa_uslugi;
    @Column(name = "rodzaj_uslugi")
    private String rodzaj_uslugi;



    public Usluga(int id_uslugi, String nazwa_uslugi, String rodzaj_uslugi) {
        this.id_uslugi = id_uslugi;
        this.nazwa_uslugi = nazwa_uslugi;
        this.rodzaj_uslugi = rodzaj_uslugi;
    }

    public int getId_uslugi() {
        return id_uslugi;
    }

    public void setId_uslugi(int id_uslugi) {
        this.id_uslugi = id_uslugi;
    }

    public String getNazwa_uslugi() {
        return nazwa_uslugi;
    }

    public void setNazwa_uslugi(String nazwa_uslugi) {
        this.nazwa_uslugi = nazwa_uslugi;
    }

    public String getRodzaj_uslugi() {
        return rodzaj_uslugi;
    }

    public void setRodzaj_uslugi(String rodzaj_uslugi) {
        this.rodzaj_uslugi = rodzaj_uslugi;
    }

    @Override
    public String toString() {
        return "Usluga{" +
                "id_uslugi=" + id_uslugi +
                ", nazwa_uslugi='" + nazwa_uslugi + '\'' +
                ", rodzaj_uslugi='" + rodzaj_uslugi + '\'' +
                '}';
    }
}
