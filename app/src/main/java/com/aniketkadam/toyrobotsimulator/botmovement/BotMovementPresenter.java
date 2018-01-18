package com.aniketkadam.toyrobotsimulator.botmovement;

import com.aniketkadam.toyrobotsimulator.botposition.BotPositionModel;
import com.aniketkadam.toyrobotsimulator.movementgrid.MovementGrid;

/**
 * Handles movement for the bot.
 */

public final class BotMovementPresenter implements IBotMovement {

    private final MovementGrid movementGrid;
    private BotPositionModel currentPositon;

    public BotMovementPresenter(MovementGrid movementGrid) {

        this.movementGrid = movementGrid;
    }

    @Override
    public boolean place(BotPositionModel movementModel) {
        boolean result = validatePlace(movementGrid, movementModel);
        if (result) {
            currentPositon = movementModel;
        }
        return result;
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
        return currentPositon;
    }

    /**
     * Validates a placement of the bot, on any part of the board.
     * @param moveGrid the grid which the bot is going to be placed on.
     * @param position the position to which it should be moved.
     * @return true if the position is valid, false otherwise.
     */
    private boolean validatePlace(MovementGrid moveGrid, BotPositionModel position) {
        if (position.getX() < moveGrid.getLbX() ||
                position.getX() > moveGrid.getUbX() ||
                position.getY() < moveGrid.getLbY() ||
                position.getY() > moveGrid.getUbY()) {
            return false;
        } else {
            return true;
        }
    }
}
