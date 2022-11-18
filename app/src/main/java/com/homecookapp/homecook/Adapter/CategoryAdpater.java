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
import com.homecookapp.homecook.Domain.CategoryDomain;
import com.homecookapp.homecook.R;

import java.util.ArrayList;

public class CategoryAdpater extends RecyclerView.Adapter<CategoryAdpater.ViewHolder> {

    ArrayList<CategoryDomain> categoryDomains;

    public CategoryAdpater(ArrayList<CategoryDomain> categoryDomains) {
        this.categoryDomains = categoryDomains;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View inflate = LayoutInflater.from(parent.getContext()).inflate(R.layout.viewholder_category , parent, false);

        return new ViewHolder(inflate);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.categoryName.setText(categoryDomains.get(position).getTitle());
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
                .into(holder.categoryPic);
    }

    @Override
    public int getItemCount() {
        return categoryDomains.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{

        TextView categoryName;
        ImageView categoryPic;
        ConstraintLayout mainlayout;

        public ViewHolder(@NonNull View itemView){
            super(itemView);
            categoryName = itemView.findViewById(R.id.categoryName);
            categoryPic = itemView.findViewById(R.id.categoryPic);
            mainlayout = itemView.findViewById(R.id.mainlayout);
        }
    }
}
