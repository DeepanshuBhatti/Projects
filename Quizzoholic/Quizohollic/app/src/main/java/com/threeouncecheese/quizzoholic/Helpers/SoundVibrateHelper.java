package com.threeouncecheese.quizzoholic.Helpers;

import android.content.Context;
import android.os.Build;
import android.os.VibrationEffect;
import android.os.Vibrator;

public class SoundVibrateHelper
{

    private SharedPreferencesHelper _sharedpref = SharedPreferencesHelper.GetInstance();
    private Context _context;

    public SoundVibrateHelper(Context context)
    {
        this._context = context;
    }

    private boolean isVibrateEnabled()
    {
        return _sharedpref.GetBooleanFromSharedPref(_context, "Vibrate", true);
    }

    private boolean isSoundEnabled()
    {
        return _sharedpref.GetBooleanFromSharedPref(_context, "Sound", true);
    }

    public void Vibrate()
    {
        if(isVibrateEnabled())
        {
            Vibrator v = (Vibrator) _context.getSystemService(Context.VIBRATOR_SERVICE);
            // Vibrate for 500 milliseconds
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                v.vibrate(VibrationEffect.createOneShot(200,VibrationEffect.DEFAULT_AMPLITUDE));
            }else{
                //deprecated in API 26
                v.vibrate(200);
            }
        }
    }

    public void PlaySound()
    {
        if(isSoundEnabled())
        {

        }
    }
}
