package com.colombianapps.paulino.proyectosmartclass.entidades;

import java.io.Serializable;

/**
 * Created by Paulino on 16/07/2017.
 */

public class Tareas implements Serializable {

    private String nicknamenew ;
    private String desriptnew;
    private String fechaentreganew ;
    private String fecharealizarnew;
    private String horarealizarnew ;

    public String getNicknamenew() {
        return nicknamenew;
    }

    public void setNicknamenew(String nicknamenew) {
        this.nicknamenew = nicknamenew;
    }

    public String getDesriptnew() {
        return desriptnew;
    }

    public void setDesriptnew(String desriptnew) {
        this.desriptnew = desriptnew;
    }

    public String getFechaentreganew() {
        return fechaentreganew;
    }

    public void setFechaentreganew(String fechaentreganew) {
        this.fechaentreganew = fechaentreganew;
    }

    public String getFecharealizarnew() {
        return fecharealizarnew;
    }

    public void setFecharealizarnew(String fecharealizarnew) {
        this.fecharealizarnew = fecharealizarnew;
    }

    public String getHorarealizarnew() {
        return horarealizarnew;
    }

    public void setHorarealizarnew(String horarealizarnew) {
        this.horarealizarnew = horarealizarnew;
    }

    public Tareas(String nicknamenew, String desriptnew, String fechaentreganew, String fecharealizarnew, String horarealizarnew) {
        this.nicknamenew = nicknamenew;
        this.desriptnew = desriptnew;
        this.fechaentreganew = fechaentreganew;
        this.fecharealizarnew = fecharealizarnew;
        this.horarealizarnew = horarealizarnew;
    }

    public Tareas(){



    }
}
