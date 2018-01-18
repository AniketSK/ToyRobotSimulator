package com.aniketkadam.toyrobotsimulator.botmovement;

import com.aniketkadam.toyrobotsimulator.base.BaseView;
import com.aniketkadam.toyrobotsimulator.botposition.BotDirection;

/**
 * Defines the view interface for the bot view.
 */

public interface IBotView extends BaseView {
    void showReport(String report);

    BotDirection getSelectedDirection();

    String getXPosition();

    String getYPosition();
}
