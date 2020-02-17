package com.nikolaevtsev.gameandrateofcurrency.concrete.model_concrete.utils;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.util.Log;

import com.nikolaevtsev.gameandrateofcurrency.R;
import com.nikolaevtsev.gameandrateofcurrency.abstract_.model_abstract.pojo.MineFieldInfo;
import com.nikolaevtsev.gameandrateofcurrency.concrete.model_concrete.pojo.ImageMineFieldInfo;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class MineFieldBuilder {

    private static int SAFE = 0;
    private static int SNAKES = 1;
    private static int SCORPIONS = 2;
    private static int VARIANTS = 3;
    private List<MineFieldInfo> fragments;
    Context context;
    private int countOfColumn;
    private Drawable drawableSnakes;
    private Drawable drawableScorpions;
    private Drawable drawableMonkey;
    private Random random;

    public MineFieldBuilder(Context context, int countOfColumn) {
        this.context = context;
        this.countOfColumn = countOfColumn;
        drawableSnakes = DrawableBuild.getDrawable(context, R.drawable.snakes);
        drawableScorpions = DrawableBuild.getDrawable(context, R.drawable.scorpions);
        drawableMonkey = DrawableBuild.getDrawable(context, R.drawable.monkey_small);
        random = new Random(System.currentTimeMillis());
        fragments = new ArrayList<>();
    }

    public List<MineFieldInfo> getFragments() {
        for (int i = 0; i < countOfColumn; i++) {
            boolean isFragmentSafe = false;
            for (int j = 0; j < countOfColumn; j++) {
                int variant;
                if (j == countOfColumn - 1 && !isFragmentSafe)
                    variant = 0;
                else
                    variant = random.nextInt(VARIANTS);

                if (variant == SAFE) {
                    if (!isFragmentSafe) {
                        isFragmentSafe = true;
                        fragments.add(new ImageMineFieldInfo(drawableMonkey, true));
                    } else
                        ++variant;
                }
                if (variant == SNAKES)
                    fragments.add(new ImageMineFieldInfo(drawableSnakes, false));
                if (variant == SCORPIONS)
                    fragments.add(new ImageMineFieldInfo(drawableScorpions, false));
            }
        }
        return fragments;
    }

}
