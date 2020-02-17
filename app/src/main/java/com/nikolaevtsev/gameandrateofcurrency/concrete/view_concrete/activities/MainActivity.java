package com.nikolaevtsev.gameandrateofcurrency.concrete.view_concrete.activities;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ProgressBar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.ViewCompat;

import com.nikolaevtsev.gameandrateofcurrency.R;
import com.nikolaevtsev.gameandrateofcurrency.abstract_.view_abstract.MainActivityAbstract;
import com.nikolaevtsev.gameandrateofcurrency.concrete.presenter_concrete.DownloadJsonPresenter;

public class MainActivity extends AppCompatActivity implements MainActivityAbstract {

    private ProgressBar progressBar;
    private DownloadJsonPresenter downloadJsonPresenter;
    public static final String INTENT_DOLLAR_RATE = "dollar rate";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        progressBar = findViewById(R.id.progressBar);
        downloadJsonPresenter = new DownloadJsonPresenter(this);
    }

    public void onClickGoToMonkeyTreasure(View view) {
        Intent intent = new Intent(this, MonkeyTreasureActivity.class);
        startActivity(intent);
    }

    public void onClickGoToWebView(View view) {
        progressBar.setVisibility(View.VISIBLE);
        downloadJsonPresenter.loadData();
    }

    @Override
    public void loadFinish(String dollarRate) {
        Intent intent = new Intent(this, WebViewActivity.class);
        intent.putExtra(INTENT_DOLLAR_RATE, dollarRate);
        startActivity(intent);
    }
}
