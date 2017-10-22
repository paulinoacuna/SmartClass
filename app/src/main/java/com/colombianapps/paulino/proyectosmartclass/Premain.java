package com.colombianapps.paulino.proyectosmartclass;

import android.app.Activity;
import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import java.util.Random;

public class Premain extends Activity {



    public int [] mciudadesimg = {R.drawable.fondo_1,R.drawable.fondo_2,R.drawable.fondo_3,R.drawable.fondo_4,
            R.drawable.fondo_5,R.drawable.fondo_6,R.drawable.fondo_7,R.drawable.fondo_8,R.drawable.fondo_9,R.drawable.fondo_10,R.drawable.fondo_11,
            R.drawable.fondo_12,R.drawable.fondo_13,R.drawable.fondo_14,R.drawable.fondo_15,R.drawable.fondo_16,R.drawable.fondo_17,R.drawable.fondo_18,R.drawable.fondo_19,
            R.drawable.fondo_20,R.drawable.fondo_21,R.drawable.fondo_22,R.drawable.fondo_23,
            R.drawable.fondo_24,R.drawable.fondo_25,R.drawable.fondo_26,R.drawable.fondo_27,R.drawable.fondo_28,R.drawable.fondo_29,R.drawable.fondo_30,
            R.drawable.fondo_31,R.drawable.fondo_32,R.drawable.fondo_33,R.drawable.fondo_34,R.drawable.fondo_35,R.drawable.fondo_36,
            R.drawable.fondo_37,R.drawable.fondo_38,R.drawable.fondo_39,R.drawable.fondo_40,R.drawable.fondo_41,R.drawable.fondo_42,R.drawable.fondo_43,
            R.drawable.fondo_44,R.drawable.fondo_45,R.drawable.fondo_46,R.drawable.fondo_47,R.drawable.fondo_48,R.drawable.fondo_49,R.drawable.fondo_50
    };

    public void cambiarimagen() {

        ImageView ivciudad = (ImageView)findViewById(R.id.mainfondo);
        Random numeros = new Random();
        int posicion = numeros.nextInt(50);
        ivciudad.setImageResource(mciudadesimg[posicion]);

    }
    //Declarar variables: tipo de objeto + nombre

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_premain);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {

                cambiarimagen();
            }
        },200);


    }


    public void btn_cambiarimagen_click (View view) {

        Intent intentar = new Intent(Premain.this,MainActivity.class);
        startActivity(intentar);

    }

}
