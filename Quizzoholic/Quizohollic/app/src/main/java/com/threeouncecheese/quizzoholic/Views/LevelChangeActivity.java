package com.threeouncecheese.quizzoholic.Views;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.TextView;

import com.threeouncecheese.quizzoholic.R;

import java.util.ArrayList;

public class LevelChangeActivity extends AppCompatActivity {

    private Integer _score;
    private String _username;
    private TextView _scoreMsgTextView;
    private ArrayList<Integer> _powerCount;
    private TextView _powerCountFiftyFifty, _powerCountAudiencePoll, _powerCountDoubleAnswer, _powerCountFlipQuestion;

    private void GetIntentValues()
    {
        Intent intent = getIntent();
        _score = intent.getIntExtra("Score", 0);
        _username = intent.getStringExtra("Username");
        _powerCount = intent.getIntegerArrayListExtra("PowerCount");
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_level_change);

        GetIntentValues();
        _scoreMsgTextView = findViewById(R.id.score_msg);
        _powerCountAudiencePoll = findViewById(R.id.power_count_audience_poll);
        _powerCountFiftyFifty = findViewById(R.id.power_count_fifty_fifty);
        _powerCountDoubleAnswer = findViewById(R.id.power_count_double_answer);
        _powerCountFlipQuestion = findViewById(R.id.power_count_flip_question);

        String scoreMsgString = "Good Job " + _username + "!\nYour Score is " + _score;

        _scoreMsgTextView.setText(scoreMsgString);
        _powerCountFlipQuestion.setText(_powerCount.get(3).toString());
        _powerCountDoubleAnswer.setText(_powerCount.get(2).toString());
        _powerCountFiftyFifty.setText(_powerCount.get(1).toString());
        _powerCountAudiencePoll.setText(_powerCount.get(0).toString());

    }

    public void BackToMenuClick(View view)
    {
        Intent intent = new Intent(LevelChangeActivity.this, MenuActivity.class);
        startActivity(intent);
        finish();
    }

    public void BackToQuiz(View view)
    {
        Intent intent = new Intent(LevelChangeActivity.this, QuizActivity.class);
        intent.putExtra("Score", _score);
        intent.putExtra("Username", _username);
        startActivity(intent);
        finish();
    }

    public void CallBuyPowers(View view)
    {
        Intent intent = new Intent(LevelChangeActivity.this, BuyPowersActivity.class);
        startActivity(intent);
        finish();
    }
}
