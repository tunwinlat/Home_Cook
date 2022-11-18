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
import com.homecookapp.homecook.Domain.ProductDomain;
import com.homecookapp.homecook.R;

import java.util.ArrayList;

public class ProductListingAdapter extends RecyclerView.Adapter<ProductListingAdapter.ViewHolder>{

    ArrayList<ProductDomain> productDomains;

    public ProductListingAdapter(ArrayList<ProductDomain> productDomains) {
        this.productDomains = productDomains;
    }
    @NonNull
    @Override
    public ProductListingAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View inflate = LayoutInflater.from(parent.getContext()).inflate(R.layout.viewholder_productlisting , parent, false);

        return new ProductListingAdapter.ViewHolder(inflate);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.productName.setText(productDomains.get(position).getTitle());
        holder.productType.setText(productDomains.get(position).getType());
        holder.productPrice.setText(productDomains.get(position).getPrice());
        holder.productStock.setText(productDomains.get(position).getStock());
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
                .into(holder.productPic);
    }


    @Override
    public int getItemCount() {return productDomains.size();}

    public class ViewHolder extends RecyclerView.ViewHolder{

        ImageView productPic;
        TextView productName;
        TextView productType;
        TextView productPrice;
        TextView productStock;
        ConstraintLayout mainlayout;

        public ViewHolder(@NonNull View itemView){
            super(itemView);
            productPic = itemView.findViewById(R.id.productPic);
            productName = itemView.findViewById(R.id.productTitle);
            productType = itemView.findViewById(R.id.productType);
            productPrice = itemView.findViewById(R.id.productPrice);
            productStock = itemView.findViewById(R.id.productStock);
            mainlayout = itemView.findViewById(R.id.mainlayout3);
        }
    }


}
