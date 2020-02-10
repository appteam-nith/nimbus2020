package com.nith.appteam.nimbus2020.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.nith.appteam.nimbus2020.Models.Id_Value;
import com.nith.appteam.nimbus2020.R;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class QuizRecyclerAdapter extends RecyclerView.Adapter<QuizRecyclerAdapter.viewholder> {
    private Context context;
    private ArrayList<Id_Value> arrayList;

    public QuizRecyclerAdapter(Context context, ArrayList<Id_Value> arrayList) {
        this.context = context;
        this.arrayList = arrayList;
    }

    @NonNull
    @Override
    public viewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View v = inflater.inflate(R.layout.quiz_recycler_item, parent, false);
        return new viewholder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull viewholder holder, int position) {
        holder.quizname.setText(arrayList.get(position).getValue());
        Picasso.with(context).load(arrayList.get(position).getImageUrl()).resize(80, 80).into(holder.mImageView);
    }

    @Override
    public int getItemCount() {
        return arrayList.size();
    }

    class viewholder extends RecyclerView.ViewHolder {
        TextView quizname;
        ImageView mImageView;

        private viewholder(@NonNull View itemView) {
            super(itemView);
            quizname = itemView.findViewById(R.id.quizdepartmentname);
            mImageView = itemView.findViewById(R.id.userImageView);
            mImageView.setClipToOutline(true);
        }
    }
}
