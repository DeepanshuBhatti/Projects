package com.threeouncecheese.quizzoholic.Views;

import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.os.Vibrator;
import android.widget.CompoundButton;
import android.widget.Switch;

import com.threeouncecheese.quizzoholic.Helpers.SharedPreferencesHelper;
import com.threeouncecheese.quizzoholic.R;
import com.threeouncecheese.quizzoholic.ViewModels.OptionsViewModel;

public class OptionsActivity extends AppCompatActivity {

    private OptionsViewModel optionsViewModel;
    private SharedPreferencesHelper sharedPreferencesHelper = SharedPreferencesHelper.GetInstance();

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_options);

        Initialize();

    }

    private void Initialize()
    {
        Switch switchVibrate = (Switch)findViewById(R.id.Options_Vibrate_Switch);
        boolean IsVibrateOn = sharedPreferencesHelper.GetBooleanFromSharedPref(switchVibrate.getContext(),"Vibrate", false);
        switchVibrate.setChecked(IsVibrateOn);

        Switch switchSound = (Switch)findViewById(R.id.Options_Sound_Switch);
        boolean IsSoundOn = sharedPreferencesHelper.GetBooleanFromSharedPref(switchVibrate.getContext(),"Sound", false);
        switchSound.setChecked(IsSoundOn);

        /*switchVibrate.setTrackDrawable(optionsViewModel.modifySwitchDrawable(switchVibrate.getContext(),
                switchVibrate.getTrackDrawable(), R.color.Chill, false, false));*/
        /*switchVibrate.setTrackDrawable(optionsViewModel.modifySwitchDrawable(switchVibrate.getContext(),
                switchVibrate.getTrackDrawable(), R.color.Black, false, true));*/

        //switchVibrate.setThumbDrawable(optionsViewModel.modifySwitchDrawable(switchVibrate.getContext(),
        //      switchVibrate.getTrackDrawable(), R.color.Black, true, true));

        switchVibrate.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener()
        {
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked)
            {
                Switch switchView = (Switch)buttonView;
                switchView.setTrackDrawable(optionsViewModel.modifySwitchDrawable(switchView.getContext(),
                        switchView.getTrackDrawable(), R.color.BondiBlue, false, true));

                /*switchView.setThumbDrawable(optionsViewModel.modifySwitchDrawable(switchView.getContext(),
                        switchView.getTrackDrawable(), R.color.Black, true, isChecked));*/
                sharedPreferencesHelper.WriteBooleanToSharedPref(switchView.getContext(), "Vibrate", isChecked);
            }
        });

        switchSound.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener()
        {
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked)
            {
                Switch switchView = (Switch)buttonView;
                /*switchView.setTrackDrawable(optionsViewModel.modifySwitchDrawable(switchView.getContext(),
                        switchView.getTrackDrawable(), R.color.BondiBlue, false, true));*/

                /*switchView.setThumbDrawable(optionsViewModel.modifySwitchDrawable(switchView.getContext(),
                        switchView.getTrackDrawable(), R.color.Black, true, isChecked));*/
                sharedPreferencesHelper.WriteBooleanToSharedPref(switchView.getContext(), "Sound", isChecked);
            }
        });
    }

    public void BuyPowers_Clicked(View view)
    {
        Intent intentOptions = new Intent(OptionsActivity.this, BuyPowersActivity.class);
        startActivity(intentOptions);
        finish();
    }

    public void HowToPlay_Clicked(View view)
    {
        Intent i = new Intent(OptionsActivity.this, OptionsSubActivity.class);
        i.putExtra("SOURCE", "HowToPlay");
        startActivity(i);
        finish();
    }

    public void FAQ_Clicked(View view)
    {
        Intent i = new Intent(OptionsActivity.this, OptionsSubActivity.class);
        i.putExtra("SOURCE", "FAQ");
        startActivity(i);
        finish();
    }

    public void LegalPrivacyStatement_Clicked(View view)
    {
        Intent i = new Intent(OptionsActivity.this, OptionsSubActivity.class);
        i.putExtra("SOURCE", "LegalPrivacyStatement");
        startActivity(i);
        finish();
    }

    public void RateUs_Clicked(View view)
    {
/*        Intent i = new Intent(OptionsActivity.this, OptionsSubActivity.class);
        startActivity(i);
        finish();*/
    }

    public void BackToMenuClick(View view)
    {
        Intent intent = new Intent(OptionsActivity.this, MenuActivity.class);
        startActivity(intent);
        finish();
    }

    @Override
    public void onBackPressed()
    {
        Intent intent = new Intent(OptionsActivity.this, MenuActivity.class);
        startActivity(intent);
        finish();
    }
}
