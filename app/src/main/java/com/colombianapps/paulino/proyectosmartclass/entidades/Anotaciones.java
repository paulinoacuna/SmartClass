package com.colombianapps.paulino.proyectosmartclass.entidades;

import java.io.Serializable;

/**
 * Created by Paulino on 16/07/2017.
 */

public class Anotaciones implements Serializable{



    public String getNombrenota() {
        return nombrenota;
    }

    public void setNombrenota(String nombrenota) {
        this.nombrenota = nombrenota;
    }

    public String getDescrpnota() {
        return descrpnota;
    }

    public void setDescrpnota(String descrpnota) {
        this.descrpnota = descrpnota;
    }

    private String nombrenota;
    private String descrpnota;

    public Anotaciones(String nombrenota, String descrpnota) {
        this.nombrenota = nombrenota;
        this.descrpnota = descrpnota;
    }
    public Anotaciones()  {


    }
}
