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

import com.nith.appteam.nimbus2020.Activities.Add_exhibition_details;
import com.nith.appteam.nimbus2020.Models.ExhibitionModel;
import com.nith.appteam.nimbus2020.R;
import com.squareup.picasso.Picasso;

import java.util.List;

public class ExhibitionRecyclerViewAdapter extends
        RecyclerView.Adapter<ExhibitionRecyclerViewAdapter.ViewHolder> {
    private Context context;
    private List<ExhibitionModel> exhibitionList;

    public ExhibitionRecyclerViewAdapter(Context context, List<ExhibitionModel> exhibition) {
        this.context = context;
        exhibitionList = exhibition;

    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.exhibition_info,
                parent, false);

        return new ViewHolder(view, context);
    }

    @Override

    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        holder.container.setAnimation(AnimationUtils.loadAnimation(context, R.anim.item_animation));
        ExhibitionModel exhibitions = exhibitionList.get(position);
        String imageLink = exhibitions.getImageExh();
        //holder.regUrl.setText(exhibitions.getRegURLExh());
        //holder.info.setText(exhibitions.getInfoExh());
        holder.date.setText(exhibitions.getDateExh());
        holder.venue.setText(exhibitions.getVenueExh());
        holder.name.setText(exhibitions.getNameExh());
        Picasso.with(context).load(imageLink).placeholder(android.R.drawable.ic_btn_speak_now).into(
                holder.imgExh);


    }

    @Override
    public int getItemCount() {
        return exhibitionList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        TextView name, date, venue;
        ImageView imgExh;
        RelativeLayout container;

        public ViewHolder(@NonNull View itemView, final Context ctx) {
            super(itemView);

            context = ctx;
            container = itemView.findViewById(R.id.contExh);
            name = itemView.findViewById(R.id.exhibtionNameID);
            imgExh = itemView.findViewById(R.id.exhibitionImageID);
            venue = itemView.findViewById(R.id.exhibitionVenueID);
            date = itemView.findViewById(R.id.ExhibitionDate);
            imgExh.setClipToOutline(true);
            //info=(TextView) itemView.findViewById(R.id.exhibtionInfoID);
            //regUrl=(TextView) itemView.findViewById(R.id.regURLExhibtion);
            itemView.setOnClickListener(new View.OnClickListener() {


                @Override
                public void onClick(View view) {
                    ExhibitionModel exhModel = exhibitionList.get(getAdapterPosition());
                    Intent intent = new Intent(context, Add_exhibition_details.class);
                    intent.putExtra("exhibition", exhModel);
                    ctx.startActivity(intent);
                }

            });

        }

        @Override
        public void onClick(View view) {

        }
    }
}

