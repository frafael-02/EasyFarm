package com.example.testapp.shop;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.testapp.OdabraniPesticidActivity;
import com.example.testapp.R;
import com.example.testapp.database.GlideApp;
import com.example.testapp.search.SearchActivty;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

import java.util.List;

public class ChildAdapter extends RecyclerView.Adapter<ChildAdapter.ViewHolder> {

    List<ShopChildModelClass> childModelClassList;
    Context context;

    public ChildAdapter(List<ShopChildModelClass> childModelClassList, Context context) {
        this.childModelClassList = childModelClassList;
        this.context = context;
    }

    @NonNull
    @Override
    public ChildAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(context).inflate(R.layout.child_rv_layout,null,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ChildAdapter.ViewHolder holder, int position) {
   // holder.iv_child_image.setImageResource(childModelClassList.get(position).image);
        StorageReference storageReference = FirebaseStorage.getInstance().getReference().child("/pesticidiSlike/" + childModelClassList.get(position).getPesticid().getId() + ".jpg");
        GlideApp.with(this.context).load(storageReference).into(holder.iv_child_image);
        holder.iv_child_image.setTag(storageReference.toString());
    holder.tv_child_text.setText(childModelClassList.get(position).text);
// da slika bode button
    holder.iv_child_image.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            String imageUrl = (String) holder.iv_child_image.getTag();
            Intent intent = new Intent(context, OdabraniPesticidActivity.class);
            intent.putExtra("NAME",childModelClassList.get(position).getPesticid().getNaziv());
            intent.putExtra("SLIKA", imageUrl);
            intent.putExtra("OPIS", childModelClassList.get(position).getPesticid().getOpis());

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

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            iv_child_image=itemView.findViewById(R.id.iv_child_item);
            tv_child_text=itemView.findViewById(R.id.tv_child_item);
        }
    }

    public void setFilteredList(List<ShopChildModelClass> filteredList){
        this.childModelClassList=filteredList;
        notifyDataSetChanged();
    }
}
