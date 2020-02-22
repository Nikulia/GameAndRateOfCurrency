package com.nikolaevtsev.gameandrateofcurrency.concrete.view_concrete.activities;

import android.content.Intent;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.webkit.WebView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.nikolaevtsev.gameandrateofcurrency.R;
import com.nikolaevtsev.gameandrateofcurrency.concrete.view_concrete.WebViewClientCurrencyComparator;

public class WebViewActivity extends AppCompatActivity {

    private TextView textViewCurrentDollar;
    private WebView webViewCurrencyComparator;
    private static final String COMPARE_CURRENCY_URL =
            "http://www.banki.ru/products/currency/cash/moskva/";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_web_view);
        textViewCurrentDollar = findViewById(R.id.textViewCurrentDollar);
        webViewCurrencyComparator = findViewById(R.id.webViewCurrencyComparator);
        Intent intent = getIntent();
        if (intent != null && intent.hasExtra(MainActivity.INTENT_DOLLAR_RATE)) {
            String dollarRate = intent.getStringExtra(MainActivity.INTENT_DOLLAR_RATE);
            textViewCurrentDollar.setText(dollarRate);
        }
        else
            textViewCurrentDollar.setText
                    (getString(R.string.value_of_the_dollar_did_not_load));
    }

    public void onClickCompare(View view) {
        webViewCurrencyComparator.setWebViewClient(new WebViewClientCurrencyComparator());
        webViewCurrencyComparator.loadUrl(COMPARE_CURRENCY_URL);
        webViewCurrencyComparator.setVisibility(View.VISIBLE);
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK && webViewCurrencyComparator.canGoBack()) {
            webViewCurrencyComparator.goBack();
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }
}
