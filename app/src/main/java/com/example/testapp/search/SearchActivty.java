package com.example.testapp.search;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.testapp.MainActivity2;
import com.example.testapp.OdabraniPesticidActivity;
import com.example.testapp.R;
import com.example.testapp.entiteti.RecyclerViewInterface;
import com.example.testapp.entiteti.UtilityClass;
import com.example.testapp.shop.ChildAdapter;
import com.example.testapp.shop.ShopChildModelClass;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;


public class SearchActivty extends AppCompatActivity implements RecyclerViewInterface {

    private RecyclerView recyclerView;
    private List<ShopChildModelClass> itemList;
    private SearchAdapter itemAdapter;
    private SearchView searchView;

    private boolean i;
    private boolean f;
    private boolean h;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);
        i=false;
        h=false;
        f=false;



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

        itemAdapter=new SearchAdapter(itemList,this,this);
        recyclerView.setAdapter(itemAdapter);

        if((getIntent().getExtras() != null))
        {
            if((int)getIntent().getExtras().get("VRSTA") == 1)
            {
                h=true;
               CheckBox checkBox = findViewById(R.id.herbicid);
               checkBox.setChecked(true);

                ;
            }
            else if((int)getIntent().getExtras().get("VRSTA") == 2)
            {
                f=true;
                CheckBox checkBox = findViewById(R.id.fungicid);
                checkBox.setChecked(true);
            }
            else{
                i=true;
                CheckBox checkBox = findViewById(R.id.insekticid);
                checkBox.setChecked(true);
            }
            itemAdapter.setFilteredList(filterCheckBox(itemList, i, f, h));
        }
    }

    private void filterList(String text) {
        List<ShopChildModelClass> filteredList = new ArrayList<>();
        if(text.equals(""))
        {
            itemAdapter.setFilteredList(itemList);
            itemAdapter.setFilteredList(filterCheckBox(itemList, i, f, h));

        }
        else{
            for(ShopChildModelClass item:itemList){
                if(item.getItemName().toLowerCase().contains(text.toLowerCase())){
                    filteredList.add(item);
                }

            }
            if(filteredList.isEmpty()){
                Toast.makeText(this, "Nema rezultata", Toast.LENGTH_SHORT).show();
                itemAdapter.setFilteredList(filteredList);
            }
            else{

                itemAdapter.setFilteredList(filterCheckBox(filteredList, i, f, h));
            }
        }

    }

    @Override
    public void OnItemClick(int position) {
        Intent intent = new Intent(SearchActivty.this, OdabraniPesticidActivity.class);
        StorageReference storageReference = FirebaseStorage.getInstance().getReference().child("/pesticidiSlike/" + itemList.get(position).getPesticid().getId() + ".jpg");
        String imageUrl = storageReference.toString();
       // intent.putExtra("NAME",itemList.get(position).getPesticid().getNaziv());
        intent.putExtra("PESTICID", itemList.get(position).getPesticid());
        intent.putExtra("SLIKA",imageUrl);
        //intent.putExtra("OPIS", itemList.get(position).getPesticid().getOpis());

        startActivity(intent);
    }

    public void onCheckBoxChecked(View view){
        CheckBox checkBox=findViewById(view.getId());




        switch (view.getId()){
            case R.id.insekticid:
            if(checkBox.isChecked()){
              //  Toast.makeText(this, "Filter insekticida", Toast.LENGTH_SHORT).show();
                i=true;

            }
            else {
               // Toast.makeText(this, "Filter isključen", Toast.LENGTH_SHORT).show();
                i=false;
            }
            break;
            case R.id.fungicid:
                if(checkBox.isChecked()){
                    f=true;
                    //Toast.makeText(this, "Filter fungicida", Toast.LENGTH_SHORT).show();
                }
                else {
                    f=false;

                  //  Toast.makeText(this, "Filter isključen", Toast.LENGTH_SHORT).show();
                }
                break;
            case R.id.herbicid:
                if(checkBox.isChecked()){
             h=true;
                    //Toast.makeText(this, "Filter herbicida", Toast.LENGTH_SHORT).show();
                }
                else {
                    h=false;
                    //Toast.makeText(this, "Filter isključen", Toast.LENGTH_SHORT).show();
                }
                break;
        }
        itemAdapter.setFilteredList(filterCheckBox(itemList, i, f, h));

    }

    public List<ShopChildModelClass> filterCheckBox(List<ShopChildModelClass> lista, boolean i, boolean f, boolean h)
    {
        List<ShopChildModelClass> filtriranaLista = new ArrayList<>(lista);
        if(i)
        {
            if(f)
            {
                if(h)
                {
                    return lista;
                }
                else{
                    filtriranaLista = filtriranaLista.stream().filter(p -> p.getPesticid().getVrsta() == 3 || p.getPesticid().getVrsta() == 2).collect(Collectors.toList());
                    return filtriranaLista;
                }
            }
            else if(h)
            {
                filtriranaLista = filtriranaLista.stream().filter(p -> p.getPesticid().getVrsta() == 1 || p.getPesticid().getVrsta() == 3).collect(Collectors.toList());
                return filtriranaLista;
            }
            else{
                filtriranaLista=filtriranaLista.stream().filter(p -> p.getPesticid().getVrsta() == 3).collect(Collectors.toList());
                return  filtriranaLista;
            }
        }
        else if(f)
        {
            if(h)
            {
                filtriranaLista=filtriranaLista.stream().filter(p -> p.getPesticid().getVrsta() == 2 || p.getPesticid().getVrsta() == 1).collect(Collectors.toList());
                return  filtriranaLista;
            }
            else{
                filtriranaLista=filtriranaLista.stream().filter(p -> p.getPesticid().getVrsta() == 2).collect(Collectors.toList());
                return  filtriranaLista;
            }
        }
        else if(h)
        {
            filtriranaLista=filtriranaLista.stream().filter(p -> p.getPesticid().getVrsta() == 1).collect(Collectors.toList());
            return  filtriranaLista;
        }
        return lista;
    }



}
