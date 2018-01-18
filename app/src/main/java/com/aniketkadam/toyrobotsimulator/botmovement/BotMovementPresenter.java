package com.aniketkadam.toyrobotsimulator.botmovement;

import com.aniketkadam.toyrobotsimulator.botposition.BotPositionModel;

/**
 * Handles movement for the bot.
 */

public class BotMovementPresenter implements IBotMovement {

    @Override
    public boolean place(BotPositionModel movementModel) {
        return false;
    }

    @Override
    public boolean moveForward() {
        return false;
    }

    @Override
    public boolean moveLeft() {
        return false;
    }

    @Override
    public boolean moveRight() {
        return false;
    }

    @Override
    public BotPositionModel reportPosition() {
        return null;
    }
}
