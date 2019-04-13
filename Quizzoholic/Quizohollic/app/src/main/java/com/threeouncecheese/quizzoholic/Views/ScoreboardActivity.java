package com.threeouncecheese.quizzoholic.Views;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;

import com.threeouncecheese.quizzoholic.R;

public class ScoreboardActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);

        setContentView(R.layout.activity_scoreboard);
    }

    public void BackToMenuClick(View view)
    {
        Intent intent = new Intent(ScoreboardActivity.this, MenuActivity.class);
        startActivity(intent);
        finish();
    }
}
