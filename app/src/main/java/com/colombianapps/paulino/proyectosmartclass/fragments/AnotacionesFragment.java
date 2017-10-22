package com.colombianapps.paulino.proyectosmartclass.fragments;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.colombianapps.paulino.proyectosmartclass.Adapteranotaciones;
import com.colombianapps.paulino.proyectosmartclass.Adapterasignaturas;
import com.colombianapps.paulino.proyectosmartclass.Adaptertareas;
import com.colombianapps.paulino.proyectosmartclass.ConexionSQLiteHelper;
import com.colombianapps.paulino.proyectosmartclass.R;
import com.colombianapps.paulino.proyectosmartclass.entidades.Anotaciones;
import com.colombianapps.paulino.proyectosmartclass.entidades.Asignatuas;
import com.colombianapps.paulino.proyectosmartclass.utilidades.Utilidades;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link AnotacionesFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link AnotacionesFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class AnotacionesFragment extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private OnFragmentInteractionListener mListener;

    RecyclerView recycleranotaciones;
    ArrayList<Anotaciones> listanotas;

    ConexionSQLiteHelper conn;

    public AnotacionesFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment AnotacionesFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static AnotacionesFragment newInstance(String param1, String param2) {
        AnotacionesFragment fragment = new AnotacionesFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View vista=inflater.inflate(R.layout.fragment_anotaciones, container, false);
        listanotas=new ArrayList<>();

        conn=new ConexionSQLiteHelper(getContext().getApplicationContext(),"bd_anotaciones",null,1);
        recycleranotaciones= (RecyclerView) vista.findViewById(R.id.recyanotid);
        recycleranotaciones.setLayoutManager(new LinearLayoutManager(getContext()));

        llenarlistanotas();

        Adapteranotaciones adapte=new Adapteranotaciones(listanotas);
        recycleranotaciones.setAdapter(adapte);


        return vista;
    }

    private void llenarlistanotas() {


        SQLiteDatabase db=conn.getReadableDatabase();

        Anotaciones nota=null;
        Cursor cursor=db.rawQuery("SELECT * FROM "+ Utilidades.TABLA_ANOTACIONES,null);

        while (cursor.moveToNext()) {
            nota=new Anotaciones();
            nota.setNombrenota(cursor.getString(0));
            nota.setDescrpnota(cursor.getString(1));

            listanotas.add(nota);



        }



    }

    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnFragmentInteractionListener) {
            mListener = (OnFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }
}
