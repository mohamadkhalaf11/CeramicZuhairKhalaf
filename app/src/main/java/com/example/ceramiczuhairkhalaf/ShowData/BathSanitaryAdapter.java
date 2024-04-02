package com.example.ceramiczuhairkhalaf.ShowData;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.ceramiczuhairkhalaf.AddTileData.BathSanitary;
import com.example.ceramiczuhairkhalaf.AddTileData.Tile;
import com.example.ceramiczuhairkhalaf.FirebaseServices;
import com.example.ceramiczuhairkhalaf.R;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class BathSanitaryAdapter extends RecyclerView.Adapter<BathSanitaryAdapter.ViewHolder> {
    private ArrayList<BathSanitary> dataBathSanitaryList;
    private LayoutInflater tInflater;
    private Context context;
    private FirebaseServices fbs;

    public BathSanitaryAdapter(ArrayList<BathSanitary> dataBathSanitaryList, Context context) {
        this.dataBathSanitaryList = dataBathSanitaryList;
        this.tInflater = tInflater;
        this.context = context;
        this.fbs = fbs;
    }

    @NonNull
    @Override
    public BathSanitaryAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.bathsanitaryinfo,parent,false);
        return new BathSanitaryAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull BathSanitaryAdapter.ViewHolder holder, int position) {
        BathSanitary bathSanitary = dataBathSanitaryList.get(position);
        holder.tvName.setText(bathSanitary.getName());
        String price = Double.toString(bathSanitary.getPrice());
        holder.tvPrice.setText(price+ " ₪");
        String size = Double.toString(bathSanitary.getSize());
        holder.tvSize.setText(size);
        holder.tvMadeIn.setText(bathSanitary.getMadeIn());
        if(bathSanitary.getImage() == null || bathSanitary.getImage().isEmpty())
        {
            Picasso.get().load(R.drawable.baseline_grid_view_24).into(holder.ivImage);
        }
        else
        {
            Picasso.get().load(bathSanitary.getImage()).into(holder.ivImage);

        }
    }

    @Override
    public int getItemCount() {
        return dataBathSanitaryList.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder{
        TextView tvName, tvPrice, tvMadeIn , tvSize;
        ImageView ivImage;
        int position;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            tvName = itemView.findViewById(R.id.tvBathSanitaryNameInfo);
            tvPrice = itemView.findViewById(R.id.tvPriceInfo);
            tvSize = itemView.findViewById(R.id.tvSizeInfo);
            ivImage = itemView.findViewById(R.id.ivBathSanitaryImageInfo);
            tvMadeIn = itemView.findViewById(R.id.tvMadeInInfo);
        }
    }
}
