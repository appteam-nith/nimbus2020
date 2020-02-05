package com.nith.appteam.nimbus2020.Adapters;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.nith.appteam.nimbus2020.Activities.Add_institute_Activity_Detail;
import com.nith.appteam.nimbus2020.Models.instituteEvent;
import com.nith.appteam.nimbus2020.R;
import com.squareup.picasso.Picasso;

import java.util.List;

public class EventIRecyclerViewAdapter extends
        RecyclerView.Adapter<EventIRecyclerViewAdapter.ViewHolder> {
    private Context context;
    private List<instituteEvent> eventList;

    public EventIRecyclerViewAdapter(Context context, List<instituteEvent> events) {
        this.context = context;
        eventList = events;

    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.instituteevents_info,
                parent, false);

        return new ViewHolder(view, context);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        instituteEvent Ievents = eventList.get(position);
        String imageLinkEVE = Ievents.getImageIEVE();
        // holder.regUrlEVEI.setText(Ievents.getRegURLIEVE());
        //holder.infoEVEI.setText(Ievents.getInfoIEVE());
        holder.datEVEI.setText(Ievents.getDateIEVE());
        holder.venueEVEI.setText(Ievents.getVenueIEVE());
        holder.nameEVEI.setText(Ievents.getNameIEVE());
        Picasso.with(context).load(imageLinkEVE).placeholder(
                android.R.drawable.ic_btn_speak_now).into(holder.imgEVEVi);

    }


    @Override
    public int getItemCount() {
        return eventList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        TextView nameEVEI, datEVEI, infoEVEI, regUrlEVEI, venueEVEI;
        ImageView imgEVEVi;

        public ViewHolder(@NonNull View itemView, final Context ctx) {
            super(itemView);
            context = ctx;
            nameEVEI = itemView.findViewById(R.id.EventINameID);
            imgEVEVi = itemView.findViewById(R.id.EventIImageID);
            venueEVEI = itemView.findViewById(R.id.EventIVenueID);
            datEVEI = itemView.findViewById(R.id.EventIDate);
            //infoEVEI=(TextView) itemView.findViewById(R.id.EventIInfoID);
            //  regUrlEVEI=(TextView) itemView.findViewById(R.id.regURLEvevntI);
            itemView.setOnClickListener(new View.OnClickListener() {


                @Override
                public void onClick(View view) {
                    instituteEvent deptIns = eventList.get(getAdapterPosition());
                    Intent intent = new Intent(context, Add_institute_Activity_Detail.class);
                    intent.putExtra("instituteEvents", deptIns);
                    ctx.startActivity(intent);
                }

            });

        }

        @Override
        public void onClick(View view) {

        }
    }
}


