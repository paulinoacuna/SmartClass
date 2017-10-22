package com.colombianapps.paulino.proyectosmartclass;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.colombianapps.paulino.proyectosmartclass.entidades.Asignatuas;
import com.colombianapps.paulino.proyectosmartclass.entidades.Tareas;
import com.colombianapps.paulino.proyectosmartclass.utilidades.Utilidades;

import java.util.ArrayList;

public class EliminarTareas extends AppCompatActivity {



    Spinner comboeliminartar;
    TextView mnicknamenew,mdescriptnew,mfechaentreganew,mfecharealizarnew,mhorarealizarnew;
    ArrayList<String> listatareas;
    ArrayList<Tareas> tareaslist;

    ConexionSQLiteHelper conn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_eliminar_tareas);

        conn= new ConexionSQLiteHelper(getApplicationContext(),"bd_tareas",null,1);

        comboeliminartar = (Spinner) findViewById(R.id.comboeliminartareasas);

        mnicknamenew = (TextView) findViewById(R.id.sale_materia_eliminar);
         mdescriptnew = (TextView) findViewById(R.id.sale_tarea_eliminar);
        mfechaentreganew = (TextView) findViewById(R.id.sale_fechaentrega_eliminar);
        mfecharealizarnew = (TextView) findViewById(R.id.sale_fecharealización_eliminar);
        mhorarealizarnew = (TextView) findViewById(R.id.sale_horarealizacion_eliminar);


        consultarlistamaterias();

        ArrayAdapter<CharSequence> adaptadorr = new ArrayAdapter
                (this,android.R.layout.simple_spinner_item,listatareas);
       comboeliminartar.setAdapter(adaptadorr);

        comboeliminartar.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                if (position!=0) {
                   mnicknamenew.setText(tareaslist.get(position-1).getNicknamenew());
                    mdescriptnew.setText(tareaslist.get(position-1).getDesriptnew());
                    mfechaentreganew.setText(tareaslist.get(position-1).getFechaentreganew());
                    mfecharealizarnew.setText(tareaslist.get(position-1).getFecharealizarnew());
                    mhorarealizarnew.setText(tareaslist.get(position-1).getHorarealizarnew());


                }else {

                    mnicknamenew.setText("");
                    mdescriptnew.setText("");
                    mfechaentreganew.setText("");
                    mfecharealizarnew.setText("");
                    mhorarealizarnew.setText("");
                }

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

    }

    private void consultarlistamaterias() {

        SQLiteDatabase db=conn.getReadableDatabase();

        Tareas tarea= null;
         tareaslist=new ArrayList<Tareas>();

        Cursor cursor =db.rawQuery("SELECT * FROM "+ Utilidades.TABLA_TAREANEW,null);

        while (cursor.moveToNext())  {

            tarea =new Tareas();
            tarea.setNicknamenew(cursor.getString(0));
            tarea.setDesriptnew(cursor.getString(1));
            tarea.setFechaentreganew(cursor.getString(2));
            tarea.setFecharealizarnew(cursor.getString(3));
            tarea.setHorarealizarnew(cursor.getString(4));



            tareaslist.add(tarea);
        }

        obtenerlista();

    }

    private void obtenerlista() {
        listatareas=new ArrayList<String>();
       listatareas.add("Seleccione");

        for (int i=0; i<tareaslist.size();i++)  {

           listatareas.add(tareaslist.get(i).getNicknamenew()+" - "+tareaslist.get(i).getDesriptnew());



        }


    }

    public void onClick(View view) {


        eliminarasigna();


    }

    private void eliminarasigna() {


        int idcombo= (int)comboeliminartar.getSelectedItemId();


        if (idcombo!=0) {
            SQLiteDatabase db=conn.getWritableDatabase();
            String [] parametros= {mdescriptnew.getText().toString()};

            db.delete(Utilidades.TABLA_TAREANEW,Utilidades.CAMPO_DESCRIPTNEW+"=?",parametros);
            Toast.makeText(getApplicationContext(),"Tarea eliminada",Toast.LENGTH_LONG).show();
            mdescriptnew.setText("");
            limpiar();
            db.close();

            Intent intento = new Intent(EliminarTareas.this,MainActivity.class);
            startActivity(intento);



        }else{

            Toast.makeText(getApplicationContext(),"Debe seleccionar una Tarea",Toast.LENGTH_LONG).show();

        }


    }

    private void limpiar() {

        mnicknamenew.setText("");
        mfechaentreganew.setText("");
        mfecharealizarnew.setText("");
        mhorarealizarnew.setText("");


    }
}
