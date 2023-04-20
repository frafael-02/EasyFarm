package com.example.testapp;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.testapp.entiteti.Polje;

import java.util.ArrayList;

public class PoljeAdapter extends ArrayAdapter<Polje> {
    private Context mContext;
    private ArrayList<Polje> mObjects;

    public PoljeAdapter(Context context, ArrayList<Polje> objects) {
        super(context, 0, objects);
        mContext = context;
        mObjects = objects;
        mObjects.add(0, null);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(android.R.layout.simple_spinner_dropdown_item, parent, false);
        }

        TextView textView = (TextView) convertView.findViewById(android.R.id.text1);
        Polje objekt = getItem(position);
        if (objekt != null) {
            textView.setText(objekt.getNaziv());
        }

        return convertView;
    }

    @Override
    public View getDropDownView(int position, View convertView, ViewGroup parent) {
        TextView textView = (TextView) LayoutInflater.from(mContext).inflate(android.R.layout.simple_spinner_dropdown_item, parent, false);
       if(mObjects.get(position) != null)
       {
           Polje myObject = mObjects.get(position);
           textView.setText(myObject.getNaziv());
           return textView;
       }
       return null;

    }

    @Override
    public Polje getItem(int position) {
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
