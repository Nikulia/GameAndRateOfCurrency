package com.nikolaevtsev.gameandrateofcurrency.concrete.model_concrete.pojo;

import android.graphics.drawable.Drawable;

import com.nikolaevtsev.gameandrateofcurrency.abstract_.model_abstract.pojo.MineFieldInfo;

public class ImageMineFieldInfo implements MineFieldInfo {
    Drawable image;
    boolean isThisFragmentSafe;

    public ImageMineFieldInfo(Drawable image, boolean isThisFragmentSafe) {
        this.image = image;
        this.isThisFragmentSafe = isThisFragmentSafe;
    }

    @Override
    public Drawable getImage() {
        return image;
    }

    public void setImage(Drawable image) {
        this.image = image;
    }

    @Override
    public boolean isThisFragmentSafe() {
        return isThisFragmentSafe;
    }

    public void setThisFragmentSafe(boolean thisFragmentSafe) {
        isThisFragmentSafe = thisFragmentSafe;
    }
}
