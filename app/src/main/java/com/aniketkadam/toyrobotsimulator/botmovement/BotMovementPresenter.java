package com.aniketkadam.toyrobotsimulator.botmovement;

import com.aniketkadam.toyrobotsimulator.botposition.BotPositionModel;
import com.aniketkadam.toyrobotsimulator.movementgrid.MovementGrid;

/**
 * Handles movement for the bot.
 */

public final class BotMovementPresenter implements IBotMovement {

    private final MovementGrid movementGrid;

    public BotMovementPresenter(MovementGrid movementGrid) {

        this.movementGrid = movementGrid;
    }

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
