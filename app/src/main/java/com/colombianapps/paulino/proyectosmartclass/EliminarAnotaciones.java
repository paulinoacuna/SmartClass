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

import com.colombianapps.paulino.proyectosmartclass.entidades.Anotaciones;
import com.colombianapps.paulino.proyectosmartclass.entidades.Asignatuas;
import com.colombianapps.paulino.proyectosmartclass.utilidades.Utilidades;

import java.util.ArrayList;

public class EliminarAnotaciones extends AppCompatActivity {

    Spinner comboeliminarnota;
    TextView mostrarnotanombre,mostrardescrip;
    ArrayList<String> listanotas;
    ArrayList<Anotaciones> notaslist;

    ConexionSQLiteHelper conn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_eliminar_anotaciones);

        conn= new ConexionSQLiteHelper(getApplicationContext(),"bd_anotaciones",null,1);
        comboeliminarnota = (Spinner) findViewById(R.id.comboeliminarnotas);

        mostrarnotanombre = (TextView) findViewById(R.id.sale_nombenota_eliminar);
        mostrardescrip = (TextView) findViewById(R.id.sale_descripnota_eliminar);

        consultarlistamaterias();

        ArrayAdapter<CharSequence> adaptadorer = new ArrayAdapter
                (this,android.R.layout.simple_spinner_item,listanotas);
        comboeliminarnota.setAdapter(adaptadorer);

        comboeliminarnota.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                if (position!=0) {
                    mostrarnotanombre.setText(notaslist.get(position-1).getNombrenota());
                    mostrardescrip.setText(notaslist.get(position-1).getDescrpnota());


                }else {

                    mostrarnotanombre.setText("");
                    mostrardescrip.setText("");
                }

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });


    }

    private void consultarlistamaterias() {
        SQLiteDatabase db=conn.getReadableDatabase();

        Anotaciones nota= null;
        notaslist=new ArrayList<Anotaciones>();

        Cursor cursor =db.rawQuery("SELECT * FROM "+ Utilidades.TABLA_ANOTACIONES,null);

        while (cursor.moveToNext())  {

            nota =new Anotaciones();
            nota.setNombrenota(cursor.getString(0));
            nota.setDescrpnota(cursor.getString(1));


            notaslist.add(nota);
        }

        obtenerlista();



    }

    private void obtenerlista() {


        listanotas=new ArrayList<String>();
        listanotas.add("Seleccione");

        for (int i=0; i<notaslist.size();i++)  {

            listanotas.add(notaslist.get(i).getNombrenota()+" - "+notaslist.get(i).getDescrpnota());



        }


    }


    public void onClick(View view) {

        eliminarnota();

    }

    private void eliminarnota() {


        int idcombo= (int)comboeliminarnota.getSelectedItemId();


        if (idcombo!=0) {
            SQLiteDatabase db=conn.getWritableDatabase();
            String [] parametros= {mostrarnotanombre.getText().toString()};

            db.delete(Utilidades.TABLA_ANOTACIONES,Utilidades.CAMPO_NOMBRENOTA+"=?",parametros);
            Toast.makeText(getApplicationContext(),"Anotación eliminada",Toast.LENGTH_LONG).show();
            mostrarnotanombre.setText("");
            limpiar();
            db.close();

            Intent intento = new Intent(EliminarAnotaciones.this,MainActivity.class);
            startActivity(intento);


        }else{

            Toast.makeText(getApplicationContext(),"Debe seleccionar una anotación",Toast.LENGTH_LONG).show();

        }


    }

    private void limpiar() {

        mostrardescrip.setText("");

    }
}
