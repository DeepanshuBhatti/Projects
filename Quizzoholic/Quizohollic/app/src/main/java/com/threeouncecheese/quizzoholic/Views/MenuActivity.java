package com.threeouncecheese.quizzoholic.Views;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;

import com.threeouncecheese.quizzoholic.R;

public class MenuActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_menu);
    }

    public void ExitApp(View view)
    {
        this.finish();
    }

    public void OptionsClick(View view)
    {
        //Toast.makeText(this, "Options Activity Will be called from Here", Toast.LENGTH_LONG).show();
        Intent intentOptions = new Intent(MenuActivity.this, OptionsActivity.class);
        startActivity(intentOptions);
        finish();
    }

    public void StartGameClick(View view)
    {
        //Toast.makeText(this, "Game will start from here", Toast.LENGTH_LONG).show();
        Intent i = new Intent(MenuActivity.this, UserNameActivity.class);
        startActivity(i);
        finish();
    }

    public void ScoreCardClick(View view)
    {
        //Toast.makeText(this, "Scorecard will be displayed here", Toast.LENGTH_LONG).show();

        Intent i = new Intent(MenuActivity.this, ScoreboardActivity.class);
        startActivity(i);
        finish();
    }
}
