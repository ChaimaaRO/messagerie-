package com.example.whatsappchatappclone.adapter;

import android.content.Context;
import android.os.Message;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.whatsappchatappclone.R;
import com.example.whatsappchatappclone.model.ChatMessage;

import java.util.ArrayList;
import java.util.List;

public class AdapterChatWhatsapp extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private final int CHAT_ME = 100;
    private final int CHAT_YOU=200;
    private List<ChatMessage> items =new ArrayList<>();
    private Context ctx;
    private OnItemClickListener mOnItemClickListener;
    public interface OnItemClickListener{
        void onItemClick(View view, ChatMessage obj, int position );

    }
    public void setOnItemClickListener(final OnItemClickListener mItemClickListener){
        this.mOnItemClickListener = mItemClickListener;
    }


    public AdapterChatWhatsapp(Context ctx) {
        this.ctx = ctx;
    }

//    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder( ViewGroup parent, int viewType) {
        RecyclerView.ViewHolder vh;
        if(viewType ==CHAT_ME){
            View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_chat_whatsapp_me, parent,false);
            vh = new ItemViewHolder(v);

        } else {
            View v =LayoutInflater.from(parent.getContext()).inflate(R.layout.item_chat_whatsapp_telegram_you,parent,false);
            vh = new ItemViewHolder(v);
        }
        return vh;
    }
    //replace the content of a view
    @Override
    public void onBindViewHolder( RecyclerView.ViewHolder holder,final  int position) {
        if ( holder instanceof ItemViewHolder){
//            final ChatMessage m = items.get(position);
            final ChatMessage m = items.get(holder.getAdapterPosition());
            ItemViewHolder vItem = (ItemViewHolder)  holder;
            vItem.text_content.setText(m.getContent());
            vItem.text_time.setText(m.getDate());
            vItem.lyt_parent.setOnClickListener(new View.OnClickListener(){

                @Override
                public void onClick(View view) {
                    if(mOnItemClickListener != null){
//                        mOnItemClickListener.onItemClick(view,m,position);
                        mOnItemClickListener.onItemClick(view,m,holder.getAdapterPosition());
                    }

                }
            });
        }

    }
// return the size of your data set

    @Override
    public int getItemCount() {
        return items.size();
    }




    public class ItemViewHolder extends RecyclerView.ViewHolder{
        public TextView text_content;
        public TextView text_time;
        public View lyt_parent;

        public ItemViewHolder (View v ){
            super(v);
            text_content = v.findViewById(R.id.text_content);
            text_time = v.findViewById(R.id.text_time);
            lyt_parent = v.findViewById(R.id.lyt_parent);
        }

    }

    @Override
    public int getItemViewType(int position) {
        return this.items.get(position).isFromMe() ? CHAT_ME : CHAT_YOU;
    }
    public void insertItem(ChatMessage item){
        this.items.add(item);
        notifyItemInserted(getItemCount());
    }
    public void setItems (List<ChatMessage> items ){ this.items = items ;}
}
