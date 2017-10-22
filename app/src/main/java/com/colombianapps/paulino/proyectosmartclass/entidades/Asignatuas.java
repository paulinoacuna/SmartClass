package com.colombianapps.paulino.proyectosmartclass.entidades;

import java.io.Serializable;

/**
 * Created by Paulino on 15/07/2017.
 */

public class Asignatuas implements Serializable {

    private String nombremateria;
    private String nickname;
    private String maestro;

    public String getNombremateria() {
        return nombremateria;
    }

    public void setNombremateria(String nombremateria) {
        this.nombremateria = nombremateria;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getMaestro() {
        return maestro;
    }

    public void setMaestro(String maestro) {
        this.maestro = maestro;
    }

    public Asignatuas(String nombremateria, String nickname, String maestro) {
        this.nombremateria = nombremateria;
        this.nickname = nickname;
        this.maestro = maestro;
    }

    public Asignatuas() {


    }
}
