package com.nikolaevtsev.gameandrateofcurrency.concrete.presenter_concrete;

import com.nikolaevtsev.gameandrateofcurrency.abstract_.presenter_abstract.JsonPresenter;
import com.nikolaevtsev.gameandrateofcurrency.abstract_.view_abstract.MainActivityAbstract;
import com.nikolaevtsev.gameandrateofcurrency.concrete.model_concrete.utils.JsonUtils;

public class DownloadJsonPresenter implements JsonPresenter {

    MainActivityAbstract mainActivity;

    public DownloadJsonPresenter(MainActivityAbstract mainActivity) {
        this.mainActivity = mainActivity;
    }

    @Override
    public void loadFinish(String dollarRate) {
        mainActivity.loadFinish(dollarRate);
    }
    public void loadData() {
        JsonUtils jsonUtils = new JsonUtils(this);
        jsonUtils.startDownloadDollarRate();
    }
}
