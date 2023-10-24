package com.example.adventureapp.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.adventureapp.R;
import com.example.adventureapp.model.Adventure;

import java.util.ArrayList;

public class AdapterAdventure extends RecyclerView.Adapter<AdapterAdventure.ViewHolder> {

    private ArrayList<Adventure> adventureArrayList;
    private OnItemClickListenar clickListenar;

    public AdapterAdventure(ArrayList<Adventure> adventures, OnItemClickListenar clickListenar)
    {
        this.adventureArrayList =adventures;
        this.clickListenar = clickListenar;
    }
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View viewItem = layoutInflater.inflate(R.layout.advanture_item,parent,false);
        ViewHolder viewHolder = new ViewHolder(viewItem);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        final  Adventure adventureItem = adventureArrayList.get(position);
        holder.tvAdvantureName.setText(adventureItem.getName());
        holder.tvAdvantureCost.setText(adventureItem.getCost());

        holder.llMain.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                clickListenar.onItemClick(position);
            }
        });
    }

    @Override
    public int getItemCount() {
        return adventureArrayList.size();
    }

    public static class  ViewHolder extends  RecyclerView.ViewHolder{

        public ImageView imgAdvanture;
        public TextView tvAdvantureName;
        public TextView tvAdvantureCost;
        public LinearLayout llMain;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            imgAdvanture = itemView.findViewById(R.id.img_advanture);
            tvAdvantureName= itemView.findViewById(R.id.tv_advanture_name);
            tvAdvantureCost = itemView.findViewById(R.id.tv_advanture_cost);
            llMain = itemView.findViewById(R.id.ll_main);
        }
    }

    public interface OnItemClickListenar {
        public void onItemClick(Integer position);

    }
}
