package database;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

public class Wypozyczenie {
    @Id
    @GeneratedValue
    @Column(name = "id_wypozyczenia")
    private int id_wypozyczenia;
    @Column(name = "id_klienta")
    private String id_klienta;
    @Column(name = "KImNa")
    private String KImNa;
    @Column(name = "id_egzemplarza")
    private String id_egzemplarza;
    @Column(name = "Egzemplarz")
    private String Egzemplarz;
    @Column(name = "id_pracownika")
    private String id_pracownika;
    @Column(name = "PImNa")
    private String PImNa;
    @Column(name = "data_wypozyczenia")
    private String data_wypozyczenia;
    @Column(name = "data_zwrotu")
    private String data_zwrotu;

    public Wypozyczenie() {
    }

    public Wypozyczenie(int id_wypozyczenia, String id_klienta, String KImNa, String id_egzemplarza, String egzemplarz, String id_pracownika, String PImNa, String data_wypozyczenia, String data_zwrotu) {
        this.id_wypozyczenia = id_wypozyczenia;
        this.id_klienta = id_klienta;
        this.KImNa = KImNa;
        this.id_egzemplarza = id_egzemplarza;
        Egzemplarz = egzemplarz;
        this.id_pracownika = id_pracownika;
        this.PImNa = PImNa;
        this.data_wypozyczenia = data_wypozyczenia;
        this.data_zwrotu = data_zwrotu;
    }

    public int getId_wypozyczenia() {
        return id_wypozyczenia;
    }

    public void setId_wypozyczenia(int id_wypozyczenia) {
        this.id_wypozyczenia = id_wypozyczenia;
    }

    public String getId_klienta() {
        return id_klienta;
    }

    public void setId_klienta(String id_klienta) {
        this.id_klienta = id_klienta;
    }

    public String getKImNa() {
        return KImNa;
    }

    public void setKImNa(String KImNa) {
        this.KImNa = KImNa;
    }

    public String getId_egzemplarza() {
        return id_egzemplarza;
    }

    public void setId_egzemplarza(String id_egzemplarza) {
        this.id_egzemplarza = id_egzemplarza;
    }

    public String getEgzemplarz() {
        return Egzemplarz;
    }

    public void setEgzemplarz(String egzemplarz) {
        Egzemplarz = egzemplarz;
    }

    public String getId_pracownika() {
        return id_pracownika;
    }

    public void setId_pracownika(String id_pracownika) {
        this.id_pracownika = id_pracownika;
    }

    public String getPImNa() {
        return PImNa;
    }

    public void setPImNa(String PImNa) {
        this.PImNa = PImNa;
    }

    public String getData_wypozyczenia() {
        return data_wypozyczenia;
    }

    public void setData_wypozyczenia(String data_wypozyczenia) {
        this.data_wypozyczenia = data_wypozyczenia;
    }

    public String getData_zwrotu() {
        return data_zwrotu;
    }

    public void setData_zwrotu(String data_zwrotu) {
        this.data_zwrotu = data_zwrotu;
    }

    @Override
    public String toString() {
        return "Wypozyczenie{" +
                "id_wypozyczenia=" + id_wypozyczenia +
                ", id_klienta='" + id_klienta + '\'' +
                ", KImNa='" + KImNa + '\'' +
                ", id_egzemplarza='" + id_egzemplarza + '\'' +
                ", Egzemplarz='" + Egzemplarz + '\'' +
                ", id_pracownika='" + id_pracownika + '\'' +
                ", PImNa='" + PImNa + '\'' +
                ", data_wypozyczenia='" + data_wypozyczenia + '\'' +
                ", data_zwrotu='" + data_zwrotu + '\'' +
                '}';
    }
}
