package com.example.ceramiczuhairkhalaf.Adapters;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.RecyclerView;

import com.example.ceramiczuhairkhalaf.CardSet;
import com.example.ceramiczuhairkhalaf.FirebaseServices;
import com.example.ceramiczuhairkhalaf.MainActivity;
import com.example.ceramiczuhairkhalaf.ProductInfoFragment;
import com.example.ceramiczuhairkhalaf.R;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class CardAdapter extends RecyclerView.Adapter<CardAdapter.ViewHolder> {
    private ArrayList<CardSet> cardsList;
    private Context context;
    private FirebaseServices fbs;
    private CardAdapter.OnItemClickListener itemClickListener;

    public CardAdapter(Context context , ArrayList<CardSet> cardsList) {
        this.cardsList = cardsList;
        this.context = context;
        this.fbs = FirebaseServices.getInstance();
        this.itemClickListener = new CardAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(int position) {
                /*
                String selectedItem = filteredList.get(position).getNameCar();
                Toast.makeText(getActivity(), "Clicked: " + selectedItem, Toast.LENGTH_SHORT).show(); */
                Bundle args = new Bundle();
                args.putParcelable("car", cardsList.get(position)); // or use Parcelable for better performance
                ProductInfoFragment cd = new ProductInfoFragment();
                cd.setArguments(args);
                FragmentTransaction ft= ((MainActivity)context).getSupportFragmentManager().beginTransaction();
                ft.replace(R.id.fragment_container,cd);
                ft.commit();
            }
        };
    }

    @NonNull
    @Override
    public CardAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.card_info,parent,false);
        return new CardAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CardAdapter.ViewHolder holder, int position) {
        CardSet cardSet = cardsList.get(position);
        holder.tvProductName.setText(cardSet.getProductName());

        holder.tvProductName.setOnClickListener(v -> {
            if (itemClickListener != null) {
                itemClickListener.onItemClick(position);
            }
        });
        holder.ivStyleImage.setOnClickListener(v -> {
            if (itemClickListener != null) {
                itemClickListener.onItemClick(position);
            }
        });

        if (cardSet.getStyleImage() == null || cardSet.getStyleImage().isEmpty())
        {
            Picasso.get().load(R.drawable.ic_launcher_background).into(holder.ivStyleImage);
        }
        else {
            Picasso.get().load(cardSet.getStyleImage()).into(holder.ivStyleImage);
        }
    }

    @Override
    public int getItemCount() {return cardsList.size();}

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        TextView tvProductName;
        ImageView ivStyleImage;
        int position;
        ViewHolder(View itemView){
            super(itemView);
            tvProductName = itemView.findViewById(R.id.tvProductNameCardInfo);
            ivStyleImage = itemView.findViewById(R.id.ivStyleImageCardInfo);
        }

        @Override
        public void onClick(View v) {
        }
    }
    public interface OnItemClickListener {
        void onItemClick(int position);
    }
    public void setOnItemClickListener(CardAdapter.OnItemClickListener listener) {
        this.itemClickListener = listener;
    }
}
