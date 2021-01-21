package database;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

public class Egzemplarz {
    @Id
    @GeneratedValue
    @Column(name = "id_egzemplarza")
    private int id_egzemplarza;
    @Column(name = "rodzaj")
    private String rodzaj;
    @Column(name = "firma")
    private String firma;
    @Column(name = "data_produkcji")
    private String data_produkcji;
    @Column(name = "id_producenta")
    private String id_producenta;
    @Column(name = "PImNa")
    private String PImNa;

    public Egzemplarz() {
    }

    public Egzemplarz(int id_egzemplarza, String rodzaj, String firma, String data_produkcji, String id_producenta, String PImNa) {
        this.id_egzemplarza = id_egzemplarza;
        this.rodzaj = rodzaj;
        this.firma = firma;
        this.data_produkcji = data_produkcji;
        this.id_producenta = id_producenta;
        this.PImNa = PImNa;
    }

    public int getId_egzemplarza() {
        return id_egzemplarza;
    }

    public void setId_egzemplarza(int id_egzemplarza) {
        this.id_egzemplarza = id_egzemplarza;
    }

    public String getRodzaj() {
        return rodzaj;
    }

    public void setRodzaj(String rodzaj) {
        this.rodzaj = rodzaj;
    }

    public String getFirma() {
        return firma;
    }

    public void setFirma(String firma) {
        this.firma = firma;
    }

    public String getData_produkcji() {
        return data_produkcji;
    }

    public void setData_produkcji(String data_produkcji) {
        this.data_produkcji = data_produkcji;
    }

    public String getId_producenta() {
        return id_producenta;
    }

    public void setId_producenta(String id_producenta) {
        this.id_producenta = id_producenta;
    }

    public String getPImNa() {
        return PImNa;
    }

    public void setPImNa(String PImNa) {
        this.PImNa = PImNa;
    }

    @Override
    public String toString() {
        return "Egzemplarz{" +
                "id_egzemplarza=" + id_egzemplarza +
                ", rodzaj='" + rodzaj + '\'' +
                ", firma='" + firma + '\'' +
                ", data_produkcji='" + data_produkcji + '\'' +
                ", id_producenta='" + id_producenta + '\'' +
                ", PImNa='" + PImNa + '\'' +
                '}';
    }
}
