package com.example.testapp.search;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.testapp.R;
import com.example.testapp.shop.ChildAdapter;
import com.example.testapp.shop.ShopChildModelClass;

import java.util.List;

public class SearchAdapter extends RecyclerView.Adapter<SearchAdapter.ViewHolder> {

    List<ShopChildModelClass> childModelClassList;
    Context context;

    public SearchAdapter(List<ShopChildModelClass> childModelClassList, Context context) {
        this.childModelClassList = childModelClassList;
        this.context = context;
    }

    @NonNull
    @Override
    public SearchAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(context).inflate(R.layout.search_cards,null,false);
        return new SearchAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull SearchAdapter.ViewHolder holder, int position) {
        holder.iv_child_image.setImageResource(childModelClassList.get(position).getImage());
        holder.tv_child_text.setText(childModelClassList.get(position).getText());
// da slika bode button
//holder.iv_child_image.setOnClickListener();
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
