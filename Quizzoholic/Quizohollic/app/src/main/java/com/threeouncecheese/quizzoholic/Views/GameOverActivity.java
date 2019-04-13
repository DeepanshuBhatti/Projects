package com.threeouncecheese.quizzoholic.Views;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.TextView;

import com.threeouncecheese.quizzoholic.R;

public class GameOverActivity extends AppCompatActivity {

    private TextView finalMessage;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);

        setContentView(R.layout.activity_game_over);

        finalMessage = findViewById(R.id.score_text);

        Intent intent = getIntent();
        int score = intent.getIntExtra("Score", 0);
        String username = intent.getStringExtra("Username");
        String message = intent.getStringExtra("Message");

        String messageText = message + "\nCongratulations " + username + "\nYou Scored " + String.format("%d", score);

        finalMessage.setText(messageText);
    }

    public void ExitApp(View view)
    {
        this.finish();
    }

    public void RestartGame(View view)
    {
        Intent i = new Intent(GameOverActivity.this, QuizActivity.class);
        startActivity(i);
        finish();
    }
    public void BackToMenuClick(View view)
    {
        Intent intent = new Intent(GameOverActivity.this, MenuActivity.class);
        startActivity(intent);
        finish();
    }
}
