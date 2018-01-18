package com.aniketkadam.toyrobotsimulator.botmovement;

import android.content.Context;

import com.aniketkadam.toyrobotsimulator.R;
import com.aniketkadam.toyrobotsimulator.botposition.BotPositionModel;

/**
 * Uses context to get the values.
 */

public class StringRepository implements IStringRespository {

    private final Context context;

    public StringRepository(Context context) {

        this.context = context;
    }

    @Override
    public String reportError() {
        return context.getString(R.string.robotNotPlacedError);
    }

    @Override
    public String reportPosition(BotPositionModel botPositionModel) {
        return context.getString(R.string.reportText, botPositionModel.getX(), botPositionModel.getY(), botPositionModel.getFacing().name());
    }

    @Override
    public String valueCannotBeEmpty() {
        return context.getString(R.string.valueCannotBeEmpty);
    }
}
