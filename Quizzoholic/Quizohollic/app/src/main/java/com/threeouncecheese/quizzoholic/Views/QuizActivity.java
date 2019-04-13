package com.threeouncecheese.quizzoholic.Views;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.CountDownTimer;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.threeouncecheese.quizzoholic.Helpers.SoundVibrateHelper;
import com.threeouncecheese.quizzoholic.R;
import com.threeouncecheese.quizzoholic.ViewModels.QuestionsViewModel;

import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;


public class QuizActivity extends AppCompatActivity {

    // Private Variables
    private Button _optionOneButton, _optionTwoButton, _optionThreeButton, _optionFourButton;
    private ImageView _powerFiftyFifty, _powerAudiencePoll, _powerFlipTheQuestion, _powerDoubleAnswer;
    private TextView _scoreText, _secondsText, _questionText;
    private TextView _powerCountFiftyFifty, _powerCountAudiencePoll, _powerCountDoubleAnswer, _powerCountFlipQuestion;
    private ProgressBar _circularTimerBar;
    private CountDownTimer _timer;
    private SoundVibrateHelper soundVibrateHelper;
    private QuestionsViewModel _questionsViewModel;
    private Boolean _doubleAnswerInUse;
    private FrameLayout _audiencePollFrame;
    private Integer _score;
    private String _username;

    private Fragment fragment;

    public static FragmentManager fragmentManager;

    // Private Methods
    private void TimesUp() {
        GameOver("Times Up");
    }

