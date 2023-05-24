package com.example.testapp;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.core.app.ActivityOptionsCompat;
import androidx.core.view.ViewCompat;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.testapp.entiteti.Evidencija;
import com.example.testapp.entiteti.Pesticid;
import com.example.testapp.entiteti.RecyclerViewInterface;
import com.example.testapp.entiteti.UtilityClass;
import com.example.testapp.search.SearchActivty;
import com.example.testapp.shop.ParentAdapter;
import com.example.testapp.shop.ShopChildModelClass;
import com.example.testapp.shop.ShopParentModelClass;

import java.util.ArrayList;
import java.util.List;

public class ShopFragment extends Fragment {

    RecyclerView recyclerView;
    ArrayList<ShopParentModelClass> parentModelClassArrayList;
    ArrayList<ShopChildModelClass> childModelClassArrayList;
    List<ShopChildModelClass> regularPList;
    List<ShopChildModelClass> bioPList;
    ArrayList<ShopChildModelClass> josNekajPList;
    Button searchButton;

    ParentAdapter parentAdapter;

    @Override
    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view =inflater.inflate(R.layout.fragment_shop, container, false);
        searchButton=view.findViewById(R.id.searchBar);

        recyclerView=view.findViewById(R.id.rv_parent);
        childModelClassArrayList=new ArrayList<>();
        parentModelClassArrayList=new ArrayList<>();
        regularPList =new ArrayList<>();
        bioPList=new ArrayList<>();
        josNekajPList=new ArrayList<>();

      /*  regularPList.add(new ShopChildModelClass(R.drawable.pesticid,"pesticid"));
        regularPList.add(new ShopChildModelClass(R.drawable.backwheat,"pesticid"));
        regularPList.add(new ShopChildModelClass(R.drawable.backlogin,"pesticid"));
        regularPList.add(new ShopChildModelClass(R.drawable.backwheat,"pesticid"));
        regularPList.add(new ShopChildModelClass(R.drawable.backlogin,"pesticid"));*/
        regularPList= UtilityClass.pesticidToShopList(MainActivity2.pesticidList);

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

        parentModelClassArrayList.add(new ShopParentModelClass("bezveze pesticida",josNekajPList));

        parentAdapter=new ParentAdapter(parentModelClassArrayList,view.getContext());
       recyclerView.setLayoutManager(new LinearLayoutManager(view.getContext()));
       recyclerView.setAdapter(parentAdapter);
       parentAdapter.notifyDataSetChanged();;


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
