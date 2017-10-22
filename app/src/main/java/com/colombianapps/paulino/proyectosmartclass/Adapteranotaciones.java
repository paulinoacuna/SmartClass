package com.colombianapps.paulino.proyectosmartclass;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.colombianapps.paulino.proyectosmartclass.entidades.Anotaciones;

import java.util.ArrayList;

/**
 * Created by Paulino on 15/07/2017.
 */

public class Adapteranotaciones extends RecyclerView.Adapter<Adapteranotaciones.ViewHolderDatosanot> {

    ArrayList<Anotaciones> listanotas;

    public Adapteranotaciones(ArrayList<Anotaciones> listanotas) {
        this.listanotas = listanotas;
    }

    @Override
    public ViewHolderDatosanot onCreateViewHolder(ViewGroup parent, int viewType) {

        View view= LayoutInflater.from(parent.getContext())
                .inflate(R.layout.lista_anotaciones_item,parent,false);

        return new ViewHolderDatosanot(view);
    }

    @Override
    public void onBindViewHolder(ViewHolderDatosanot holder, int position) {

        holder.itemnotanombre.setText(listanotas.get(position).getNombrenota());
        holder.itemnotadescrip.setText(listanotas.get(position).getDescrpnota());

    }

    @Override
    public int getItemCount() {
        return listanotas.size();
    }

    public class ViewHolderDatosanot extends RecyclerView.ViewHolder {

        TextView itemnotanombre,itemnotadescrip;

        public ViewHolderDatosanot(View itemView) {
            super(itemView);

            itemnotanombre= (TextView) itemView.findViewById(R.id.nombrenotalistaanotacionesitem);
            itemnotadescrip= (TextView) itemView.findViewById(R.id.descripnotalistaanotacionesitem);


        }
    }
}
