package com.colombianapps.paulino.proyectosmartclass;

import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.colombianapps.paulino.proyectosmartclass.entidades.Asignatuas;
import com.colombianapps.paulino.proyectosmartclass.utilidades.Utilidades;

import java.util.ArrayList;

public class EliminarAsignaturas extends AppCompatActivity {

    Spinner comboeliminarmateria;
    TextView mostrarmateria,mostrarabrev,mostrarmaestro;
    ArrayList<String> listamaterias;
    ArrayList<Asignatuas> materiaslist;

    ConexionSQLiteHelper conn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_eliminar_asignaturas);

        conn= new ConexionSQLiteHelper(getApplicationContext(),"bd_asignaturas",null,1);

        comboeliminarmateria = (Spinner) findViewById(R.id.comboeliminarasig);

        mostrarmateria = (TextView) findViewById(R.id.sale_asignatura_eliminar);
        mostrarabrev = (TextView) findViewById(R.id.sale_nickname_eliminar);
        mostrarmaestro = (TextView) findViewById(R.id.sale_maestro_eliminar);

        consultarlistamaterias();

        ArrayAdapter<CharSequence> adaptadorr = new ArrayAdapter
                (this,android.R.layout.simple_spinner_item,listamaterias);
        comboeliminarmateria.setAdapter(adaptadorr);

        comboeliminarmateria.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                if (position!=0) {
                   mostrarmateria.setText(materiaslist.get(position-1).getNombremateria());
                    mostrarabrev.setText(materiaslist.get(position-1).getNickname());
                    mostrarmaestro.setText(materiaslist.get(position-1).getMaestro());



                }else {

                    mostrarmateria.setText("");
                    mostrarabrev.setText("");
                    mostrarmaestro.setText("");

                }

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

    }

    private void consultarlistamaterias() {

        SQLiteDatabase db=conn.getReadableDatabase();

        Asignatuas materia= null;
        materiaslist=new ArrayList<Asignatuas>();

        Cursor cursor =db.rawQuery("SELECT * FROM "+ Utilidades.TABLA_ASIGNATURA,null);

        while (cursor.moveToNext())  {

            materia =new Asignatuas();
            materia.setNombremateria(cursor.getString(0));
            materia.setNickname(cursor.getString(1));
            materia.setMaestro(cursor.getString(2));


            materiaslist.add(materia);
    }

        obtenerlista();

    }

    private void obtenerlista() {
        listamaterias=new ArrayList<String>();
        listamaterias.add("Seleccione");

        for (int i=0; i<materiaslist.size();i++)  {

            listamaterias.add(materiaslist.get(i).getNombremateria()+" - "+materiaslist.get(i).getNickname());



        }


}

    public void onClick(View view) {


        eliminarasigna();


    }

    private void eliminarasigna() {


        int idcombo= (int)comboeliminarmateria.getSelectedItemId();


        if (idcombo!=0) {
            SQLiteDatabase db=conn.getWritableDatabase();
            String [] parametros= {mostrarabrev.getText().toString()};

            db.delete(Utilidades.TABLA_ASIGNATURA,Utilidades.CAMPO_NICKNAME+"=?",parametros);
            Toast.makeText(getApplicationContext(),"Asignatura eliminada",Toast.LENGTH_LONG).show();
            mostrarabrev.setText("");
            limpiar();
            db.close();
            Intent intento = new Intent(EliminarAsignaturas.this,MainActivity.class);
            startActivity(intento);



        }else{

            Toast.makeText(getApplicationContext(),"Debe seleccionar Asignatura",Toast.LENGTH_LONG).show();

        }


    }

    private void limpiar() {

        mostrarmateria.setText("");
        mostrarmaestro.setText("");

    }
}