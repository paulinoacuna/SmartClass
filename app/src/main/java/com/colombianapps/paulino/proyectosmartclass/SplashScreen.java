package com.colombianapps.paulino.proyectosmartclass;

import android.app.Activity;
import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;

import java.util.Random;

public class SplashScreen extends Activity {
    public int [] logosigualessminicio = {R.drawable.fondosm,R.drawable.fondosmcopy};
    public int [] logosigualescsbtinicio = {R.drawable.fondocsbt,R.drawable.fondocsbtcopy};

    private void cambiarlogocsbt() {

        ImageView cambiologocsbtinicio = (ImageView)findViewById(R.id.fondocsbtinicial);
        Random numeros = new Random();
        int posicion = numeros.nextInt(2);
        cambiologocsbtinicio.setImageResource(logosigualescsbtinicio[posicion]);
    }

    private void cambiarlogosm() {

        ImageView cambiologosminicio = (ImageView)findViewById(R.id.fondosminicial);
        Random numeros = new Random();
        int posicion = numeros.nextInt(2);
        cambiologosminicio.setImageResource(logosigualessminicio[posicion]);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {

                cambiarlogocsbt();
                cambiarlogosm();
            }
        },200);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {

                Intent intent = new Intent(SplashScreen.this,Premain.class);
                startActivity(intent);


            }
        },4000);

    }



}
