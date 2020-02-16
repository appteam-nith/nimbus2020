package com.nith.appteam.nimbus2020.Adapters;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.nith.appteam.nimbus2020.Activities.Add_talk_details;
import com.nith.appteam.nimbus2020.Models.TalkModel;
import com.nith.appteam.nimbus2020.R;
import com.squareup.picasso.Picasso;

import java.util.List;

public class TalkRecyclerViewAdapter extends
        RecyclerView.Adapter<TalkRecyclerViewAdapter.ViewHolder> {
    private Context context;
    private List<TalkModel> talksList;

    public TalkRecyclerViewAdapter(Context context, List<TalkModel> talks) {
        this.context = context;
        talksList = talks;

    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.talks_info, parent,
                false);

        return new ViewHolder(view, context);
    }

    @Override

    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.container.setAnimation(AnimationUtils.loadAnimation(context, R.anim.item_animation));
        TalkModel talks = talksList.get(position);
        String imageLink = talks.getImage();
        //holder.regUrl.setText(talks.getRegURL());
        //holder.info.setText(talks.getInfo());
        holder.date.setText(talks.getDate());
        holder.venue.setText(talks.getVenue());
        holder.name.setText(talks.getName());
        Picasso.with(context).load(imageLink).into(
                holder.imgSpkr);


    }

    @Override
    public int getItemCount() {
        return talksList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        TextView name, date, venue;
        ImageView imgSpkr;
        RelativeLayout container;

        public ViewHolder(@NonNull View itemView, final Context ctx) {
            super(itemView);
            context = ctx;
            container = itemView.findViewById(R.id.contTalk);
            name = itemView.findViewById(R.id.speakerNameID);
            imgSpkr = itemView.findViewById(R.id.speakerImageID);
            venue = itemView.findViewById(R.id.speakerVenueID);
            date = itemView.findViewById(R.id.SpeakerDate);
            imgSpkr.setClipToOutline(true);

            //   info=(TextView) itemView.findViewById(R.id.speakerInfoID);
            //  regUrl=(TextView) itemView.findViewById(R.id.regURL);
            itemView.setOnClickListener(new View.OnClickListener() {


                @Override
                public void onClick(View view) {
                    TalkModel talkModel = talksList.get(getAdapterPosition());
                    Intent intent = new Intent(context, Add_talk_details.class);
                    intent.putExtra("talk", talkModel);
                    ctx.startActivity(intent);
//                    ctx.overridePendingTransition(R.anim.ease_in, R.anim.ease_out);
                }

            });

        }

        @Override
        public void onClick(View view) {

        }
    }
}

