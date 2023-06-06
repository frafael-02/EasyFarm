package com.example.testapp.shop;


import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.testapp.OdabraniPesticidActivity;
import com.example.testapp.R;
import com.example.testapp.database.GlideApp;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

import java.io.Serializable;
import java.util.List;

public class GeneralShopAdapter extends RecyclerView.Adapter<GeneralShopAdapter.ViewHolder> {

    List<ShopChildModelClass> childModelClassList;
    Context context;

    public GeneralShopAdapter(List<ShopChildModelClass> childModelClassList, Context context) {
        this.childModelClassList = childModelClassList;
        this.context = context;
    }

    @NonNull
    @Override
    public GeneralShopAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(context).inflate(R.layout.general_rv_shop_layout,null,false);
        return new GeneralShopAdapter.ViewHolder(view);
    }


    @Override
    public void onBindViewHolder(@NonNull GeneralShopAdapter.ViewHolder holder, int position) {
        // holder.iv_child_image.setImageResource(childModelClassList.get(position).image);
        StorageReference storageReference = FirebaseStorage.getInstance().getReference().child("/pesticidiSlike/" + childModelClassList.get(position).getPesticid().getId() + ".jpg");
        GlideApp.with(this.context).load(storageReference).into(holder.iv_child_image);
        holder.iv_child_image.setTag(storageReference.toString());
        holder.tv_child_text.setText(childModelClassList.get(position).text);
        if(childModelClassList.get(position).getPesticid().getVrsta() == 1)
        {
            holder.imagePesticid.setImageResource(R.drawable.herbicid_checked);
        }
        else if(childModelClassList.get(position).getPesticid().getVrsta() == 2)
        {
            holder.imagePesticid.setImageResource(R.drawable.fungicid_checked);
        }
        if(childModelClassList.get(position).getPesticid().getBio()){
            holder.bioPesticid.setVisibility(View.VISIBLE);
        }

// da slika bode button
        holder.relativeLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String imageUrl = (String) holder.iv_child_image.getTag();
                Intent intent = new Intent(context, OdabraniPesticidActivity.class);
                intent.putExtra("PESTICID", (Serializable) childModelClassList.get(position).getPesticid());
                //intent.putExtra("NAME",childModelClassList.get(position).getPesticid().getNaziv());
                intent.putExtra("SLIKA", imageUrl);
                //intent.putExtra("OPIS", childModelClassList.get(position).getPesticid().getOpis());

                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return childModelClassList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        ImageView iv_child_image;
        TextView tv_child_text;

        ImageView imagePesticid;
        TextView bioPesticid;
        TextView opisPesticid;
        RelativeLayout relativeLayout;


        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            iv_child_image=itemView.findViewById(R.id.iv_child_item);
            tv_child_text=itemView.findViewById(R.id.tv_child_item);
            imagePesticid=itemView.findViewById(R.id.vrsta_pesticida);
            bioPesticid=itemView.findViewById(R.id.bio_child_item);
            opisPesticid=itemView.findViewById(R.id.opis_child_item);
            relativeLayout=itemView.findViewById(R.id.main_relative_layout);

        }
    }

    public void setFilteredList(List<ShopChildModelClass> filteredList){
        this.childModelClassList=filteredList;
        notifyDataSetChanged();
    }

    public void setChildModelClassList(List<ShopChildModelClass> childModelClassList) {
        this.childModelClassList = childModelClassList;
    }
}
