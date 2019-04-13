package com.threeouncecheese.quizzoholic.Views;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.threeouncecheese.quizzoholic.R;

public class OptionsSubFragment extends Fragment {

    private String _Source;
    public void setData(String strMessage)
    {
        _Source = strMessage;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_sub_options, container, false);
        SetDataToView(view);
        return view;
    }

    private void SetDataToView(View view)
    {
        TextView header = view.findViewById(R.id.options_frament_header);
        TextView content = view.findViewById(R.id.options_frament_content);
        switch(_Source)
        {
            case "HowToPlay":
                header.setText(R.string.button_text_how_to_play);
                content.setText(R.string.HowToPlayContent);
                break;

            case "FAQ":
                header.setText(R.string.button_text_faq);
                content.setText(R.string.FAQ_Content);
                break;

            case "LegalPrivacyStatement":
                header.setText(R.string.button_text_legal_privacy_statement);
                content.setText(R.string.LegalPrivacyStatement_Content);
                break;

            default:
                break;
        }
    }
}
