package com.homecookapp.homecook.Adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import com.homecookapp.homecook.Domain.CategoryDomain;
import com.homecookapp.homecook.Domain.StoreDomain;
import com.homecookapp.homecook.R;

import java.util.ArrayList;

public class MerchantAdapter extends RecyclerView.Adapter<MerchantAdapter.ViewHolder>{

    ArrayList<StoreDomain> storeDomains;

    public MerchantAdapter(ArrayList<StoreDomain> storeDomains) {
        this.storeDomains = storeDomains;
    }
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View inflate = LayoutInflater.from(parent.getContext()).inflate(R.layout.viewholder_storelisting , parent, false);

        return new MerchantAdapter.ViewHolder(inflate);
    }

    @Override
    public void onBindViewHolder(@NonNull CategoryAdpater.ViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return storeDomains.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{

        TextView storeName;
        ImageView storePic;
        ConstraintLayout mainlayout;

        public ViewHolder(@NonNull View itemView){
            super(itemView);
            categoryName = itemView.findViewById(R.id.categoryName);
            categoryPic = itemView.findViewById(R.id.categoryPic);
            mainlayout = itemView.findViewById(R.id.mainlayout);
        }
    }
}
