package com.nith.appteam.nimbus2020.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.nith.appteam.nimbus2020.Models.Id_Value;
import com.nith.appteam.nimbus2020.Models.Schedule;
import com.nith.appteam.nimbus2020.R;

import org.w3c.dom.Text;

import java.util.ArrayList;

public class ScheduleRecyclerAdaptor extends RecyclerView.Adapter<ScheduleRecyclerAdaptor.viewholder> {
    private Context context;
    private ArrayList<Schedule> arrayList;

    public ScheduleRecyclerAdaptor(Context context, ArrayList<Schedule> arrayList) {
        this.context = context;
        this.arrayList = arrayList;
    }

    @NonNull
    @Override
    public viewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        android.view.View v = inflater.inflate(R.layout.schedule_recycler_item, null);
        return new viewholder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull viewholder holder, int position) {
        holder.name.setText(arrayList.get(position).getName());
        holder.Department.setText(arrayList.get(position).getDepartment());
        holder.info.setText(arrayList.get(position).getInfo());
        holder.time.setText(arrayList.get(position).getTime());
        holder.venue.setText(arrayList.get(position).getVenue());
    }

    @Override
    public int getItemCount() {
        return arrayList.size();
    }

    class viewholder extends RecyclerView.ViewHolder {
        TextView name;
        TextView venue;
        TextView Department;
        TextView time;
        TextView info;


        private viewholder(@NonNull android.view.View itemView) {
            super(itemView);
            name = itemView.findViewById(R.id.name);
            venue=itemView.findViewById(R.id.venue);
            Department=itemView.findViewById(R.id.departmentname_recycler);
            time=itemView.findViewById(R.id.time);
            info=itemView.findViewById(R.id.info);
        }
    }
}

