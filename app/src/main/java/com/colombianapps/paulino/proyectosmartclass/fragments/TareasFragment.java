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

import com.colombianapps.paulino.proyectosmartclass.Adapterasignaturas;
import com.colombianapps.paulino.proyectosmartclass.Adaptertareas;
import com.colombianapps.paulino.proyectosmartclass.ConexionSQLiteHelper;
import com.colombianapps.paulino.proyectosmartclass.R;
import com.colombianapps.paulino.proyectosmartclass.entidades.Asignatuas;
import com.colombianapps.paulino.proyectosmartclass.entidades.Tareas;
import com.colombianapps.paulino.proyectosmartclass.utilidades.Utilidades;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link TareasFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link TareasFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class TareasFragment extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private OnFragmentInteractionListener mListener;

    RecyclerView recyclertareas;
    ArrayList<Tareas> listadetareas;

    ConexionSQLiteHelper conn;


    public TareasFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment TareasFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static TareasFragment newInstance(String param1, String param2) {
        TareasFragment fragment = new TareasFragment();
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
        View vista=inflater.inflate(R.layout.fragment_tareas, container, false);
        listadetareas=new ArrayList<>();

        conn=new ConexionSQLiteHelper(getContext().getApplicationContext(),"bd_tareas",null,1);
        recyclertareas= (RecyclerView) vista.findViewById(R.id.recyclertareasid);
        recyclertareas.setLayoutManager(new LinearLayoutManager(getContext()));

        llenarlistatareas();

        Adaptertareas adaptert=new Adaptertareas(listadetareas);
         recyclertareas.setAdapter(adaptert);


        return vista;
    }

    private void llenarlistatareas() {

        SQLiteDatabase db=conn.getReadableDatabase();
        Tareas tarea=null;

        Cursor cursor=db.rawQuery("SELECT * FROM "+ Utilidades.TABLA_TAREANEW,null);

        while (cursor.moveToNext()) {
            tarea=new Tareas();

            tarea.setNicknamenew(cursor.getString(0));
            tarea.setDesriptnew(cursor.getString(1));
            tarea.setFechaentreganew(cursor.getString(2));
            tarea.setFecharealizarnew(cursor.getString(3));
            tarea.setHorarealizarnew(cursor.getString(4));


            listadetareas.add(tarea);

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
