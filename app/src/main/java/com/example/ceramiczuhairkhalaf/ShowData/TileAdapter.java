package com.example.ceramiczuhairkhalaf.ShowData;

import android.view.LayoutInflater;
import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.ceramiczuhairkhalaf.AddTileData.Tile;
import com.example.ceramiczuhairkhalaf.FirebaseServices;
import com.example.ceramiczuhairkhalaf.R;

import java.util.ArrayList;
import java.util.List;

public class TileAdapter extends RecyclerView.Adapter<TileAdapter.ViewHolder> {
    private ArrayList<Tile> dataTileList;
    private LayoutInflater tInflater;
    private Context context;
    private FirebaseServices fbs;

    public TileAdapter(ArrayList<Tile> dataTileList,Context context)
    {
        this.dataTileList = dataTileList;
        this.context = context;
        this.fbs = FirebaseServices.getInstance();
    }

    @NonNull
    @Override
    public TileAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.tileinfo,parent,false);
        return new TileAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Tile tile = dataTileList.get(position);
        holder.tvTileName.setText(tile.getName());
        holder.tvCompany.setText(tile.getCompany());
        String price = Double.toString(tile.getPrice());
        holder.tvPrice.setText(price);
        String size = Double.toString(tile.getSize());
        holder.tvSize.setText(size);
    }

    @Override
    public int getItemCount() {
        return dataTileList.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder{
        TextView tvTileName, tvPrice, tvCompany , tvSize;
        int position;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            tvTileName = itemView.findViewById(R.id.tvTileNameTileInfo);
            tvPrice = itemView.findViewById(R.id.tvPriceTileInfo);
            tvSize = itemView.findViewById(R.id.tvSizeTileInfo);
            tvCompany = itemView.findViewById(R.id.tvCompanyTileInfo);
        }
    }

}
