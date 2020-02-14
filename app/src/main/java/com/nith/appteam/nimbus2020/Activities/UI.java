package com.nith.appteam.nimbus2020.Activities;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;

import com.nith.appteam.nimbus2020.Fragments.Dashboard;
import com.nith.appteam.nimbus2020.Fragments.OurTeam;
import com.nith.appteam.nimbus2020.Fragments.Sponsor;
import com.nith.appteam.nimbus2020.R;

public class UI extends AppCompatActivity {
    TextView  dashboardTab, sponsorTab, teamTab;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ui);
        dashboardTab = findViewById(R.id.dashboard_tab);
        sponsorTab = findViewById(R.id.sponsor_tab);
        teamTab = findViewById(R.id.team_tab);
        dashboardTab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Dashboard dashboard = new Dashboard();
                FragmentManager fm = getSupportFragmentManager();
                fm.beginTransaction()
                        .replace(R.id.fragment_holder, dashboard)
                        .commit();
            }
        });

        sponsorTab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Sponsor sponsorFragment = new Sponsor();
                FragmentManager fm = getSupportFragmentManager();
                fm.beginTransaction()
                        .replace(R.id.fragment_holder, sponsorFragment)
                        .commit();
            }
        });
        teamTab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                OurTeam ourTeam = new OurTeam();
                FragmentManager fm = getSupportFragmentManager();
                fm.beginTransaction()
                        .replace(R.id.fragment_holder, ourTeam)
                        .commit();
            }
        });

        dashboardTab.performClick();
    }
}
