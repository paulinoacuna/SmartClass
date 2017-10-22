package com.colombianapps.paulino.proyectosmartclass;

import android.content.ContentValues;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.colombianapps.paulino.proyectosmartclass.utilidades.Utilidades;

public class RegistroAsignaturas extends AppCompatActivity {

    EditText campomateria,camponickname,campomaestro;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registro_asignaturas);

        campomateria=(EditText) findViewById(R.id.campoasignatura);
        campomaestro=(EditText) findViewById(R.id.campo_profesor);
        camponickname= (EditText) findViewById(R.id.camponickname);
    }

    public void onClick(View view) {

        registrarmaterias();
    }

    private void registrarmaterias() {

        ConexionSQLiteHelper conn=new ConexionSQLiteHelper(this,"bd_asignaturas",null,1);
        SQLiteDatabase db=conn.getWritableDatabase();

        ContentValues values= new ContentValues();

        values.put(Utilidades.CAMPO_NOMBREMATERIA,campomateria.getText().toString());
        values.put(Utilidades.CAMPO_NICKNAME,camponickname.getText().toString());
        values.put(Utilidades.CAMPO_MAESTRO,campomaestro.getText().toString());

        Long idresultante=db.insert(Utilidades.TABLA_ASIGNATURA,Utilidades.CAMPO_NICKNAME,values);
        limpiar();

        Toast.makeText(getApplicationContext(),"Se guardó la asignatura ",Toast.LENGTH_LONG).show();
        db.close();

        Intent intento = new Intent(RegistroAsignaturas.this,MainActivity.class);
        startActivity(intento);


    }

    private void limpiar() {

        campomateria.setText("");
        camponickname.setText("");
        campomaestro.setText("");
    }
}
