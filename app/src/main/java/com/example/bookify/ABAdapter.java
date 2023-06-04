package com.example.bookify;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.Locale;

public class ABAdapter extends RecyclerView.Adapter<ABAdapter.ABViewholder>{


 Context context;
 ArrayList<SellingBook> list;

    public ABAdapter(Context context, ArrayList<SellingBook> list) {
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public ABViewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View v= LayoutInflater.from(context).inflate(R.layout.row_book_list, parent, false);
        return new ABViewholder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull ABViewholder holder, int position) {
     SellingBook selling =list.get(position);
        holder.bookname.setText(selling.getName());
        holder.price.setText(selling.getPrice());
        holder.qunatity.setText(selling.getQuantity());
        holder.delivery.setText(selling.getDelivery());
        holder.Description.setText(selling.getDescription());
        holder.author.setText(selling.getAuthor());
        holder.contact.setText(selling.getContact());
    }
    @Override
    public int getItemCount()
    {
        return list.size();
    }
    public  void filteredList (ArrayList<SellingBook> filterList){
        list=filterList;
        notifyDataSetChanged();
    }
    public static class ABViewholder extends RecyclerView.ViewHolder{
        TextView bookname,price,qunatity,delivery,Description,author,contact;
        public ABViewholder(@NonNull View itemView) {
            super(itemView);
            bookname = itemView.findViewById(R.id.name);
            price = itemView.findViewById(R.id.Price);
            qunatity= itemView.findViewById(R.id.quantity);
            delivery = itemView.findViewById(R.id.Delivery);
            Description=itemView.findViewById(R.id.description);
            author=itemView.findViewById(R.id.author);
            contact=itemView.findViewById(R.id.Contact);
        }
    }
}
