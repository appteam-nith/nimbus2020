package com.nith.appteam.nimbus2020.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.nith.appteam.nimbus2020.Models.ScheduleModel;
import com.nith.appteam.nimbus2020.R;

import java.util.List;

public class ScheduleRecyclerViewAdapter extends RecyclerView.Adapter<ScheduleRecyclerViewAdapter.ViewHolder> {
    private Context context;
    private List<ScheduleModel> scheduleList;

    public ScheduleRecyclerViewAdapter(Context context, List<ScheduleModel> schedule) {
        this.context = context;
        scheduleList = schedule;

    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.schedule_add, parent, false);

        return new ViewHolder(view, context);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        ScheduleModel scheduleModel = scheduleList.get(position);
        //    String imageLink=scheduleModel.getImage();
        //holder.regUrl.setText(talks.getRegURL());
        //holder.info.setText(talks.getInfo());
        holder.deptNAme.setText(scheduleModel.getDeptSch());
        holder.date.setText(scheduleModel.getTimeSch());
        holder.venue.setText(scheduleModel.getVenueSch());
        holder.name.setText(scheduleModel.getNameSch());
        // Picasso.with(context).load(imageLink).placeholder(android.R.drawable.ic_btn_speak_now).into(holder.imgSpkr);
    }


    @Override
    public int getItemCount() {
        return scheduleList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        TextView name, date, venue, deptNAme;

        public ViewHolder(@NonNull View itemView, final Context ctx) {
            super(itemView);
            context = ctx;
            name = itemView.findViewById(R.id.EventSchNameID);
            deptNAme = itemView.findViewById(R.id.EventSchVenueID);
            venue = itemView.findViewById(R.id.EventSchVenueID);
            date = itemView.findViewById(R.id.EventSchDate);

            //   info=(TextView) itemView.findViewById(R.id.speakerInfoID);
            //  regUrl=(TextView) itemView.findViewById(R.id.regURL);
            itemView.setOnClickListener(new View.OnClickListener() {


                @Override
                public void onClick(View view) {
                    Toast.makeText(context, "Scroll only", Toast.LENGTH_SHORT).show();
                }

            });

        }

        @Override
        public void onClick(View view) {

        }
    }
}