    private void CallSleep()
    {
        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    private void CheckAnswer(Button selectedOption)
    {
        String optionString = selectedOption.getText().toString().toLowerCase();
        CallSleep();
        if (_questionsViewModel.IsCorrectAnswer(optionString)) {
            selectedOption.setActivated(true);
            CallSleep();
            selectedOption.setActivated(false);
            IncrementScore();
            GetNextQuestion();
        } else {
            selectedOption.setActivated(true);
            CallSleep();
            selectedOption.setActivated(false);
            if(_doubleAnswerInUse)
            {
                selectedOption.setText("");
                selectedOption.setEnabled(false);
                _doubleAnswerInUse = false;
                return;
            }
            GameOver("Wrong Answer");
        }
    }

    private void stopTimer() {
        if (_timer != null) {
            _timer.cancel();
            _timer = null;
        }
    }

    private void GetNextQuestion() {
        EnablePowers();

        if(_questionsViewModel.get_LevelUp())
        {
            CallLevelUp();
            return;
        }

        _questionText.setText(_questionsViewModel.GetNextQuestion());
        List<String> tempOptions = _questionsViewModel.GetOptions();

        _optionOneButton.setText(tempOptions.get(0));
        _optionTwoButton.setText(tempOptions.get(1));
        _optionThreeButton.setText(tempOptions.get(2));
        _optionFourButton.setText(tempOptions.get(3));

        EnableDisableOptions(true);
        _timer.start();
    }

    private void IncrementScore()
    {
        _questionsViewModel.UpdateScore();
        String scoreToDisplay = _questionsViewModel.get_username() + "\nYour Score\n" + _questionsViewModel.get_currentScore();
        _scoreText.setText(scoreToDisplay);
    }

    private void GameOver(String gameOverMessage) {
        stopTimer();
        Intent intent = new Intent(QuizActivity.this, GameOverActivity.class);
        intent.putExtra("Score", _questionsViewModel.get_currentScore());
        intent.putExtra("Username", _questionsViewModel.get_username());
        intent.putExtra("Message", gameOverMessage);
        startActivity(intent);
        finish();
    }

    private void CallLevelUp() {
        stopTimer();

        ArrayList<Integer> powerCount = new ArrayList<>();

        powerCount.add(_questionsViewModel.GetFiftyFiftyCount());
        powerCount.add(_questionsViewModel.GetAudiencePollCount());
        powerCount.add(_questionsViewModel.GetDoubleAnswerCount());
        powerCount.add(_questionsViewModel.GetFlipQuestionCount());

        Intent intent = new Intent(QuizActivity.this, LevelChangeActivity.class);
        intent.putExtra("Score", _questionsViewModel.get_currentScore());
        intent.putExtra("Username", _questionsViewModel.get_username());
        intent.putExtra("PowerCount", powerCount);
        startActivity(intent);
        finish();
    }

    private void InitializeUserControls() {
        _optionOneButton = findViewById(R.id.option1);
        _optionTwoButton = findViewById(R.id.option2);
        _optionThreeButton = findViewById(R.id.option3);
        _optionFourButton = findViewById(R.id.option4);

        _powerFiftyFifty = findViewById(R.id.power_fifty_fifty);
        _powerAudiencePoll = findViewById(R.id.power_audience_poll);
        _powerDoubleAnswer = findViewById(R.id.power_double_answer);
        _powerFlipTheQuestion = findViewById(R.id.power_flip_question);

        _powerCountAudiencePoll = findViewById(R.id.power_count_audience_poll);
        _powerCountFiftyFifty = findViewById(R.id.power_count_fifty_fifty);
        _powerCountDoubleAnswer = findViewById(R.id.power_count_double_answer);
        _powerCountFlipQuestion = findViewById(R.id.power_count_flip_question);

        _scoreText = findViewById(R.id.score);
        _secondsText = findViewById(R.id.seconds);
        _questionText = findViewById(R.id.question_text);
        _circularTimerBar = findViewById(R.id.circular_progressbar);

        _audiencePollFrame = findViewById(R.id.fragment_frame);
        fragment = null;
    }

    private void InitializeTimer()
    {
        _timer = new CountDownTimer(10000, 1000) {

            public void onTick(long millisUntilFinished) {
                long secs = millisUntilFinished / 1000;
                _questionsViewModel.set_currentSecondsRemaining((int)secs);
                _circularTimerBar.setProgress(_questionsViewModel.get_currentSecondsRemaining());
                _secondsText.setText(String.format("%d", _questionsViewModel.get_currentSecondsRemaining()));
            }

            public void onFinish() {
                TimesUp();
            }
        };
    }

    private void GetIntentValues()
    {
        Intent intent = getIntent();
        _score = intent.getIntExtra("Score", 0);
        _username = intent.getStringExtra("Username");
    }

    private void InitializeVariables()
    {
        _doubleAnswerInUse = false;
        GetIntentValues();
        _questionsViewModel.set_username(_username);
        _questionsViewModel.set_currentScore(_score);
        _circularTimerBar.setProgress(100);
        _scoreText.setText(String.format("%s\nYour Score\n%s", _questionsViewModel.get_username(), _score.toString()));
        _secondsText.setText("10");
        InitializeTimer();
        soundVibrateHelper = new SoundVibrateHelper(getApplicationContext());
        DisablePowers();
        GetNextQuestion();
    }

    private void EnableDisableOptions(boolean value) {
        _optionOneButton.setEnabled(value);
        _optionTwoButton.setEnabled(value);
        _optionThreeButton.setEnabled(value);
        _optionFourButton.setEnabled(value);
    }

    private void DisablePowers()
    {
        _powerFiftyFifty.setEnabled(false);
        _powerAudiencePoll.setEnabled(false);
        _powerFlipTheQuestion.setEnabled(false);
        _powerDoubleAnswer.setEnabled(false);
    }

    @SuppressLint("SetTextI18n")
    private void UpdatePowerCount()
    {
        _powerCountFlipQuestion.setText(_questionsViewModel.GetFlipQuestionCount().toString());
        _powerCountDoubleAnswer.setText(_questionsViewModel.GetDoubleAnswerCount().toString());
        _powerCountFiftyFifty.setText(_questionsViewModel.GetFiftyFiftyCount().toString());
        _powerCountAudiencePoll.setText(_questionsViewModel.GetAudiencePollCount().toString());
    }

    private void EnablePowers()
    {
        UpdatePowerCount();

        if(_questionsViewModel.GetFiftyFiftyCount() > 0)
            _powerFiftyFifty.setEnabled(true);
        if(_questionsViewModel.GetAudiencePollCount() > 0)
            _powerAudiencePoll.setEnabled(true);
        if(_questionsViewModel.GetDoubleAnswerCount() > 0)
            _powerFlipTheQuestion.setEnabled(true);
        if(_questionsViewModel.GetFlipQuestionCount() > 0)
            _powerDoubleAnswer.setEnabled(true);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_quiz);

        _questionsViewModel = new QuestionsViewModel();

        InitializeUserControls();
        InitializeVariables();

        if(_audiencePollFrame!=null)
        {
            if(savedInstanceState!=null)
                return;
        }

    }

