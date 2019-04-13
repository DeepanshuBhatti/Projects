package com.threeouncecheese.quizzoholic.Views;

import android.content.Intent;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.threeouncecheese.quizzoholic.R;

public class OptionsSubActivity extends AppCompatActivity {

    private String strActivitySource;
    private static FragmentManager fragmentManager;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_options_sub);

        Intent intent = getIntent();
        strActivitySource = intent.getStringExtra("SOURCE");

        SetData(strActivitySource);
    }


    private void SetData(String source)
    {
        if(findViewById(R.id.suboptions_frame) == null)
            return;

        fragmentManager = getSupportFragmentManager();

        switch (source)
        {
            case "HowToPlay":
            case "FAQ":
            case "LegalPrivacyStatement":
                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                OptionsSubFragment optionsSubFragment = new OptionsSubFragment();
                optionsSubFragment.setData(source);
                fragmentTransaction.add(R.id.suboptions_frame, optionsSubFragment, null).commit();
                break;
            default:
                break;
        }
    }

    public void CloseClicked(View view)
    {
        Intent intent = new Intent(OptionsSubActivity.this, OptionsActivity.class);
        startActivity(intent);
        finish();
    }
}
