package com.example.testapp.api;

import android.annotation.SuppressLint;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.graphics.drawable.Drawable;
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

import org.apache.poi.sl.usermodel.Line;

import java.util.List;

public class ChatAdapter extends RecyclerView.Adapter<ChatAdapter.ViewHolder>{

    private List<Message> messages;

    public ChatAdapter(List<Message> messages) {
        this.messages = messages;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public TextView messageText;

      //  public ImageView imageView;
        public LinearLayout linearLayout;

        public ViewHolder(View itemView) {
            super(itemView);
            messageText = itemView.findViewById(R.id.messageText);
            //imageView = itemView.findViewById(R.id.logoImage);
            linearLayout=itemView.findViewById(R.id.linearLayout);
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
          // holder.imageView.setImageResource(R.drawable.account);
            @SuppressLint("UseCompatLoadingForDrawables")
            Drawable img = holder.messageText.getContext().getResources().getDrawable( R.drawable.account_chat1 );
            holder.messageText.setCompoundDrawablesWithIntrinsicBounds( img, null, null, null);
            holder.messageText.
                    getBackground().setColorFilter(Color.rgb(108,197,29), PorterDuff.Mode.SRC_ATOP);
            holder.linearLayout.setGravity(Gravity.START);
            layoutParams.gravity = Gravity.END;

        } else {
            //holder.imageView.setImageResource(R.drawable.ai_icon);
            holder.messageText.
                    getBackground().setColorFilter(Color.rgb(174,220,129),PorterDuff.Mode.SRC_ATOP);
            holder.messageText.setTextAlignment(View.TEXT_ALIGNMENT_TEXT_END);
            layoutParams.gravity = Gravity.END;

        }

        holder.messageText.setLayoutParams(layoutParams);

    }

    @Override
    public int getItemCount() {
        return messages.size();
    }
}
