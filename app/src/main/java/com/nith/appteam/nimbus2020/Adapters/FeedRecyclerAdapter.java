package com.nith.appteam.nimbus2020.Adapters;

import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.URLUtil;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.nith.appteam.nimbus2020.Models.FeedItem;
import com.nith.appteam.nimbus2020.R;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class FeedRecyclerAdapter extends RecyclerView.Adapter<FeedRecyclerAdapter.viewHolder> {
    private Context context;
    private ArrayList<FeedItem> arrayList;
    private FeedItem currentFeed;
    private String postUrl;
    private int position;

    public FeedRecyclerAdapter(Context context, ArrayList<FeedItem> arrayList) {
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
        this.position = position;
        currentFeed = arrayList.get(position);
        String img = currentFeed.getImageUrl();
        postUrl = currentFeed.getSocialUrl();
        Picasso.with(context)
                .load(img)
                .placeholder(R.drawable.nimbus_logo)
                .into(holder.feedImage);
    }

    @Override
    public int getItemCount() {
        return arrayList.size();
    }

    class viewHolder extends RecyclerView.ViewHolder implements View.OnLongClickListener {
        private ImageView feedImage;

        private viewHolder(@NonNull View itemView) {
            super(itemView);
            feedImage = itemView.findViewById(R.id.feed_image);
            feedImage.setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View v) {
                    FeedItem currentFeed = arrayList.get(getAdapterPosition());
                    postUrl = currentFeed.getSocialUrl();
                    try {
                        if (!URLUtil.isValidUrl(postUrl)) {
                            Toast.makeText(context, " This is not a valid link", Toast.LENGTH_LONG).show();
                        } else {
                            Intent intent = new Intent(Intent.ACTION_VIEW);
                            intent.setData(Uri.parse(postUrl));
                            context.startActivity(intent);
                        }
                    } catch (ActivityNotFoundException e) {
                        Toast.makeText(context, " You don't have any browser to open web page", Toast.LENGTH_LONG).show();
                    }
                    return true;
                }
            });
        }

        @Override
        public boolean onLongClick(View v) {
            return false;
        }
    }
}
