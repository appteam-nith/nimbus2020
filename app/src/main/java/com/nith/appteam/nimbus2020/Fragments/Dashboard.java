package com.nith.appteam.nimbus2020.Fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.nith.appteam.nimbus2020.R;


public class Dashboard extends Fragment {
    TextView quote1;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_dashboard, container, false);
        quote1 = rootView.findViewById(R.id.quote1);
//        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
//            quote1.setText(Html.fromHtml("<p>HOW YOU <font color = " + R.color.blueLight + ">DOIN'?!</font></p", Html.FROM_HTML_MODE_COMPACT));
//        } else {
//            quote1.setText(Html.fromHtml("<h2>Title</h2><br><p>Description here</p>"));
//        }
        return rootView;
    }
}
