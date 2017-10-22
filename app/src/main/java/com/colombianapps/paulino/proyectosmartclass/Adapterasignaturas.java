package com.colombianapps.paulino.proyectosmartclass;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.colombianapps.paulino.proyectosmartclass.entidades.Asignatuas;

import org.w3c.dom.Text;

import java.util.ArrayList;

/**
 * Created by Paulino on 15/07/2017.
 */

public class Adapterasignaturas extends RecyclerView.Adapter<Adapterasignaturas.ViewHolderDatosasig> {

    ArrayList<Asignatuas> listaasinaturas;

    public Adapterasignaturas(ArrayList<Asignatuas> listaasinaturas) {
        this.listaasinaturas = listaasinaturas;


    }

    @Override
    public ViewHolderDatosasig onCreateViewHolder(ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext())
                .inflate(R.layout.lista_asignaturas_item,parent,false);
        return new ViewHolderDatosasig(view);

    }

    @Override
    public void onBindViewHolder(ViewHolderDatosasig holder, int position) {

        holder.itemnombreasig.setText(listaasinaturas.get(position).getNombremateria());
        holder.itemnicknameasig.setText(listaasinaturas.get(position).getNickname());
        holder.itemmaestroasig.setText(listaasinaturas.get(position).getMaestro());
    }

    @Override
    public int getItemCount() {
        return listaasinaturas.size();


    }

    public class ViewHolderDatosasig extends RecyclerView.ViewHolder {

        TextView itemnombreasig,itemnicknameasig,itemmaestroasig;


        public ViewHolderDatosasig(View itemView) {
            super(itemView);

            itemnombreasig= (TextView) itemView.findViewById(R.id.Asignaturalistaasignaturasitem);
            itemnicknameasig= (TextView) itemView.findViewById(R.id.abreviaturalistaasignaturasitem);
            itemmaestroasig= (TextView) itemView.findViewById(R.id.maestroitemlista);
        }


    }
}
