package com.example.testapp.api;

import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.testapp.R;
import com.example.testapp.entiteti.Message;

import java.util.List;

public class ChatAdapter extends RecyclerView.Adapter<ChatAdapter.ViewHolder>{

    private List<Message> messages;

    public ChatAdapter(List<Message> messages) {
        this.messages = messages;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public TextView messageText;

        public ImageView imageView;

        public ViewHolder(View itemView) {
            super(itemView);
            messageText = itemView.findViewById(R.id.messageText);
            imageView = itemView.findViewById(R.id.logoImage);
        }
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_message, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        Message message = messages.get(position);
        holder.messageText.setText(message.getText());
        LinearLayout.LayoutParams layoutParams = (LinearLayout.LayoutParams) holder.messageText.getLayoutParams();
        if (message.isSentByUser()) {
            holder.imageView.setImageResource(R.drawable.account);
            layoutParams.gravity = Gravity.END;

        } else {
            holder.imageView.setImageResource(R.drawable.ai_icon);
            layoutParams.gravity = Gravity.START;

        }

        holder.messageText.setLayoutParams(layoutParams);

    }

    @Override
    public int getItemCount() {
        return messages.size();
    }
}
