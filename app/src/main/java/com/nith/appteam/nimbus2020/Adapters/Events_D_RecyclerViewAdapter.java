package com.nith.appteam.nimbus2020.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;


import com.nith.appteam.nimbus2020.Models.departmentEvent;
import com.nith.appteam.nimbus2020.R;
import com.squareup.picasso.Picasso;

import java.util.List;

public class Events_D_RecyclerViewAdapter extends RecyclerView.Adapter<Events_D_RecyclerViewAdapter.ViewHolder> {
    private Context context;
    private List<departmentEvent> eventListDep;
    public Events_D_RecyclerViewAdapter(Context context, List<departmentEvent> events) {
        this.context=context;
        eventListDep=events;

    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.department_events_info,parent,false);

        return new ViewHolder(view,context);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        departmentEvent Devents=eventListDep.get(position);
        String imageLinkEVED=Devents.getImageDEVE();
        holder.regUrlEVED.setText(Devents.getRegURLDEVE());
        holder.infoEVED.setText(Devents.getInfoDEVE());
        holder.datEVED.setText(Devents.getDateDEVE());
        holder.venueEVED.setText(Devents.getVenueDEVE());
        holder.nameEVED.setText(Devents.getNameDEVE());
        Picasso.get().load(imageLinkEVED).placeholder(android.R.drawable.ic_btn_speak_now).into(holder.imgEVEVD);
    }

    @Override
    public int getItemCount() {
        return eventListDep.size();
    }
    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        TextView nameEVED,datEVED,infoEVED,regUrlEVED,venueEVED;
        ImageView imgEVEVD;

        public ViewHolder(@NonNull View itemView, final Context ctx) {
            super(itemView);
            context=ctx;
            nameEVED=(TextView) itemView.findViewById(R.id.EventDNameID);
            imgEVEVD=(ImageView) itemView.findViewById(R.id.EventDImageID);
            venueEVED=(TextView) itemView.findViewById(R.id.EventDVenueID);
            datEVED=(TextView)itemView.findViewById(R.id.EventDDate);
            infoEVED=(TextView) itemView.findViewById(R.id.EventDInfoID);
            regUrlEVED=(TextView) itemView.findViewById(R.id.regURLEvevntD);
            itemView.setOnClickListener(new View.OnClickListener() {



                @Override
                public void onClick(View view) {
                    Toast.makeText(context,"Scroll Only",Toast.LENGTH_SHORT).show();
                }

            });

        }

        @Override
        public void onClick(View view) {

        }
    }
}


