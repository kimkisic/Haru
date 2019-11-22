package com.kisicsoft.harutest;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class CardviewAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private ArrayList<Cardviewitem> cardviewitems = new ArrayList<>();
    public CardviewAdapter() {
        cardviewitems.add(new Cardviewitem(R.drawable.donkatsu,"브라운돈까스 왕십리점", "4.5", "5,701", "123m"));
        cardviewitems.add(new Cardviewitem(R.drawable.food,"스시도쿠", "4.1", "278", "276m"));
        cardviewitems.add(new Cardviewitem(R.drawable.pcc,"라이온시티 PC방 행당점", "4.7", "42", "691m"));
        cardviewitems.add(new Cardviewitem(R.drawable.cf,"9HERTZ", "4.2", "592", "801m"));
        cardviewitems.add(new Cardviewitem(R.drawable.hyun,"엔터식스왕십리역사점", "3.9", "1,121", "1,127m"));
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.card_view, parent, false);
        return new RowCell(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {

        ((RowCell)holder).imageView.setImageResource(cardviewitems.get(position).imageview);
        ((RowCell)holder).title.setText(cardviewitems.get(position).title);
        ((RowCell)holder).star.setText(cardviewitems.get(position).star);
        ((RowCell)holder).review.setText(cardviewitems.get(position).review);
        ((RowCell)holder).distance.setText(cardviewitems.get(position).distance);

    }

    @Override
    public int getItemCount() {
        return cardviewitems.size();
    }

    private class RowCell extends RecyclerView.ViewHolder {
        public ImageView imageView;
        public TextView title;
        public TextView star;
        public TextView review;
        public TextView distance;
        public RowCell(View view) {
            super(view);
            imageView = (ImageView)view.findViewById(R.id.cardview_image);
            title = (TextView)view.findViewById(R.id.cardview_title);
            star = (TextView)view.findViewById(R.id.cardview_star);
            review = (TextView)view.findViewById(R.id.cardview_reivew);
            distance = (TextView)view.findViewById(R.id.cardview_distance);
        }
    }
}
