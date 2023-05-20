package com.example.testapp;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.example.testapp.entiteti.Pesticid;


import java.util.ArrayList;

public class PesticidAdapter extends ArrayAdapter<Pesticid> {
    private Context mContext;
    private ArrayList<Pesticid> mObjects;

    public PesticidAdapter(Context context, ArrayList<Pesticid> objects) {
        super(context, 0, objects);
        mContext = context;
        mObjects = objects;
        mObjects.add(0, null);

    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.spinner_dropdown_layout, parent, false);
        }

        TextView textView = (TextView) convertView.findViewById(R.id.text1);
        Pesticid objekt = getItem(position);
        if (objekt != null) {
            textView.setText(objekt.getNaziv());
        }
        else
            textView.setText("Odaberite pesticid");

        return convertView;
    }

    @Override
    public View getDropDownView(int position, View convertView, ViewGroup parent) {
        TextView textView = (TextView) LayoutInflater.from(mContext).inflate(android.R.layout.simple_spinner_dropdown_item, parent, false);
        if(position != 0)
        {
            Pesticid myObject = mObjects.get(position);
            textView.setText(myObject.getNaziv());
            return textView;
        }
        else
        {
            textView.setText("Odaberite pesticid");
            return textView;
        }





    }

    @Override
    public Pesticid getItem(int position) {

        return mObjects.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public String toString() {
        return mObjects.toString();
    }

}
