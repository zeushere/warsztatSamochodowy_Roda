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
    private int koszt_naprawy;
    @Column(name = "data_naprawy")
    private String data_naprawy;
    @Column(name = "id_wlasciciela")
    private String id_wlasciciela;
    @Column(name = "id_uslugi")
    private String id_uslugi;

    public Naprawa(int id_naprawy, int koszt_naprawy, String data_naprawy, String id_wlasciciela, String id_uslugi) {
        this.id_naprawy = id_naprawy;
        this.koszt_naprawy = koszt_naprawy;
        this.data_naprawy = data_naprawy;
        this.id_wlasciciela = id_wlasciciela;
        this.id_uslugi = id_uslugi;

    }

    public int getId_naprawy() {
        return id_naprawy;
    }

    public void setId_naprawy(int id_naprawy) {
        this.id_naprawy = id_naprawy;
    }

    public int getKoszt_naprawy() {
        return koszt_naprawy;
    }

    public void setKoszt_naprawy(int koszt_naprawy) {
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

    @Override
    public String toString() {
        return "Naprawa{" +
                "id_naprawy=" + id_naprawy +
                ", koszt_naprawy=" + koszt_naprawy +
                ", data_naprawy='" + data_naprawy + '\'' +
                ", id_wlasciciela='" + id_wlasciciela + '\'' +
                ", id_uslugi='" + id_uslugi + '\'' +
                '}';
    }

}
