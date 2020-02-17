package com.nikolaevtsev.gameandrateofcurrency.concrete.view_concrete.activities;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.nikolaevtsev.gameandrateofcurrency.R;
import com.nikolaevtsev.gameandrateofcurrency.abstract_.model_abstract.pojo.MineFieldInfo;
import com.nikolaevtsev.gameandrateofcurrency.abstract_.view_abstract.MonkeyTreasureActivityAbstract;
import com.nikolaevtsev.gameandrateofcurrency.concrete.presenter_concrete.MonkeyTreasurePresenter;
import com.nikolaevtsev.gameandrateofcurrency.concrete.view_concrete.adapters.MineFieldAdapter;

import java.util.List;

public class MonkeyTreasureActivity extends AppCompatActivity implements MonkeyTreasureActivityAbstract {

    private final int COUNT_OF_COLUMN = 3;
    private MineFieldAdapter adapter;
    private RecyclerView recyclerViewMineField;
    private MonkeyTreasurePresenter monkeyTreasurePresenter;
    private int line;

    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_monkey_treasure);
        recyclerViewMineField = findViewById(R.id.recyclerViewMineField);
        adapter = new MineFieldAdapter(COUNT_OF_COLUMN);
        recyclerViewMineField.setLayoutManager(new GridLayoutManager(this, COUNT_OF_COLUMN));
        recyclerViewMineField.setAdapter(adapter);
        monkeyTreasurePresenter = new MonkeyTreasurePresenter(this);
        line = 0;
        final List<MineFieldInfo> fragments = monkeyTreasurePresenter.getFragments();
        adapter.setFragments(fragments);
        adapter.setOnMineFieldFragmentClickListener(new MineFieldAdapter.
                OnMineFieldFragmentClickListener() {
            @Override
            public void onMineFieldFragmentClick(int position) {
                MineFieldInfo mineFieldInfo = monkeyTreasurePresenter.getMineFieldInfo(position);
                if (mineFieldInfo.isThisFragmentSafe() &&
                        position / COUNT_OF_COLUMN == 0) {
                    AlertDialog.Builder builder = new AlertDialog.Builder
                            (MonkeyTreasureActivity.this);
                    builder.setTitle(getString(R.string.congratulations));
                    builder.setMessage(R.string.dialog_message_win);
                    builder.setCancelable(false);
                    builder.setPositiveButton(R.string.button_play_again, new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            dialog.dismiss();
                            Intent intent = new Intent(MonkeyTreasureActivity.this,
                                    MonkeyTreasureActivity.class);
                            startActivity(intent);
                        }
                    });
                    AlertDialog dialog = builder.create();
                    dialog.show();
                } else if (!mineFieldInfo.isThisFragmentSafe()) {
                    AlertDialog.Builder builder = new AlertDialog.Builder
                            (MonkeyTreasureActivity.this);
                    builder.setTitle(getString(R.string.dialog_lose_title));
                    builder.setMessage(R.string.dialog_lose_message);
                    builder.setCancelable(false);
                    builder.setPositiveButton(R.string.button_play_again, new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            dialog.dismiss();
                            Intent intent = new Intent(MonkeyTreasureActivity.this,
                                    MonkeyTreasureActivity.class);
                            startActivity(intent);
                        }
                    });
                    AlertDialog dialog = builder.create();
                    dialog.show();
                }
            }
        });
    }

    @Override
    public int getCountOfColumn() {
        return COUNT_OF_COLUMN;
    }

    @Override
    public Context getContext() {
        return getApplicationContext();
    }
}
