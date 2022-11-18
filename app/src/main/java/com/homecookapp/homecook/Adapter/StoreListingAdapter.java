package com.homecookapp.homecook.Adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.homecookapp.homecook.Domain.StoreDomain;
import com.homecookapp.homecook.R;

import java.util.ArrayList;

public class StoreListingAdapter extends RecyclerView.Adapter<StoreListingAdapter.ViewHolder>{

    ArrayList<StoreDomain> storeDomains;

    public StoreListingAdapter(ArrayList<StoreDomain> storeDomains) {
        this.storeDomains = storeDomains;
    }
    @NonNull
    @Override
    public StoreListingAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View inflate = LayoutInflater.from(parent.getContext()).inflate(R.layout.viewholder_storelisting , parent, false);

        return new StoreListingAdapter.ViewHolder(inflate);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.storeName.setText(storeDomains.get(position).getTitle());
        holder.storeType.setText(storeDomains.get(position).getType());
        holder.storeLocation.setText(storeDomains.get(position).getLocation());
        holder.storeRating.setText(storeDomains.get(position).getRating());
        String picUrl="";
        switch(position){
            case 0:{
                picUrl = "cat_1";
                break;
            }
            case 1:{
                picUrl = "cat_2";
                break;
            }
            case 2:{
                picUrl = "cat_3";
                break;
            }
            case 3:{
                picUrl = "cat_4";
                break;
            }
            case 4:{
                picUrl = "cat_5";
                break;
            }
        }
        int drawableResourceId = holder.itemView.getContext().getResources()
                .getIdentifier(picUrl, "drawable",
                        holder.itemView.getContext().getPackageName());
        Glide.with(holder.itemView.getContext())
                .load(drawableResourceId)
                .into(holder.storePic);
    }


    @Override
    public int getItemCount() {return storeDomains.size();}

    public class ViewHolder extends RecyclerView.ViewHolder{

        ImageView storePic;
        TextView storeName;
        TextView storeType;
        TextView storeLocation;
        TextView storeRating;
        ConstraintLayout mainlayout;

        public ViewHolder(@NonNull View itemView){
            super(itemView);
            storePic = itemView.findViewById(R.id.storePic);
            storeName = itemView.findViewById(R.id.storeName);
            storeType = itemView.findViewById(R.id.storeType);
            storeLocation = itemView.findViewById(R.id.storeLocation);
            storeRating = itemView.findViewById(R.id.storeRating);
            mainlayout = itemView.findViewById(R.id.mainlayout);

        }
    }
}
