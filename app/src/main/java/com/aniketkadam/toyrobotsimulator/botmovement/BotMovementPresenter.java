package com.aniketkadam.toyrobotsimulator.botmovement;

import android.support.annotation.RestrictTo;

import com.aniketkadam.toyrobotsimulator.botposition.BotDirection;
import com.aniketkadam.toyrobotsimulator.botposition.BotPositionModel;
import com.aniketkadam.toyrobotsimulator.movementgrid.MovementGrid;

import org.jetbrains.annotations.NotNull;

/**
 * Handles movement for the bot.
 */

public final class BotMovementPresenter implements IBotMovement {

    private final MovementGrid movementGrid;
    private BotPositionModel currentPositon;

    public BotMovementPresenter(@NotNull MovementGrid movementGrid) {

        this.movementGrid = movementGrid;
    }

    @Override
    public boolean place(@NotNull BotPositionModel movementModel) {
        boolean result = validatePlace(movementGrid, movementModel);
        if (result) {
            currentPositon = movementModel;
        }
        return result;
    }

    @Override
    public boolean moveForward() {
        BotPositionModel expectedPositon = getUnvalidatedExpectedPositonAfterMove(currentPositon, currentPositon.getFacing());
        if ( validatePlace(movementGrid, expectedPositon) )
        {
            currentPositon = expectedPositon;
            return true;
        } else {
            return false;
        }
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

    /**
     * * Note: Consider rewriting in kotlin so the changing of the attributes can be implemented in expectedPostion = currentPosition.copy(y = 2);
     * @param currentPositon the position that the bot is currently in.
     * @param directionToMove where it is facing currently and therefore the direction to move in.
     * @return the new {BotPostionalModel}, moved to wherever it was supposed to. It is NOT guaranteed to be a valid position.
     */
    private BotPositionModel getUnvalidatedExpectedPositonAfterMove(BotPositionModel currentPositon, BotDirection directionToMove) {
        BotPositionModel expectedPositon = currentPositon.clone();
        // Move the current position to where it would be if the movement was executed.
        switch (directionToMove) {

            case NORTH:
                expectedPositon.setY(currentPositon.getY() + 1);
                break;

            case SOUTH:
                expectedPositon.setY(currentPositon.getY() - 1);
                break;
            case EAST:
                expectedPositon.setX(currentPositon.getX() + 1);
                break;
            case WEST:
                expectedPositon.setX(currentPositon.getX() - 1);
                break;
        }

        return expectedPositon;
    }

    /**
     * The current position should only be retrieved by tests to check if it is what was expected.
     * There is no reason currently, to call this directly. Consider looking at the function reportPositon.
     * @return the current position for testing if it's valid.
     */
    @RestrictTo(RestrictTo.Scope.TESTS)
    BotPositionModel getCurrentPosition() {
        return currentPositon;
    }
}
