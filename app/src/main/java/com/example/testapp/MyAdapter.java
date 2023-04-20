package com.example.testapp;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.example.testapp.entiteti.Evidencija;

import java.util.ArrayList;

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.MyViewHolder>{

    Context context;

    ArrayList<Evidencija> list;

    private SelectListener listener;

    public MyAdapter(Context context, ArrayList<Evidencija> list, SelectListener listener)
    {
        this.context=context;
        this.list=list;
        this.listener = listener;
    }


    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(context).inflate(R.layout.item, parent, false);
        return new MyViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {

        Evidencija evidencija = list.get(position);
        holder.imePolja.setText(evidencija.getPoljeIme());
        holder.arkodId.setText(evidencija.getArkodId());
        holder.imePesticida.setText(evidencija.getImePesticida());
        holder.imeBiljke.setText(evidencija.getImeBiljke());
        holder.datum.setText(evidencija.getVrijemeStart().getDayOfMonth() + " " + evidencija.getVrijemeStart().getMonthValue() + " " + evidencija.getVrijemeStart().getYear());
        holder.cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listener.onItemClicked(list.get(position)); //uzimanje odabranog itema iz liste recycleviewa
            }
        });


    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder{

        TextView imePesticida;
        TextView imePolja;
        TextView datum;
        TextView imeBiljke;
        TextView arkodId;
        public CardView cardView;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            imePesticida = itemView.findViewById(R.id.pesticidView);
            imePolja = itemView.findViewById(R.id.nazivView);
            imeBiljke = itemView.findViewById(R.id.kulturaView);
            datum = itemView.findViewById(R.id.datumView);
            arkodId = itemView.findViewById(R.id.arkodView);

            cardView=itemView.findViewById(R.id.itemId);
        }
    }
}
