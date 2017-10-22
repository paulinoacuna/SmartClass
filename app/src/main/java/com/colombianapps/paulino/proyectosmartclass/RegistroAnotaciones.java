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

public class RegistroAnotaciones extends AppCompatActivity {

    EditText cambonombrenota,campodescripnota;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registro_anotaciones);

        cambonombrenota = (EditText) findViewById(R.id.camponombrenota);
        campodescripnota = (EditText) findViewById(R.id.campodescripnota);
    }

    public void onClick(View view) {

        registrarnotas();
    }

    private void registrarnotas() {

        ConexionSQLiteHelper conn=new ConexionSQLiteHelper(this,"bd_anotaciones",null,1);
        SQLiteDatabase db=conn.getWritableDatabase();

        ContentValues values= new ContentValues();

        values.put(Utilidades.CAMPO_NOMBRENOTA,cambonombrenota.getText().toString());
        values.put(Utilidades.CAMPO_DESCRIPNOTA,campodescripnota.getText().toString());

        Long idresultante=db.insert(Utilidades.TABLA_ANOTACIONES,Utilidades.CAMPO_NOMBRENOTA,values);

        limpiar();
        Toast.makeText(getApplicationContext(),"Anotación Guardada ",Toast.LENGTH_LONG).show();
        db.close();

        Intent intento = new Intent(RegistroAnotaciones.this,MainActivity.class);
        startActivity(intento);

    }

    private void limpiar() {

        cambonombrenota.setText("");
        campodescripnota.setText("");

    }
}
