package com.kisicsoft.harutest;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.io.ByteArrayOutputStream;
import java.util.ArrayList;

public class CardviewAdapter extends RecyclerView.Adapter<CardviewAdapter.CustomViewHolder> {

    private Context context;
    private ArrayList<Cardviewitem> cardviewitemArrayList;

    public CardviewAdapter(Context context, ArrayList<Cardviewitem> cardviewitemArrayList) {
        this.context = context;
        this.cardviewitemArrayList = cardviewitemArrayList;
    }

    @NonNull
    @Override
    public CustomViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.card_view, parent, false);
        CustomViewHolder holder = new CustomViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull CardviewAdapter.CustomViewHolder holder, int position) {
        Glide.with(holder.itemView)
                .load(cardviewitemArrayList.get(position).getImageview())
                .into(holder.cardview_image);
        holder.cardview_title.setText(cardviewitemArrayList.get(position).getTitle());
        holder.cardview_star.setText(cardviewitemArrayList.get(position).getStar());
        holder.cardview_review.setText(cardviewitemArrayList.get(position).getReview());
        holder.cardview_distance.setText(cardviewitemArrayList.get(position).getDistance());

    }

    @Override
    public int getItemCount() {
        return (cardviewitemArrayList != null ? cardviewitemArrayList.size() : 0);
    }

    public class CustomViewHolder extends RecyclerView.ViewHolder {
        ImageView cardview_image;
        TextView cardview_title;
        TextView cardview_star;
        TextView cardview_review;
        TextView cardview_distance;

        public CustomViewHolder(@NonNull View itemView) {
            super(itemView);
            this.cardview_image = itemView.findViewById(R.id.cardview_image);
            this.cardview_title = itemView.findViewById(R.id.cardview_title);
            this.cardview_star = itemView.findViewById(R.id.cardview_star);
            this.cardview_review = itemView.findViewById(R.id.cardview_reivew);
            this.cardview_distance = itemView.findViewById(R.id.cardview_distance);
        }
    }
}
