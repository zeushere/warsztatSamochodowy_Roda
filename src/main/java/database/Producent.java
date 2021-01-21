package database;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

public class Producent {
    @Id
    @GeneratedValue
    @Column(name = "id_producenta")
    private int id_producenta;
    @Column(name = "imie")
    private String imie;
    @Column(name = "nazwisko")
    private String nazwisko;
    @Column(name = "liczba_egzemplarzy")
    private String liczba_egzemplarzy;

    public Producent() {
    }

    public Producent(int id_producenta, String imie, String nazwisko, String liczba_egzemplarzy) {
        this.id_producenta = id_producenta;
        this.imie = imie;
        this.nazwisko = nazwisko;
        this.liczba_egzemplarzy = liczba_egzemplarzy;
    }

    public int getId_producenta() {
        return id_producenta;
    }

    public void setId_producenta(int id_producenta) {
        this.id_producenta = id_producenta;
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

    public String getLiczba_egzemplarzy() {
        return liczba_egzemplarzy;
    }

    public void setLiczba_egzemplarzy(String liczba_egzemplarzy) {
        this.liczba_egzemplarzy = liczba_egzemplarzy;
    }

    @Override
    public String toString() {
        return "Producent{" +
                "id_producenta=" + id_producenta +
                ", imie='" + imie + '\'' +
                ", nazwisko='" + nazwisko + '\'' +
                ", liczba_egzemplarzy='" + liczba_egzemplarzy + '\'' +
                '}';
    }
}
