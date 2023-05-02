package com.example.testapp;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import com.example.testapp.database.DatabaseQueries;
import com.example.testapp.entiteti.Evidencija;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link EvidencijaFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class EvidencijaFragment extends Fragment implements SelectListener {

    RecyclerView recyclerView;

    MyAdapter myAdapter;






    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public EvidencijaFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment EvidencijaFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static EvidencijaFragment newInstance(String param1, String param2) {
        EvidencijaFragment fragment = new EvidencijaFragment();
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
        // Inflate the layout for this fragment
      View view =inflater.inflate(R.layout.fragment_evidencija, container, false);
      recyclerView = view.findViewById(R.id.evidencijaListId);
        recyclerView.setLayoutManager(new LinearLayoutManager(view.getContext()));
        recyclerView.setHasFixedSize(true);


        myAdapter = new MyAdapter(view.getContext(), (ArrayList<Evidencija>) MainActivity2.evidencijaList, this);
      recyclerView.setAdapter(myAdapter);

        FloatingActionButton mButton = (FloatingActionButton) view.findViewById(R.id.addEvidencijaButton);
        mButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // here you set what you want to do when user clicks your button,

                    Intent myIntent = new Intent(getActivity(), NovaEvidencijaActivity.class);
                    // myIntent.putExtra("key", value); //Optional parameters

                    startActivity(myIntent);


            }
        });
    Button novoPoljeBtn = view.findViewById(R.id.button3);
    novoPoljeBtn.setOnClickListener(new View.OnClickListener(){

      @Override
      public void onClick(View v)
      {
          Intent myIntent = new Intent(getActivity(), NovoPoljeActivity.class);
          // myIntent.putExtra("key", value); //Optional parameters

          startActivity(myIntent);
      }

    });


      return view;


    }

    @Override
    public void onItemClicked(Evidencija evidencija) {
        Intent myIntent = new Intent(getActivity(), OdabranaEvidencijaActivity.class);
        myIntent.putExtra("evidencija", evidencija);
        startActivity(myIntent);
    }

    @Override
    public void onResume()
    {
        super.onResume();
        if(isVisible())
        {
            recyclerView.setAdapter(myAdapter);
        }
    }





}