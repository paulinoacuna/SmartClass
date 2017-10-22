package com.colombianapps.paulino.proyectosmartclass;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.colombianapps.paulino.proyectosmartclass.entidades.Tareas;

import java.util.ArrayList;

/**
 * Created by Paulino on 15/07/2017.
 */
public class Adaptertareas extends RecyclerView.Adapter<Adaptertareas.ViewHolderDatos>{

    ArrayList<Tareas> listataareas;

    public Adaptertareas(ArrayList<Tareas> listataareas) {
        this.listataareas = listataareas;
    }

    @Override
    public ViewHolderDatos onCreateViewHolder(ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext())
                .inflate(R.layout.lista_tareas_item,parent,false);


        return new ViewHolderDatos(view);
    }

    @Override
    public void onBindViewHolder(Adaptertareas.ViewHolderDatos holder, int position) {

        holder.itemnicknamelist.setText(listataareas.get(position).getNicknamenew());
        holder.itemdescriptlist.setText(listataareas.get(position).getDesriptnew());
        holder.itemfechaentregalist.setText(listataareas.get(position).getFechaentreganew());
        holder.itemfecharealizarlist.setText(listataareas.get(position).getFecharealizarnew());
        holder.itemhorarealizarlist.setText(listataareas.get(position).getHorarealizarnew());

    }


    @Override
    public int getItemCount() {
        return listataareas.size();
    }




    public class ViewHolderDatos extends RecyclerView.ViewHolder {

        TextView itemnicknamelist,itemdescriptlist,itemfechaentregalist,itemfecharealizarlist,
        itemhorarealizarlist;

        public ViewHolderDatos(View itemView) {
            super(itemView);

           itemnicknamelist= (TextView) itemView.findViewById(R.id.Nicknamelistatareasitem);
            itemdescriptlist= (TextView) itemView.findViewById(R.id.descripcionlistatareasitem);
            itemfechaentregalist= (TextView) itemView.findViewById(R.id.fechaentregalistatareasitem);
            itemfecharealizarlist= (TextView) itemView.findViewById(R.id.fecharealizacionitemtareaslista);
            itemhorarealizarlist= (TextView) itemView.findViewById(R.id.horarealizacionitemtareaslista);

        }
    }
}
