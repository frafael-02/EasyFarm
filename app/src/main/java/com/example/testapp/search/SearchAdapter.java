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
import com.example.testapp.entiteti.RecyclerViewInterface;
import com.example.testapp.shop.ChildAdapter;
import com.example.testapp.shop.ShopChildModelClass;

import java.util.List;

public class SearchAdapter extends RecyclerView.Adapter<SearchAdapter.ViewHolder> {
    private final RecyclerViewInterface recyclerViewInterface;
    List<ShopChildModelClass> childModelClassList;
    Context context;

    public SearchAdapter(List<ShopChildModelClass> childModelClassList, Context context, RecyclerViewInterface recyclerViewInterface) {
        this.childModelClassList = childModelClassList;
        this.context = context;
        this.recyclerViewInterface=recyclerViewInterface;
    }

    @NonNull
    @Override
    public SearchAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(context).inflate(R.layout.search_cards,null,false);
        return new SearchAdapter.ViewHolder(view, recyclerViewInterface);
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

    public static class ViewHolder extends RecyclerView.ViewHolder {

        ImageView iv_child_image;
        TextView tv_child_text;

        public ViewHolder(@NonNull View itemView, RecyclerViewInterface recyclerViewInterface) {
            super(itemView);
            iv_child_image=itemView.findViewById(R.id.iv_child_item);
            tv_child_text=itemView.findViewById(R.id.tv_child_item);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                  if(recyclerViewInterface !=null){
                      int pos= getAdapterPosition();
                      if(pos!=RecyclerView.NO_POSITION){
                          recyclerViewInterface.OnItemClick(pos);
                      }
                  }
                }
            });
        }
    }

    public void setFilteredList(List<ShopChildModelClass> filteredList){
        this.childModelClassList=filteredList;
        notifyDataSetChanged();
    }
}
