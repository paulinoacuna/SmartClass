package com.colombianapps.paulino.proyectosmartclass;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Paulino on 20/07/2017.
 */

public class SeccionesAdapter extends FragmentStatePagerAdapter {


    private final List<Fragment> listafragments=new ArrayList<>();
    private final List<String> listatitulos=new ArrayList<>();

    public SeccionesAdapter(FragmentManager fm) {
        super(fm);
    }

    public void addFragment(Fragment fragment,String titulo){
        listafragments.add(fragment);
        listatitulos.add(titulo);


    }

    @Override
    public CharSequence getPageTitle(int position) {
        return listatitulos.get(position);
    }

    @Override
    public Fragment getItem(int position) {
        return listafragments.get(position);
    }

    @Override
    public int getCount() {
        return listafragments.size();
    }
}
