package com.example.rentabike.HomeAdapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.RecyclerView.Adapter;

import com.example.rentabike.R;

import java.util.ArrayList;

public class FeaturedAdapter extends RecyclerView.Adapter<FeaturedAdapter.FeaturedViewHolder> {

    ArrayList<FeaturedHelperClass> featuredBike;

    public FeaturedAdapter(ArrayList<FeaturedHelperClass> featuredBike) {
        this.featuredBike = featuredBike;
    }

    @NonNull
    @Override
    public FeaturedViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.featured_card_design,parent, false);
        FeaturedViewHolder featuredViewFolder= new FeaturedViewHolder(view);
        return featuredViewFolder;
    }

    @Override
    public void onBindViewHolder(@NonNull FeaturedViewHolder holder, int position) {

        FeaturedHelperClass featuredHelperClass=featuredBike.get(position);

        holder.image.setImageResource(featuredHelperClass.getImage());
        holder.title.setText(featuredHelperClass.getTitle());
        holder.desc.setText(featuredHelperClass.getDescription());
    }

    @Override
    public int getItemCount() {
        return featuredBike.size();
    }


    public static class FeaturedViewHolder extends RecyclerView.ViewHolder{

        ImageView image;
        TextView title,desc;
    public FeaturedViewHolder(@NonNull View itemView) {
        super(itemView);

        //Hooks
        image=itemView.findViewById(R.id.featured_image);
        title=itemView.findViewById(R.id.featured_title);
        desc=itemView.findViewById(R.id.featured_desc);

    }
}
}
