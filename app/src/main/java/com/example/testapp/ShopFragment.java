package com.example.testapp;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.testapp.entiteti.UtilityClass;
import com.example.testapp.search.SearchActivty;
import com.example.testapp.shop.GeneralShopAdapter;
import com.example.testapp.shop.ParentAdapter;
import com.example.testapp.shop.ShopChildModelClass;
import com.example.testapp.shop.ShopParentModelClass;

import java.util.ArrayList;
import java.util.List;

public class ShopFragment extends Fragment {

    RecyclerView recyclerView;
    RecyclerView recyclerView2;
    ArrayList<ShopParentModelClass> parentModelClassArrayList;
    ArrayList<ShopChildModelClass> childModelClassArrayList;
    List<ShopChildModelClass> regularPList;
    List<ShopChildModelClass> bioPList;
    List<ShopChildModelClass> dolePList;
    GeneralShopAdapter generalShopAdapter;
    Button searchButton;

    ParentAdapter parentAdapter;

    @Override
    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        ((AppCompatActivity) getActivity()).getSupportActionBar().show();

        getActivity().setTitle("Pronađi pesticid");
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view =inflater.inflate(R.layout.fragment_shop, container, false);
        searchButton=view.findViewById(R.id.searchBar);

        recyclerView=view.findViewById(R.id.rv_parent);
        recyclerView2=view.findViewById(R.id.rv_general);
        childModelClassArrayList=new ArrayList<>();
        parentModelClassArrayList=new ArrayList<>();
        regularPList =new ArrayList<>();
        bioPList=new ArrayList<>();
        dolePList=new ArrayList<>();

      /*  regularPList.add(new ShopChildModelClass(R.drawable.pesticid,"pesticid"));
        regularPList.add(new ShopChildModelClass(R.drawable.backwheat,"pesticid"));
        regularPList.add(new ShopChildModelClass(R.drawable.backlogin,"pesticid"));
        regularPList.add(new ShopChildModelClass(R.drawable.backwheat,"pesticid"));
        regularPList.add(new ShopChildModelClass(R.drawable.backlogin,"pesticid"));*/
        regularPList= UtilityClass.pesticidToShopList(MainActivity2.pesticidList);
        dolePList=UtilityClass.pesticidToShopList(MainActivity2.pesticidList);

        parentModelClassArrayList.add(new ShopParentModelClass("Pregled pesticida", (ArrayList<ShopChildModelClass>) regularPList));

       /* bioPList.add(new ShopChildModelClass(R.drawable.pesticid,"pesticid"));
        bioPList.add(new ShopChildModelClass(R.drawable.backwheat,"pesticid"));
        bioPList.add(new ShopChildModelClass(R.drawable.backlogin,"pesticid"));
        bioPList.add(new ShopChildModelClass(R.drawable.backwheat,"pesticid"));
        bioPList.add(new ShopChildModelClass(R.drawable.backlogin,"pesticid"));*/

        bioPList= UtilityClass.pesticidToShopBioList(MainActivity2.pesticidList);


        parentModelClassArrayList.add(new ShopParentModelClass("Pregled BIO pesticida", (ArrayList<ShopChildModelClass>) bioPList));

      /*  josNekajPList.add(new ShopChildModelClass(R.drawable.pesticid,"pesticid"));
        josNekajPList.add(new ShopChildModelClass(R.drawable.backwheat,"pesticid"));
        josNekajPList.add(new ShopChildModelClass(R.drawable.backlogin,"pesticid"));
        josNekajPList.add(new ShopChildModelClass(R.drawable.backwheat,"pesticid"));
        josNekajPList.add(new ShopChildModelClass(R.drawable.backlogin,"pesticid"));*/

        parentAdapter=new ParentAdapter(parentModelClassArrayList,view.getContext());
        generalShopAdapter=new GeneralShopAdapter(dolePList,view.getContext());
       recyclerView.setLayoutManager(new LinearLayoutManager(view.getContext()));
        recyclerView2.setLayoutManager(new LinearLayoutManager(view.getContext()));
       recyclerView.setAdapter(parentAdapter);
       recyclerView2.setAdapter(generalShopAdapter);
       parentAdapter.notifyDataSetChanged();
       generalShopAdapter.notifyDataSetChanged();


        searchButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), SearchActivty.class);
                startActivity(intent);
            }
        });


        return view;
    }



}
