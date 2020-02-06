package com.nith.appteam.nimbus2020.Activities;

import android.app.AlertDialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.nith.appteam.nimbus2020.R;

import org.json.JSONException;
import org.json.JSONObject;

import androidx.appcompat.app.AppCompatActivity;

public class QuizInstructionsActivity extends AppCompatActivity {

    Button playNow;
    Button leaderboard;
    String response;
    String quizId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz_instructions);

        playNow = findViewById(R.id.playNowButton);
        leaderboard = findViewById(R.id.leaderboardButton);

        response = getIntent().getStringExtra("questions");
        quizId = getIntent().getStringExtra("quizId");

        Log.e("response", response);

        playNow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                boolean flag = true;

                try {
                    JSONObject jsonObject = new JSONObject(response);
                    String error = jsonObject.getString("errorCode");

                    if (error.equals("1")) {
                        playNow.setClickable(false);
                        new AlertDialog.Builder(QuizInstructionsActivity.this)
                                .setTitle("Play Later")
                                .setMessage("This is not the right time to play the quiz")
                                .setIcon(android.R.drawable.ic_dialog_alert)
                                .show();
                        flag = false;

                    } else if (error.equals("2")) {
                        playNow.setClickable(false);
                        new AlertDialog.Builder(QuizInstructionsActivity.this)
                                .setTitle("Already played")
                                .setMessage("You can play a quiz only one time")
                                .setIcon(android.R.drawable.ic_dialog_alert)
                                .show();
                        flag = false;

                    } else if (error.equals("4")) {
                        Toast.makeText(QuizInstructionsActivity.this, "No questions available",
                                Toast.LENGTH_SHORT).show();
                        flag = false;
                    }

                } catch (JSONException e) {
                    e.printStackTrace();
                }

                if (flag) {

                    Intent intent = new Intent(QuizInstructionsActivity.this, Quiz.class);
                    intent.putExtra("questions", response);
                    startActivity(intent);
                }


            }
        });

        leaderboard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(QuizInstructionsActivity.this,
                        LeaderBoardActivity.class);
                intent.putExtra("quizId", quizId);
                startActivity(intent);
            }
        });
    }
}