    private void CallAudiencePollFragment(List<Integer> audienceData)
    {
        _timer.cancel();

        fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        AudiencePollFragment audiencePollFragment = new AudiencePollFragment();

        Bundle bundle = new Bundle();
        bundle.putIntegerArrayList("params", (ArrayList<Integer>) audienceData);
        audiencePollFragment.setArguments(bundle);

        fragmentTransaction.add(R.id.fragment_frame, audiencePollFragment, null).commit();

        _audiencePollFrame.setVisibility(View.VISIBLE);
    }

    private void UseAudiencePoll()
    {
        _powerAudiencePoll.setEnabled(false);
        List<Integer> audienceData = _questionsViewModel.PowerUsedAudiencePoll();

        CallAudiencePollFragment(audienceData);
    }

    private void UseFiftyFifty()
    {
        _powerFiftyFifty.setEnabled(false);
        List<Boolean> updatedOptions =  _questionsViewModel.PowerUsedFiftyFifty();
        if(!updatedOptions.get(0))
        {
            _optionOneButton.setText("");
            _optionOneButton.setEnabled(false);
        }
        if(!updatedOptions.get(1))
        {
            _optionTwoButton.setText("");
            _optionTwoButton.setEnabled(false);
        }
        if(!updatedOptions.get(2))
        {
            _optionThreeButton.setText("");
            _optionThreeButton.setEnabled(false);
        }
        if(!updatedOptions.get(3))
        {
            _optionFourButton.setText("");
            _optionFourButton.setEnabled(false);
        }
    }

    private void UseDoubleAnswer()
    {
        _powerDoubleAnswer.setEnabled(false);
        _questionsViewModel.PowerUsedDoubleAnswer();
        _doubleAnswerInUse = true;
        Toast.makeText(this, "Two Options can be selected", Toast.LENGTH_LONG).show();
    }

    private void UseFlipQuestion()
    {
        _powerFlipTheQuestion.setEnabled(false);
        _questionsViewModel.PowerUsedFlipQuestion();
        GetNextQuestion();
    }

    public void PowerActivated(View view)
    {
        switch (view.getId()) {
            case R.id.power_audience_poll:
                UseAudiencePoll();
                break;
            case R.id.power_fifty_fifty:
                UseFiftyFifty();
                break;
            case R.id.power_double_answer:
                UseDoubleAnswer();
                break;
            case R.id.power_flip_question:
                UseFlipQuestion();
                break;
            default:
                break;
        }
        UpdatePowerCount();
    }

    public void OptionSelected(View view) {
        soundVibrateHelper.Vibrate();
        //soundVibrateHelper.PlaySound();
        if(!_doubleAnswerInUse) {
            EnableDisableOptions(false);
            _timer.cancel();
            EnableDisableOptions(false);
        }
        DisablePowers();
        switch (view.getId()) {
            case R.id.option1:
                CheckAnswer(_optionOneButton);
                break;
            case R.id.option2:
                CheckAnswer(_optionTwoButton);
                break;
            case R.id.option3:
                CheckAnswer(_optionThreeButton);
                break;
            case R.id.option4:
                CheckAnswer(_optionFourButton);
                break;
            default:
                break;
        }
    }

    public void BackToQuiz(View view)
    {
        _audiencePollFrame.setVisibility(View.INVISIBLE);
        _timer.start();
    }

    @Override
    public void finalize()
    {
        stopTimer();
    }

    @Override
    public void onBackPressed() {

    }

}
