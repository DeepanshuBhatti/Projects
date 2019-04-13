package com.threeouncecheese.quizzoholic.Views;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.EditText;

import com.threeouncecheese.quizzoholic.R;

public class UserNameActivity extends AppCompatActivity {
    private EditText _usernameEditText;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_user_name);

        _usernameEditText = findViewById(R.id.username_edit);
    }

    public void StartGame(View view)
    {
        String _username = "Unknown";
        if(!_usernameEditText.getText().toString().equals(""))
        {
            _username = _usernameEditText.getText().toString();
        }

        Intent intent = new Intent(UserNameActivity.this, QuizActivity.class);
        intent.putExtra("Username", _username);
        startActivity(intent);
        finish();
    }

    public void BackToMenuClick(View view)
    {
        Intent intent = new Intent(UserNameActivity.this, MenuActivity.class);
        startActivity(intent);
        finish();
    }
}
