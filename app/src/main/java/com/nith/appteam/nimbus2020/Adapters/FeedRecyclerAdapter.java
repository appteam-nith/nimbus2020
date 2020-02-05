package com.nith.appteam.nimbus2020.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.nith.appteam.nimbus2020.R;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class FeedRecyclerAdapter extends RecyclerView.Adapter<FeedRecyclerAdapter.viewHolder> {
    private Context context;
    private ArrayList<String> arrayList;

    public FeedRecyclerAdapter(Context context, ArrayList<String> arrayList) {
        this.context = context;
        this.arrayList = arrayList;
    }

    @NonNull
    @Override
    public viewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.recycler_item_feed, parent, false);
        return new viewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull viewHolder holder, int position) {
        String img = arrayList.get(position);
        Picasso.with(context)
                .load(img)
                .placeholder(R.drawable.nimbus_logo)
                .into(holder.feedImage);
    }

    @Override
    public int getItemCount() {
        return arrayList.size();
    }

    class viewHolder extends RecyclerView.ViewHolder {
        private ImageView feedImage;

        private viewHolder(@NonNull View itemView) {
            super(itemView);
            feedImage = itemView.findViewById(R.id.feed_image);
        }
    }
}
