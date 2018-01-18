package com.aniketkadam.toyrobotsimulator;

import android.os.Bundle;
import android.support.annotation.IntegerRes;
import android.widget.Toast;

import com.aniketkadam.toyrobotsimulator.base.BaseActivity;
import com.aniketkadam.toyrobotsimulator.botmovement.BotMovementPresenter;
import com.aniketkadam.toyrobotsimulator.botmovement.IBotView;
import com.aniketkadam.toyrobotsimulator.botposition.BotDirection;
import com.aniketkadam.toyrobotsimulator.botposition.BotPositionModel;
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
        binding.setPresenter(presenter);
    }

    @Override
    protected int getLayoutRes() {
        return R.layout.activity_main;
    }

    @Override
    public BotMovementPresenter getPresenter() {
        return new BotMovementPresenter(new MovementGrid(lowerBoundX, getLowerBoundY, upperBoundX, upperBoundY), this);
    }

    @Override
    public void showReport(String report) {
        Toast.makeText(this, report, Toast.LENGTH_LONG).show();
    }

    @Override
    public BotDirection getSelectedDirection() {
        BotDirection selectedDirection;
        switch (binding.positionRadioGroup.getCheckedRadioButtonId()) {
            case R.id.radioButtonNorth:
                selectedDirection = BotDirection.NORTH;
                break;

            case R.id.radioButtonSouth:
                selectedDirection = BotDirection.SOUTH;
                break;

            case R.id.radioButtonWest:
                selectedDirection = BotDirection.WEST;
                break;

            case R.id.radioButtonEast:
                selectedDirection = BotDirection.EAST;
                break;

            default:
                selectedDirection = null; // An error is wanted here. The null guarantees that.

        }

        return selectedDirection;
    }

    @Override
    public String getXPosition() {
        return binding.textViewX.getText().toString();
    }

    @Override
    public String getYPosition() {
        return binding.textViewY.getText().toString();
    }

}
