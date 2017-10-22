package com.colombianapps.paulino.proyectosmartclass;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;

import com.colombianapps.paulino.proyectosmartclass.clases.UtilidadesTabs;
import com.colombianapps.paulino.proyectosmartclass.fragments.AnotacionesFragment;
import com.colombianapps.paulino.proyectosmartclass.fragments.AsignaturasFragment;
import com.colombianapps.paulino.proyectosmartclass.fragments.ContenedorFragment;
import com.colombianapps.paulino.proyectosmartclass.fragments.TareasFragment;
import com.github.clans.fab.FloatingActionMenu;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener,AsignaturasFragment
        .OnFragmentInteractionListener,TareasFragment.OnFragmentInteractionListener,
        FragmentAcercadeldesarrollador.OnFragmentInteractionListener,
        AnotacionesFragment.OnFragmentInteractionListener,
        ContenedorFragment.OnFragmentInteractionListener,FragmentColegio.OnFragmentInteractionListener{





    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        //floating menu

        FloatingActionMenu fabmenu = (FloatingActionMenu) findViewById(R.id.fabmenup);
        fabmenu.setClosedOnTouchOutside(true);

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();


        if(UtilidadesTabs.validapantalla=true){


            Fragment fragrment = new TareasFragment();
            getSupportFragmentManager().beginTransaction().
                    replace(R.id.contentenedorprincipal, fragrment).commit();


            UtilidadesTabs.validapantalla=false;

        }


        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);


    }

    public void clicksubtareas(View view) {

        Intent intentosr = new Intent(MainActivity.this,RegistroTareas.class);
        startActivity(intentosr);


    }

    public void clicksubasignaturas(View view) {

        Intent intentostr = new Intent(MainActivity.this,PersonalizarAsignaturas.class);
        startActivity(intentostr);


    }

    public void clicksubanotaciones(View view) {

        Intent intenttosr = new Intent(MainActivity.this,PersonalizarAnotaciones.class);
        startActivity(intenttosr);


    }


    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        //noinspection SimplifiableIfStatement


        if (id == R.id.action_settings_eliminar_tareasdos) {
            Intent intenttaciosn = new Intent(MainActivity.this,EliminarTareas.class);
            startActivity(intenttaciosn);
            return true;
        } else if (id == R.id.action_settings_eliminar_asignturasdos) {

            Intent inte = new Intent(MainActivity.this,EliminarAsignaturas.class);
            startActivity(inte);
            return true;


    } else if (id == R.id.action_settings_eliminar_anotacionesdos) {

            Intent inten = new Intent(MainActivity.this,EliminarAnotaciones.class);
            startActivity(inten);
            return true;


        }





        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();


        Fragment mifragment = null;
        Boolean fragmentseleccionado = false;


        if (id == R.id.nav_tareas) {
            mifragment=new TareasFragment();
            fragmentseleccionado = true;

        } else if (id == R.id.nav_asignaturas) {
            mifragment=new AsignaturasFragment();
            fragmentseleccionado = true;


        } else if (id == R.id.nav_anotaciones) {
            mifragment=new AnotacionesFragment();
            fragmentseleccionado = true;


        } else if (id == R.id.nav_horario_de_clases) {
            mifragment=new ContenedorFragment();
            fragmentseleccionado=true;


        } else if (id == R.id.nav_acerca_del_desarrollador) {
            mifragment = new FragmentAcercadeldesarrollador();
            fragmentseleccionado = true;


        } else if (id == R.id.nav_colegio_sanbenito_de_tibatí) {
            mifragment = new FragmentColegio();
            fragmentseleccionado = true;

        }
        if (fragmentseleccionado)  {
            getSupportFragmentManager().beginTransaction().
                    replace(R.id.contentenedorprincipal, mifragment).commit();
        }


        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }



    @Override
    public void onFragmentInteraction(Uri uri) {

    }
}
