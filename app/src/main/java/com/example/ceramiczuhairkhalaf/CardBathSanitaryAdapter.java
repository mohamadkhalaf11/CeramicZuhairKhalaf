package com.example.ceramiczuhairkhalaf;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.RecyclerView;

import com.example.ceramiczuhairkhalaf.Adapters.CardAdapter;
import com.example.ceramiczuhairkhalaf.AppFace.ProductInfoFragment;
import com.example.ceramiczuhairkhalaf.Drawer.DrawerActivity;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class CardBathSanitaryAdapter extends RecyclerView.Adapter<CardBathSanitaryAdapter.ViewHolder>{
    private ArrayList<CardSetBathSanitary> cardsList;
    private Context context;
    private FirebaseServices fbs;
    private CardBathSanitaryAdapter.OnItemClickListener itemClickListener;

    public CardBathSanitaryAdapter(Context context , ArrayList<CardSetBathSanitary> cardsList) {
        this.cardsList = cardsList;
        this.context = context;
        this.fbs = FirebaseServices.getInstance();
        this.itemClickListener = new CardBathSanitaryAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(int position) {

                String selectedItem = cardsList.get(position).getName();
                Toast.makeText(context, "Clicked: " + selectedItem, Toast.LENGTH_SHORT).show();
                Bundle args = new Bundle();
                args.putParcelable("bathSanitary", cardsList.get(position)); // or use Parcelable for better performance
                BathSanitaryInfoFragment cd = new BathSanitaryInfoFragment();
                cd.setArguments(args);
                FragmentTransaction ft= ((DrawerActivity)context).getSupportFragmentManager().beginTransaction();
                ft.replace(R.id.fragment_container,cd);
                ft.commit();
            }
        };
    }
    @NonNull
    @Override
    public CardBathSanitaryAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.card_info,parent,false);
        return new CardBathSanitaryAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CardBathSanitaryAdapter.ViewHolder holder, int position) {
        CardSetBathSanitary cardSet = cardsList.get(position);
        holder.tvProductName.setText(cardSet.getName());

        holder.tvProductName.setOnClickListener(v -> {
            if (itemClickListener != null) {
                itemClickListener.onItemClick(position);
            }
        });
        holder.ivImage.setOnClickListener(v -> {
            if (itemClickListener != null) {
                itemClickListener.onItemClick(position);
            }
        });

        if (cardSet.getImage() == null || cardSet.getImage().isEmpty())
        {
            Picasso.get().load(R.drawable.ic_launcher_background).into(holder.ivImage);
        }
        else {
            Picasso.get().load(cardSet.getImage()).into(holder.ivImage);
        }
    }

    @Override
    public int getItemCount() {return cardsList.size();}

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        TextView tvProductName;
        ImageView ivImage;
        int position;
        ViewHolder(View itemView){
            super(itemView);
            tvProductName = itemView.findViewById(R.id.tvProductNameCardInfo);
            ivImage = itemView.findViewById(R.id.ivStyleImageCardInfo);
        }

        @Override
        public void onClick(View v) {
        }
    }
    public interface OnItemClickListener {
        void onItemClick(int position);
    }
    public void setOnItemClickListener(CardBathSanitaryAdapter.OnItemClickListener listener) {
        this.itemClickListener = listener;
    }
}
