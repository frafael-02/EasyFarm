package com.example.testapp;

import android.os.Bundle;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.testapp.entiteti.UtilityClass;
import com.example.testapp.shop.ChildAdapter;
import com.example.testapp.shop.ShopChildModelClass;

import java.util.ArrayList;
import java.util.List;


public class SearchActivty extends AppCompatActivity {

    private RecyclerView recyclerView;
    private List<ShopChildModelClass> itemList;
    private ChildAdapter itemAdapter;
    private SearchView searchView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);

        searchView=findViewById(R.id.searchView);
        searchView.clearFocus();
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                filterList(newText);
                return false;
            }
        });

        recyclerView=findViewById(R.id.recyclerView);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
        itemList= UtilityClass.pesticidToShopList(MainActivity2.pesticidList);

     /*   itemList.add(new ShopChildModelClass(R.drawable.pesticid,"pesticid"));
        itemList.add(new ShopChildModelClass(R.drawable.backwheat,"pesticid"));
        itemList.add(new ShopChildModelClass(R.drawable.backlogin,"pesticid"));
        itemList.add(new ShopChildModelClass(R.drawable.backwheat,"pesticid"));
        itemList.add(new ShopChildModelClass(R.drawable.backlogin,"pesticid"));*/

        itemAdapter=new ChildAdapter(itemList,this);
        recyclerView.setAdapter(itemAdapter);
    }

    private void filterList(String text) {
        List<ShopChildModelClass> filteredList = new ArrayList<>();
        for(ShopChildModelClass item:itemList){
            if(item.getItemName().toLowerCase().contains(text.toLowerCase())){
                filteredList.add(item);
            }
        }
        if(filteredList.isEmpty()){
            Toast.makeText(this, "Nema rezultata", Toast.LENGTH_SHORT).show();
        }
        else{
itemAdapter.setFilteredList(filteredList);
        }
    }
}
