package com.aniketkadam.toyrobotsimulator;

import android.os.Bundle;
import android.widget.Toast;

import com.aniketkadam.toyrobotsimulator.base.BaseActivity;
import com.aniketkadam.toyrobotsimulator.botmovement.BotMovementPresenter;
import com.aniketkadam.toyrobotsimulator.botmovement.IBotView;
import com.aniketkadam.toyrobotsimulator.databinding.ActivityMainBinding;
import com.aniketkadam.toyrobotsimulator.movementgrid.MovementGrid;

public class MainActivity extends BaseActivity<BotMovementPresenter, ActivityMainBinding> implements IBotView {

    private final int lowerBoundX = 0;
    private final int getLowerBoundY = 0;
    private final int upperBoundX = 5;
    private final int upperBoundY = 5;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    protected int getLayoutRes() {
        return R.layout.activity_main;
    }

    @Override
    public BotMovementPresenter getPresenter() {
        return new BotMovementPresenter(new MovementGrid(lowerBoundX, getLowerBoundY, upperBoundX, upperBoundY));
    }

    @Override
    public void showReport(String report) {
        Toast.makeText(this, report, Toast.LENGTH_LONG).show();
    }
}
