package com.nith.appteam.nimbus2020.Fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.nith.appteam.nimbus2020.Adapters.OurTeamAdapter;
import com.nith.appteam.nimbus2020.Models.TeamMember;
import com.nith.appteam.nimbus2020.R;

import java.util.ArrayList;
import java.util.List;

public class OurTeam extends Fragment {
    RecyclerView recyclerView;
    OurTeamAdapter ourTeamAdapter;
    List<TeamMember> teamList;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_our_team, container, false);
        recyclerView = rootView.findViewById(R.id.our_team_recycler_view);
        teamList = new ArrayList<>();
        TeamMember teamMember = new TeamMember("XYZ", String.valueOf(R.string.defaultImageUrl), "president");
        teamList.add(teamMember);
        ourTeamAdapter = new OurTeamAdapter(teamList, getActivity());
        GridLayoutManager layoutManager = new GridLayoutManager(getActivity(), 2);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(ourTeamAdapter);
        return rootView;
    }
}
