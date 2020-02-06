package com.nith.appteam.nimbus2020.Activities;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.nith.appteam.nimbus2020.R;

import androidx.appcompat.app.AppCompatActivity;

public class QuizScoreActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz_score);

        int score = getIntent().getIntExtra("score", 0);

        TextView sc = findViewById(R.id.score_obtained);
        sc.setText(score + "");

        findViewById(R.id.quiz_home_link).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(QuizScoreActivity.this, MainActivity.class);
                startActivity(intent);
            }
        });

    }
}
