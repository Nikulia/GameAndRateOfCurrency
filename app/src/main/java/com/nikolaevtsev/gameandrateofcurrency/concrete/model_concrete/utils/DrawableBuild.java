package com.nikolaevtsev.gameandrateofcurrency.concrete.model_concrete.utils;

import android.content.Context;
import android.graphics.drawable.Drawable;

public class DrawableBuild {
    public static Drawable getDrawable (Context context, int drawId) {
        return context.getResources().getDrawable(drawId);
    }
}
