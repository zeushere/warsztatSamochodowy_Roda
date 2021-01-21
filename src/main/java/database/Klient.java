package database;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

public class Klient {
    @Id
    @GeneratedValue
    @Column(name = "id_klienta")
    private int id_klienta;
    @Column(name = "imie")
    private String imie;
    @Column(name = "nazwisko")
    private String nazwisko;
    @Column(name = "PESEL")
    private String PESEL;

    public Klient() {
    }

    public Klient(int id_klienta, String imie, String nazwisko, String PESEL) {
        this.id_klienta = id_klienta;
        this.imie = imie;
        this.nazwisko = nazwisko;
        this.PESEL = PESEL;
    }

    public int getId_klienta() {
        return id_klienta;
    }

    public void setId_klienta(int id_klienta) {
        this.id_klienta = id_klienta;
    }

    public String getImie() {
        return imie;
    }

    public void setImie(String imie) {
        this.imie = imie;
    }

    public String getNazwisko() {
        return nazwisko;
    }

    public void setNazwisko(String nazwisko) {
        this.nazwisko = nazwisko;
    }

    public String getPESEL() {
        return PESEL;
    }

    public void setPESEL(String PESEL) {
        this.PESEL = PESEL;
    }

    @Override
    public String toString() {
        return "KlientController{" +
                "id_klienta=" + id_klienta +
                ", imie='" + imie + '\'' +
                ", nazwisko='" + nazwisko + '\'' +
                ", PESEL='" + PESEL + '\'' +
                '}';
    }
}
