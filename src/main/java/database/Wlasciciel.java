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
    @Column(name = "marka_samochodu_wlasciciela")
    private String marka_samochodu_wlasciciela;
    @Column(name = "model_samochodu_wlasciciela")
    private String model_samochodu_wlasciciela;


    public Wlasciciel() {
    }

    public Wlasciciel(int id_wlasciciela, String imie_wlasciciela, String nazwisko_wlasciciela, String marka_samochodu_wlasciciela, String model_samochodu_wlasciciela) {
        this.id_wlasciciela = id_wlasciciela;
        this.imie_wlasciciela = imie_wlasciciela;
        this.nazwisko_wlasciciela = nazwisko_wlasciciela;
        this.marka_samochodu_wlasciciela = marka_samochodu_wlasciciela;
        this.model_samochodu_wlasciciela = model_samochodu_wlasciciela;
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

    public String getMarka_samochodu_wlasciciela() {
        return marka_samochodu_wlasciciela;
    }

    public void setMarka_samochodu_wlasciciela(String marka_samochodu_wlasciciela) {
        this.marka_samochodu_wlasciciela = marka_samochodu_wlasciciela;
    }

    public String getModel_samochodu_wlasciciela() {
        return model_samochodu_wlasciciela;
    }

    public void setModel_samochodu_wlasciciela(String model_samochodu_wlasciciela) {
        this.model_samochodu_wlasciciela = model_samochodu_wlasciciela;
    }

    @Override
    public String toString() {
        return "Wlasciciel{" +
                "id_wlasciciela=" + id_wlasciciela +
                ", imie_wlasciciela='" + imie_wlasciciela + '\'' +
                ", nazwisko_wlasciciela='" + nazwisko_wlasciciela + '\'' +
                ", marka_samochodu_wlasciciela='" + marka_samochodu_wlasciciela + '\'' +
                ", model_samochodu_wlasciciela='" + model_samochodu_wlasciciela + '\'' +
                '}';
    }
}
