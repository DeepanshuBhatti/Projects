package com.threeouncecheese.quizzoholic.ViewModels;

import android.content.Context;
import android.content.res.ColorStateList;
import android.graphics.PorterDuff;
import android.graphics.drawable.Drawable;
import android.support.annotation.CheckResult;
import android.support.annotation.ColorInt;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
import android.support.v4.graphics.drawable.DrawableCompat;
import android.widget.CompoundButton;

import com.threeouncecheese.quizzoholic.R;

public class OptionsViewModel {

    public OptionsViewModel()
    {

    }

    public static Drawable createTintedDrawable(@Nullable Drawable drawable, @ColorInt int color) {
        if (drawable == null) return null;
        drawable = DrawableCompat.wrap(drawable.mutate());
        DrawableCompat.setTintMode(drawable, PorterDuff.Mode.SRC_IN);
        DrawableCompat.setTint(drawable, color);
        return drawable;
    }

    public static Drawable modifySwitchDrawable(@NonNull Context context, @NonNull Drawable from, @ColorInt int tint, boolean thumb, boolean isChecked) {

        int disabled;
        int normal;

        if(thumb) {
            disabled = ContextCompat.getColor(context, isChecked? R.color.Green : R.color.Black);
            normal = ContextCompat.getColor(context, isChecked? R.color.Green : R.color.Black);
        }
        else
        {
            disabled = ContextCompat.getColor(context, isChecked? R.color.Green : R.color.Black );
            normal = ContextCompat.getColor(context, isChecked? R.color.Green : R.color.Black );
        }

        final ColorStateList sl = new ColorStateList(
                new int[][]{
                        new int[]{-android.R.attr.state_enabled},
                        new int[]{android.R.attr.state_enabled, -android.R.attr.state_activated, -android.R.attr.state_checked},
                        new int[]{android.R.attr.state_enabled, android.R.attr.state_activated},
                        new int[]{android.R.attr.state_enabled, android.R.attr.state_checked}
                },
                new int[]{
                        disabled,
                        normal,
                        tint,
                        tint
                }
        );
        return createTintedDrawable(from, sl);
    }

    @CheckResult
    @Nullable
    public static Drawable createTintedDrawable(@Nullable Drawable drawable, @NonNull ColorStateList sl) {
        if (drawable == null) return null;
        drawable = DrawableCompat.wrap(drawable.mutate());
        DrawableCompat.setTintList(drawable, sl);
        return drawable;
    }
}
