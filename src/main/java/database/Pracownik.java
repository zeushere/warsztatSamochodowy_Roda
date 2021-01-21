package database;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

public class Pracownik {
    @Id
    @GeneratedValue
    @Column(name = "id_pracownika")
    private int id_pracownika;
    @Column(name = "imie")
    private String imie;
    @Column(name = "nazwisko")
    private String nazwisko;
    @Column(name = "data_zatrudnienia")
    private String data_zatrudnienia;
    @Column(name = "wynagrodzenie")
    private String wynagrodzenie;
    @Column(name = "Wyna")
    private String Wyna;

    public Pracownik() {
    }

    public Pracownik(int id_pracownika, String imie, String nazwisko, String data_zatrudnienia, String wynagrodzenie, String Wyna) {
        this.id_pracownika = id_pracownika;
        this.imie = imie;
        this.nazwisko = nazwisko;
        this.data_zatrudnienia = data_zatrudnienia;
        this.wynagrodzenie = wynagrodzenie;
        this.Wyna = Wyna;
    }

    public int getId_pracownika() {
        return id_pracownika;
    }

    public void setId_pracownika(int id_pracownika) {
        this.id_pracownika = id_pracownika;
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

    public String getData_zatrudnienia() {
        return data_zatrudnienia;
    }

    public void setData_zatrudnienia(String data_zatrudnienia) {
        this.data_zatrudnienia = data_zatrudnienia;
    }

    public String getWynagrodzenie() {
        return wynagrodzenie;
    }

    public void setWynagrodzenie(String wynagrodzenie) {
        this.wynagrodzenie = wynagrodzenie;
    }

    public String getWyna() {
        return Wyna;
    }

    public void setWyna(String Wyna) {
        this.Wyna = Wyna;
    }

    @Override
    public String toString() {
        return "Pracownik{" +
                "id_pracownika=" + id_pracownika +
                ", imie='" + imie + '\'' +
                ", nazwisko='" + nazwisko + '\'' +
                ", data_zatrudnienia='" + data_zatrudnienia + '\'' +
                ", wynagrodzenie='" + wynagrodzenie + '\'' +
                ", Wyna='" + Wyna + '\'' +
                '}';
    }
}
