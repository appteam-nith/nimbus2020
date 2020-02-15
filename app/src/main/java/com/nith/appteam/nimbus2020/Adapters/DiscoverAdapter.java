package com.nith.appteam.nimbus2020.Adapters;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.nith.appteam.nimbus2020.Models.DiscoverModel;
import com.nith.appteam.nimbus2020.R;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;


public class DiscoverAdapter extends RecyclerView.Adapter<DiscoverAdapter.DiscoverViewHolder> {

    List<DiscoverModel> mDiscoverModelList;
    Activity mActivity;

    public DiscoverAdapter(List<DiscoverModel> discoverModelList, Activity activity) {
        mDiscoverModelList = discoverModelList;
        mActivity = activity;
    }

    @NonNull
    @Override
    public DiscoverViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.discover_item, parent, false);
        return new DiscoverViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull DiscoverViewHolder holder, int position) {

        DiscoverModel discoverModel = mDiscoverModelList.get(position);
        holder.eventName.setText(discoverModel.getEventName());
//        holder.location.setText(discoverModel.getLocation());
//        holder.time.setText(discoverModel.getTime());

    }

    @Override
    public int getItemCount() {
        return mDiscoverModelList.size();
    }


    public class DiscoverViewHolder extends RecyclerView.ViewHolder {

        TextView eventName;
        TextView location;
        TextView time;

        public DiscoverViewHolder(@NonNull View itemView) {
            super(itemView);
            eventName = itemView.findViewById(R.id.eventName);
            location = itemView.findViewById(R.id.location);
            time = itemView.findViewById(R.id.time);

        }
    }
}
