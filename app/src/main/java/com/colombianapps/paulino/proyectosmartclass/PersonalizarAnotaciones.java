package com.colombianapps.paulino.proyectosmartclass;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class PersonalizarAnotaciones extends AppCompatActivity {



    Button mibootoneliminar,mibotonagregar;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_personalizar_anotaciones);

        mibootoneliminar = (Button) findViewById(R.id.btn_direcciona_eliminar_anotaciones);
        mibotonagregar= (Button) findViewById(R.id.btn_direcciona_registro_anotaciones);

        mibootoneliminar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intento= new Intent(PersonalizarAnotaciones.this,EliminarAnotaciones.class);
                startActivity(intento);

            }
        });
        mibotonagregar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intentos= new Intent(PersonalizarAnotaciones.this,RegistroAnotaciones.class);
                startActivity(intentos);

            }
        });
}
}