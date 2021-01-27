package database;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

public class Naprawa {

    @Id
    @GeneratedValue
    @Column(name = "id_naprawy")
    private int id_naprawy;
    @Column(name = "koszt_naprawy")
    private String koszt_naprawy;
    @Column(name = "data_naprawy")
    private String data_naprawy;
    @Column(name = "id_wlasciciela")
    private String id_wlasciciela;
    @Column(name = "id_uslugi")
    private String id_uslugi;
    @Column(name = "usluga_nazwa")
    private String usluga_nazwa;
    @Column(name = "imie_nazwisko")
    private String imie_nazwisko;
    @Column(name = "marka_model")
    private String marka_model;
    @Column(name = "kosztPLN")
    private String kosztPLN;

    public Naprawa() {

    }

    public Naprawa(int id_naprawy, String koszt_naprawy, String data_naprawy, String id_wlasciciela, String id_uslugi, String usluga_nazwa, String imie_nazwisko, String marka_model, String kosztPLN) {
        this.id_naprawy = id_naprawy;
        this.koszt_naprawy = koszt_naprawy;
        this.data_naprawy = data_naprawy;
        this.id_wlasciciela = id_wlasciciela;
        this.id_uslugi = id_uslugi;
        this.usluga_nazwa = usluga_nazwa;
        this.imie_nazwisko = imie_nazwisko;
        this.marka_model = marka_model;
        this.kosztPLN = kosztPLN;
    }

    public int getId_naprawy() {
        return id_naprawy;
    }

    public void setId_naprawy(int id_naprawy) {
        this.id_naprawy = id_naprawy;
    }

    public String getKoszt_naprawy() {
        return koszt_naprawy;
    }

    public void setKoszt_naprawy(String koszt_naprawy) {
        this.koszt_naprawy = koszt_naprawy;
    }

    public String getData_naprawy() {
        return data_naprawy;
    }

    public void setData_naprawy(String data_naprawy) {
        this.data_naprawy = data_naprawy;
    }

    public String getId_wlasciciela() {
        return id_wlasciciela;
    }

    public void setId_wlasciciela(String id_wlasciciela) {
        this.id_wlasciciela = id_wlasciciela;
    }

    public String getId_uslugi() {
        return id_uslugi;
    }

    public void setId_uslugi(String id_uslugi) {
        this.id_uslugi = id_uslugi;
    }


    public String getImie_nazwisko() {
        return imie_nazwisko;
    }

    public void setImie_nazwisko(String imie_nazwisko) {
        this.imie_nazwisko = imie_nazwisko;
    }

    public String getMarka_model() {
        return marka_model;
    }

    public void setMarka_model(String marka_model) {
        this.marka_model = marka_model;
    }

    public String getUsluga_nazwa() {
        return usluga_nazwa;
    }

    public void setUsluga_nazwa(String usluga_nazwa) {
        this.usluga_nazwa = usluga_nazwa;
    }


    public String getKosztPLN() {
        return kosztPLN;
    }

    public void setKosztPLN(String kosztPLN) {
        this.kosztPLN = kosztPLN;
    }

    @Override
    public String toString() {
        return "Naprawa{" +
                "id_naprawy=" + id_naprawy +
                ", koszt_naprawy='" + koszt_naprawy + '\'' +
                ", data_naprawy='" + data_naprawy + '\'' +
                ", id_wlasciciela='" + id_wlasciciela + '\'' +
                ", id_uslugi='" + id_uslugi + '\'' +
                ", usluga_nazwa='" + usluga_nazwa + '\'' +
                ", imie_nazwisko='" + imie_nazwisko + '\'' +
                ", marka_model='" + marka_model + '\'' +
                ", kosztPLN='" + kosztPLN + '\'' +
                '}';
    }
}
