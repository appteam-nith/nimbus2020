package com.nith.appteam.nimbus2020.Activities;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toolbar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.nith.appteam.nimbus2020.Adapters.contributorAdapter;
import com.nith.appteam.nimbus2020.Models.contributorsItem;
import com.nith.appteam.nimbus2020.R;

import java.util.ArrayList;

class contributorsActivity extends AppCompatActivity {

    RecyclerView rvContributors;
    contributorAdapter ContributorAdapter;
    ArrayList<contributorsItem> contributorsItems;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contributors);

        TextView back;
        back = (TextView) findViewById(R.id.back);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
overridePendingTransition(R.anim.ease_in, R.anim.ease_out);
            }
        });

//        YoYo.with(Techniques.Bounce)
//                .duration(5000)
//                .repeat(0)
//                .playOn(findViewById(R.id.backdrop));
        String BASE_URL = "https://github.com/";
        rvContributors = (RecyclerView) findViewById(R.id.contributors_view);
        contributorsItems = new ArrayList<>();

        //contributorsItems.add(new contributorsItem("", BASE_URL + ".png", BASE_URL + ""));

        //pass out seniors
        contributorsItems.add(new contributorsItem("Ashish Gurjar", BASE_URL + "akgurjar.png",
                BASE_URL + "akgurjar"));
        contributorsItems.add(new contributorsItem("Sahil Ramola", BASE_URL + "RamolaWeb.png",
                BASE_URL + "RamolaWeb"));
        contributorsItems.add(new contributorsItem("Sukhbir Singh", BASE_URL + "sukhbir-singh.png",
                BASE_URL + "sukhbir-singh"));
        contributorsItems.add(new contributorsItem("Jalaz Kumar", BASE_URL + "jaykay12.png",
                BASE_URL + "jaykay12"));
        contributorsItems.add(new contributorsItem("Narendra Dodwaria", BASE_URL + "narendra36.png",
                BASE_URL + "narendra36"));
        contributorsItems.add(new contributorsItem("Goutham Reddy ", BASE_URL + "zeus512.png",
                BASE_URL + "zeus512"));
        contributorsItems.add(new contributorsItem("Vijaya Laxmi", BASE_URL + "vijaya22.png",
                BASE_URL + "vijaya22"));
        contributorsItems.add(new contributorsItem("Aditya Arora", BASE_URL + "adi23arora.png",
                BASE_URL + "adi23arora"));
        contributorsItems.add(new contributorsItem("B Jatin Rao", BASE_URL + "Jatin0312.png",
                BASE_URL + "Jatin0312"));
        contributorsItems.add(new contributorsItem("Nitin Sharma", BASE_URL + "iamNitin16.png",
                BASE_URL + "iamNitin16"));
        contributorsItems.add(new contributorsItem("Suraj Negi", BASE_URL + "Akatsuki06.png",
                BASE_URL + "Akatsuki06"));
        contributorsItems.add(new contributorsItem("Parvesh Dhull", BASE_URL + "Parveshdhull.png",
                BASE_URL + "Parveshdhull"));

        //final Year
        contributorsItems.add(new contributorsItem("Bharat Shah", BASE_URL + "bharatshah1498.png",
                BASE_URL + "bharatshah1498"));
        contributorsItems.add(new contributorsItem("Kartik Saxena", BASE_URL + "SaxenaKartik.png",
                BASE_URL + "SaxenaKartik"));
        contributorsItems.add(
                new contributorsItem("Muskan Jhunjhunwalla", BASE_URL + "musukeshu.png",
                        BASE_URL + "musukeshu"));
        contributorsItems.add(
                new contributorsItem("Anubhaw Bhalotia", BASE_URL + "anubhawbhalotia.png",
                        BASE_URL + "anubhawbhalotia"));
        contributorsItems.add(new contributorsItem("Kaushal Kishore", BASE_URL + "kaushal16124.png",
                BASE_URL + "kaushal16124"));

        //3rd Year
        contributorsItems.add(new contributorsItem("Utkarsh Singh", BASE_URL + "utkarshsingh99.png",
                BASE_URL + "utkarshsingh99"));
        contributorsItems.add(new contributorsItem("Vishal Parmar", BASE_URL + "Vishal17599.png",
                BASE_URL + "Vishal17599"));
        contributorsItems.add(new contributorsItem("Abhinav Lamba", BASE_URL + "Abhinavlamba.png",
                BASE_URL + "Abhinavlamba"));
        contributorsItems.add(new contributorsItem("Divyanshu", BASE_URL + "dextroxd.png",
                BASE_URL + "dextroxd"));
        contributorsItems.add(new contributorsItem("Nishant Singh Hada", BASE_URL + "thisisnsh.png",
                BASE_URL + "thisisnsh"));
        contributorsItems.add(
                new contributorsItem("Aakanksha Tewari", BASE_URL + "aakankshaa23.png",
                        BASE_URL + "aakankshaa23"));
        contributorsItems.add(
                new contributorsItem("Abhiraj Singh Rathore", BASE_URL + "AbhirajSinghRathore.png",
                        BASE_URL + "AbhirajSingh99"));
        contributorsItems.add(new contributorsItem("Alisha Mehta", BASE_URL + "Alisha1116.png",
                BASE_URL + "Alisha1116"));
        contributorsItems.add(
                new contributorsItem("Anshu Akansha", BASE_URL + "AnshuAkansha52227.png",
                        BASE_URL + "AnshuAkansha"));
        contributorsItems.add(new contributorsItem("Daniyaal Khan", BASE_URL + "drtweety.png",
                BASE_URL + "drtweety"));
        contributorsItems.add(new contributorsItem("Tanvi Mahajan", BASE_URL + "tanvi003.png",
                BASE_URL + "tanvi003"));


        //2nd year
        contributorsItems.add(new contributorsItem("Sarthak Gupta", BASE_URL + "SarthakGupta3.png",
                BASE_URL + "SarthakGupta3"));
        contributorsItems.add(new contributorsItem("Shweta Gurnani", BASE_URL + "shwetagurnani.png",
                BASE_URL + "shwetagurnani"));
        contributorsItems.add(
                new contributorsItem("Shubham Kumar", BASE_URL + "SkroX.png", BASE_URL + "SkroX"));
        contributorsItems.add(
                new contributorsItem("Chandan Sitlani", BASE_URL + "ChandanSitlani.png",
                        BASE_URL + "ChandanSitlani"));
        contributorsItems.add(new contributorsItem("Pawan Harariya", BASE_URL + "pawanharariya.png",
                BASE_URL + "pawanharariya"));
        contributorsItems.add(new contributorsItem("Ayush Kaushal", BASE_URL + "Yashu017.png",
                BASE_URL + "Yashu017"));
        contributorsItems.add(new contributorsItem("Ananya Sharma", BASE_URL + "Ananya-Sharma.png",
                BASE_URL + "Ananya-Sharma"));
        contributorsItems.add(new contributorsItem("Amishi Bansal", BASE_URL + "AmishiBansal.png",
                BASE_URL + "AmishiBansal"));
        contributorsItems.add(
                new contributorsItem("Moulik Bhardwaj", BASE_URL + "moulikbhardwaj.png",
                        BASE_URL + "moulikbhardwaj"));
        contributorsItems.add(new contributorsItem("Rupali Mehra", BASE_URL + "rupalimehra.png",
                BASE_URL + "rupalimehra"));
        contributorsItems.add(
                new contributorsItem("Pranjal Dureja", BASE_URL + "pranjaldureja0002.png",
                        BASE_URL + "pranjaldureja0002"));
        contributorsItems.add(new contributorsItem("Shreya Agarwal", BASE_URL + "shreyaag770.png",
                BASE_URL + "shreyaag770"));

        //1st year
        contributorsItems.add(
                new contributorsItem("Anshit", BASE_URL + "Anshit01.png", BASE_URL + "Anshit01"));
        contributorsItems.add(new contributorsItem("Mrigank Anand", BASE_URL + "spiderpxm.png",
                BASE_URL + "spiderxm"));
        contributorsItems.add(new contributorsItem("Gautam Verma", BASE_URL + "hey1729gautam.png",
                BASE_URL + "hey1729gautam"));
        contributorsItems.add(new contributorsItem("Kashika", BASE_URL + "Kashika1020.png",
                BASE_URL + "Kashika1020"));
        contributorsItems.add(new contributorsItem("Khyati Saini", BASE_URL + "KhyatiSaini.png",
                BASE_URL + "KhyatiSaini"));

        ContributorAdapter = new contributorAdapter(contributorsItems, contributorsActivity.this);
        rvContributors.setAdapter(ContributorAdapter);

//        tbContributers = (Toolbar) findViewById(R.id.contributors_toolbar);
//        tbContributers.setTitle("Contributors");
//        setSupportActionBar(tbContributers);
//        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        rvContributors.setLayoutManager(new GridLayoutManager(this, 2));
    }

    private void setSupportActionBar(Toolbar toolbar) {
    }

    public View findViewById(int contributors_toolbar) {
        return null;
    }

}
