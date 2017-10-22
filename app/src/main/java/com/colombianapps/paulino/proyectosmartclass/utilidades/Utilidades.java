package com.colombianapps.paulino.proyectosmartclass.utilidades;

/**
 * Created by Paulino on 16/07/2017.
 */

public class Utilidades {
    //Aquí se definen las tablas para almacenar la base de datos mi negro

    //tabla de las asignaturas
    public static final String TABLA_ASIGNATURA="asignatura";

    public static final String CAMPO_NOMBREMATERIA="nombremateria";
    public static final String CAMPO_NICKNAME="nickname";
    public static final String CAMPO_MAESTRO="maestro";

    public static final String CREAR_TABLA_ASIGNATURA=
            "CREATE TABLE "+TABLA_ASIGNATURA+" ("+CAMPO_NOMBREMATERIA
                    +" TEXT, "+CAMPO_NICKNAME+" TEXT, "+CAMPO_MAESTRO+" TEXT)";


    //tabla de las tareas(contiene asignaturasnickname)



    public static final String TABLA_TAREANEW="tareasnew";

    public static final String CAMPO_NICKNAMENEW="nicknamenew";
    public static final String CAMPO_DESCRIPTNEW="descriptnew";
    public static final String CAMPO_FECHAENTREGANEW="fechaentreganew";
    public static final String CAMPO_FECHAREALIARNEW="fecharealizarnew";
    public static final String CAMPO_HORAREALIZARNEW="horarealizarnew";


    public static final String CREAR_TABLA_TAREANEW=
            "CREATE TABLE "+TABLA_TAREANEW+" ("+CAMPO_NICKNAMENEW
                    +" TEXT, "+CAMPO_DESCRIPTNEW+" TEXT, "+CAMPO_FECHAENTREGANEW
                    +" TEXT, "+CAMPO_FECHAREALIARNEW+" TEXT, "+CAMPO_HORAREALIZARNEW+" TEXT)";



    //tabla de anotaciones
    public static final String TABLA_ANOTACIONES="anotaciones";

    public static final String CAMPO_NOMBRENOTA="nombrenota";
    public static final String CAMPO_DESCRIPNOTA="descripnota";

    public static final String CREAR_TABLA_ANOTACIONES=
            "CREATE TABLE "+TABLA_ANOTACIONES+" ("+CAMPO_NOMBRENOTA
                    +" TEXT, "+CAMPO_DESCRIPNOTA+" TEXT)";

    //tabla usuario




}
