package com.threeouncecheese.quizzoholic.ViewModels;

import android.annotation.SuppressLint;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

public class QuestionsViewModel {
    private Integer _multiplier;
    private Integer _currentSecondsRemaining;
    private Integer _currentScore;
    private String _username;
    private List<Integer> _powersCount;
    private List<String> _questions;
    private Map<Integer, List<String>> _options;
    private Random _rand;
    private Integer _currentQuestionId;
    private Integer _levelChangeTrigger;

    private String getCorrectAnswer() {
        return _options.get(_currentQuestionId).get(4).toLowerCase();
    }

    private void InitializePowersCountForUser() {
        _powersCount.add(50);
        _powersCount.add(50);
        _powersCount.add(50);
        _powersCount.add(50);
    }

    private void PowerUsed(Integer index) {
        Integer value = _powersCount.get(index);
        value = value - 1;
        _powersCount.set(index, value);
    }

    private Integer GetCorrectAnswerId() {
        String optionA = _options.get(_currentQuestionId).get(0).toLowerCase();
        String optionB = _options.get(_currentQuestionId).get(1).toLowerCase();
        String optionC = _options.get(_currentQuestionId).get(2).toLowerCase();
        String optionD = _options.get(_currentQuestionId).get(3).toLowerCase();
        String correctAnswer = _options.get(_currentQuestionId).get(4).toLowerCase();

        if (optionA.equals(correctAnswer))
            return 0;
        else if (optionB.equals(correctAnswer))
            return 1;
        else if (optionC.equals(correctAnswer))
            return 2;
        else
            return 3;
    }

    private void UpdateLevelChangeTrigger()
    {
        _levelChangeTrigger = 2;
    }


    private void InitializeQuestions() {
        _questions.add("Which among these is most responsible, strategic and energetic person?");
        _questions.add("Which is the third planet in the Solar System?");
        _questions.add("Who was the Highest paid actor for the year 2017?");
        _questions.add("Who won the FIFA Wrold Cup 2018?");
    }

    private void InitializeOptions() {
        _options.put(0, new ArrayList<String>() {{
            add("Sravani");
            add("Deepanshu");
            add("Hasti");
            add("Kapil");
            add("Deepanshu");
        }});
        _options.put(1, new ArrayList<String>() {{
            add("Venus");
            add("Mars");
            add("Earth");
            add("Mercury");
            add("Earth");
        }});
        _options.put(2, new ArrayList<String>() {{
            add("Dwyane Johnson");
            add("Vin Diesel");
            add("Tom Cruise");
            add("Robert Downey Jr.");
            add("Dwyane Johnson");
        }});
        _options.put(3, new ArrayList<String>() {{
            add("England");
            add("Coratia");
            add("Germany");
            add("France");
            add("France");
        }});
    }

    public List<Boolean> PowerUsedFiftyFifty() {
        PowerUsed(0);
        List<Boolean> optionsToDelete = new ArrayList<>();
        optionsToDelete.add(true);
        optionsToDelete.add(true);
        optionsToDelete.add(true);
        optionsToDelete.add(true);

        int correctAnswerIndex = GetCorrectAnswerId();
        int valuesChanged = 0;
        while (valuesChanged < 2) {
            int optionNumber = _rand.nextInt(4);
            if (optionsToDelete.get(optionNumber)) {
                if (optionNumber != correctAnswerIndex) {
                    optionsToDelete.set(optionNumber, false);
                    valuesChanged = valuesChanged + 1;
                }
            }
        }
        return optionsToDelete;
    }

    public List<Integer> PowerUsedAudiencePoll() {
        PowerUsed(1);

        List<Integer> audienceData = new ArrayList<>();
        audienceData.add(0);
        audienceData.add(0);
        audienceData.add(0);
        audienceData.add(0);

        int correctAnswerIndex = GetCorrectAnswerId();

        int maxPercentage = _rand.nextInt(52) + 45;

        int total = maxPercentage;
        audienceData.set(correctAnswerIndex, maxPercentage);
        int valuesChanged = 0;

        while (valuesChanged < 2) {
            int optionNumber = _rand.nextInt(4);
            if (optionNumber != correctAnswerIndex) {
                if (audienceData.get(optionNumber) == 0) {
                    int tempPercentage = _rand.nextInt(100 - total);
                    valuesChanged = valuesChanged + 1;
                    audienceData.set(optionNumber, tempPercentage);
                    total += tempPercentage;
                }
            }
        }

        for (int i = 0; i < 4; i++) {
            if (audienceData.get(i) == 0) {
                audienceData.set(i, 100 - total);
            }
        }
        return audienceData;
    }

    public void PowerUsedDoubleAnswer() {
        PowerUsed(2);
    }

    public void PowerUsedFlipQuestion() {
        PowerUsed(3);
    }


    public Integer get_currentSecondsRemaining() {
        return _currentSecondsRemaining;
    }

    public void set_currentSecondsRemaining(Integer _currentSecondsRemaining) {
        this._currentSecondsRemaining = _currentSecondsRemaining;
    }

    public Integer get_currentScore() {
        return _currentScore;
    }

    public void set_currentScore(int score) {
        _currentScore = score;
    }

    public String get_username() {
        return _username;
    }

    public Boolean get_LevelUp()
    {
        if(_levelChangeTrigger > 0)
            return false;
        else
            UpdateLevelChangeTrigger();
        return true;
    }

    public void set_username(String _username) {
        this._username = _username;
    }

    public Integer GetFiftyFiftyCount() {
        return _powersCount.get(0);
    }

    public Integer GetAudiencePollCount() {
        return _powersCount.get(1);
    }

    public Integer GetDoubleAnswerCount() {
        return _powersCount.get(2);
    }

    public Integer GetFlipQuestionCount() {
        return _powersCount.get(3);
    }



    public void InitializeVariables() {
        _multiplier = 100;
        _currentSecondsRemaining = 0;
        _currentScore = 0;
        _levelChangeTrigger = 3;
        _username = "Unknown";
        _powersCount = new ArrayList<>();
        _questions = new ArrayList<>();
        _options = new HashMap<>();
        _rand = new Random();
    }

    public List<String> GetOptions() {
        return _options.get(_currentQuestionId);
    }

    @SuppressLint("UseSparseArrays")
    public QuestionsViewModel() {
        InitializeVariables();
        InitializeQuestions();
        InitializeOptions();
        InitializePowersCountForUser();
    }

    public boolean IsCorrectAnswer(String answerSelected) {
        if (answerSelected.equals(getCorrectAnswer()))
            return true;
        return false;
    }

    public void UpdateScore() {
        int remainingTime = _currentSecondsRemaining;
        _multiplier = _multiplier + remainingTime;
        _currentScore = _currentScore + _multiplier;
    }

    public String GetNextQuestion() {
        _currentQuestionId = _rand.nextInt(4);
        _levelChangeTrigger--;
        return _questions.get(_currentQuestionId);
    }

}
