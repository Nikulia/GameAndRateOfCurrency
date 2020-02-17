package com.nikolaevtsev.gameandrateofcurrency.concrete.presenter_concrete;

import com.nikolaevtsev.gameandrateofcurrency.abstract_.model_abstract.pojo.MineFieldInfo;
import com.nikolaevtsev.gameandrateofcurrency.abstract_.view_abstract.MonkeyTreasureActivityAbstract;
import com.nikolaevtsev.gameandrateofcurrency.concrete.model_concrete.utils.MineFieldBuilder;

import java.util.List;

public class MonkeyTreasurePresenter {

    private List<MineFieldInfo> fragments;

    public MonkeyTreasurePresenter(MonkeyTreasureActivityAbstract monkeyTreasureActivity) {
        int countOfColumn = monkeyTreasureActivity.getCountOfColumn();
        MineFieldBuilder mineFieldBuilder = new MineFieldBuilder(monkeyTreasureActivity.getContext(),
                countOfColumn);
        fragments = mineFieldBuilder.getFragments();
    }

    public List<MineFieldInfo> getFragments() {
        return fragments;
    }

    public MineFieldInfo getMineFieldInfo(int position) {
        return fragments.get(position);
    }

}
