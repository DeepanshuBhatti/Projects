package com.threeouncecheese.quizzoholic.Views;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.threeouncecheese.quizzoholic.R;

import java.util.ArrayList;
import java.util.List;


public class AudiencePollFragment extends Fragment {

    private ImageView _bar1, _bar2, _bar3, _bar4;
    private TextView _text1, _text2, _text3, _text4;
    private TextView _percentage1, _percentage2, _percentage3, _percentage4;
    private CountDownTimer _timer;
    private ProgressBar _timerProgressBar;

    public AudiencePollFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view  = inflater.inflate(R.layout.fragment_audience_poll, container, false);

        InitializeUserControls(view);

        _text1.setText("A");
        _text2.setText("B");
        _text3.setText("C");
        _text4.setText("D");

        List<Integer> audiencePoll = new ArrayList<>();
        if (getArguments() != null) {
            audiencePoll = getArguments().getIntegerArrayList("params");
        }

        SetPercentageText(audiencePoll);
        SetBarForAudiencePoll(audiencePoll);

        InitializeTimer();

        return view;
    }

    private void InitializeUserControls(View view)
    {
        _bar1 = view.findViewById(R.id.bar_option1);
        _bar2 = view.findViewById(R.id.bar_option2);
        _bar3 = view.findViewById(R.id.bar_option3);
        _bar4 = view.findViewById(R.id.bar_option4);

        _percentage1 = view.findViewById(R.id.percentage_option1);
        _percentage2 = view.findViewById(R.id.percentage_option2);
        _percentage3 = view.findViewById(R.id.percentage_option3);
        _percentage4 = view.findViewById(R.id.percentage_option4);

        _text1 = view.findViewById(R.id.text_option1);
        _text2 = view.findViewById(R.id.text_option2);
        _text3 = view.findViewById(R.id.text_option3);
        _text4 = view.findViewById(R.id.text_option4);

        _timerProgressBar = view.findViewById(R.id.progress_bar_time);
    }

    private void SetPercentageText(List<Integer> audiencePoll)
    {
        _percentage1.setText(String.format("%s%%", audiencePoll.get(0).toString()));
        _percentage2.setText(String.format("%s%%", audiencePoll.get(1).toString()));
        _percentage3.setText(String.format("%s%%", audiencePoll.get(2).toString()));
        _percentage4.setText(String.format("%s%%", audiencePoll.get(3).toString()));
    }

    private void SetBarForAudiencePoll(List<Integer> audiencePoll)
    {
        _bar1.requestLayout();
        _bar2.requestLayout();
        _bar3.requestLayout();
        _bar4.requestLayout();

        _bar1.getLayoutParams().height = 2 + 6*audiencePoll.get(0);
        _bar2.getLayoutParams().height = 2 + 6*audiencePoll.get(1);
        _bar3.getLayoutParams().height = 2 + 6*audiencePoll.get(2);
        _bar4.getLayoutParams().height = 2 + 6*audiencePoll.get(3);
    }

    private void stopTimer() {
        if (_timer != null) {
            _timer.cancel();
            _timer = null;
        }
    }

    private void InitializeTimer()
    {
        _timer = new CountDownTimer(10000, 1000) {

            public void onTick(long millisUntilFinished) {
                long secs = millisUntilFinished / 1000;
                _timerProgressBar.setProgress((int)secs);
            }

            public void onFinish() {
                TimesUp();
            }
        };
    }

    public void TimesUp()
    {
        stopTimer();
    }

}
