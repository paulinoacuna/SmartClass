package com.colombianapps.paulino.proyectosmartclass;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.colombianapps.paulino.proyectosmartclass.utilidades.Utilidades;

/**
 * Created by Paulino on 16/07/2017.
 */

public class ConexionSQLiteHelper extends SQLiteOpenHelper {


    public ConexionSQLiteHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        db.execSQL(Utilidades.CREAR_TABLA_ASIGNATURA);
        db.execSQL(Utilidades.CREAR_TABLA_TAREANEW);
        db.execSQL(Utilidades.CREAR_TABLA_ANOTACIONES);
        db.execSQL(" create table alarma( idal integer primary key autoincrement,encabezado text, mensaje text,fecha date, hora time)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        db.execSQL("DROP TABLE IF EXIST "+Utilidades.TABLA_ASIGNATURA);
        db.execSQL("DROP TABLE IF EXIST "+Utilidades.TABLA_TAREANEW);
        db.execSQL("DROP TABLE IF EXIST "+Utilidades.TABLA_ANOTACIONES);
        db.execSQL("drop table if exists alarma" );
        db.execSQL(" create table alarma( idal integer primary key autoincrement,encabezado text, mensaje text,fecha date, hora time)");
        onCreate(db);
    }
}
