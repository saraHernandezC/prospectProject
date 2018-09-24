package com.example.sarah.prospectsproject.helper;

import android.view.View;
import android.widget.ImageView;

import com.example.sarah.prospectsproject.R;

/**
 * Created by sarah on 12/09/2018.
 */

public class GetImageView {

    public int getImageStateView (int prospectState){
        int getIconResource;
        switch (prospectState){
            case Constants.ZERO: getIconResource = R.drawable.time;
            break;
            case Constants.ONE: getIconResource = R.drawable.success;
            break;
            case Constants.TWO: getIconResource = R.drawable.accepted;
            break;
            case Constants.THREE: getIconResource = R.drawable.cancel;
            break;
            case Constants.FOUR: getIconResource = R.drawable.disabled;
            break;

            default: getIconResource = R.drawable.ic_help_black_24dp;
                break;
        }
        return getIconResource;
    }

    public String getStatusText(int buttonClicked){
    String textToTextView;
        switch (buttonClicked){
            case 0 : textToTextView =  Constants.TEXT_PENDING;
            break;
            case 1: textToTextView = Constants.TEXT_SUCCESS;
            break;
            case 2: textToTextView = Constants.TEXT_ACCEPTED;
            break;
            case 3: textToTextView = Constants.TEXT_CANCEL;
            break;
            case 4: textToTextView = Constants.TEXT_DISABLED;
            break;
            default: textToTextView = Constants.EMPTY_STRING;
        }

        return textToTextView;
    }
}
