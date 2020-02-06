package com.nith.appteam.nimbus2020.Adapters;

import android.app.Activity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.nith.appteam.nimbus2020.Models.CoreTeamModel;
import com.nith.appteam.nimbus2020.R;
import com.squareup.picasso.Picasso;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class CoreTeamAdapter extends RecyclerView.Adapter<CoreTeamAdapter.CoreTeamViewHolder> {

    List<CoreTeamModel> mCoreTeamModelList;
    Activity mActivity;

    public CoreTeamAdapter(List<CoreTeamModel> coreTeamModelList, Activity activity) {
        mCoreTeamModelList = coreTeamModelList;
        mActivity = activity;
    }

    @NonNull
    @Override
    public CoreTeamViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = View.inflate(parent.getContext(), R.layout.adapter_core_team, null);
        return new CoreTeamViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CoreTeamViewHolder holder, int position) {

        CoreTeamModel coreTeamModel = mCoreTeamModelList.get(position);
        holder.coreTeamName.setText(coreTeamModel.getName());
        holder.coreTeamPos.setText(coreTeamModel.getPosition());
        if (!coreTeamModel.getImageUrl().isEmpty()) {
            Picasso.with(mActivity).load(coreTeamModel.getImageUrl()).resize(80,
                    80).centerCrop().into(
                    holder.coreTeamImageView);
        }

    }

    @Override
    public int getItemCount() {
        return mCoreTeamModelList.size();
    }


    public class CoreTeamViewHolder extends RecyclerView.ViewHolder {

        ImageView coreTeamImageView;
        TextView coreTeamName;
        TextView coreTeamPos;

        public CoreTeamViewHolder(@NonNull View itemView) {
            super(itemView);
            coreTeamImageView = itemView.findViewById(R.id.coreTeamImageView);
            coreTeamName = itemView.findViewById(R.id.coreTeamName);
            coreTeamPos = itemView.findViewById(R.id.coreTeamPos);
        }
    }
}
