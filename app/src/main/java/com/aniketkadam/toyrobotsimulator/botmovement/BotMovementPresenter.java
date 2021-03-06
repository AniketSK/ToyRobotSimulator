package com.aniketkadam.toyrobotsimulator.botmovement;

import android.support.annotation.NonNull;
import android.support.annotation.RestrictTo;

import com.aniketkadam.toyrobotsimulator.base.BasePresenter;
import com.aniketkadam.toyrobotsimulator.botposition.BotDirection;
import com.aniketkadam.toyrobotsimulator.botposition.BotPositionModel;
import com.aniketkadam.toyrobotsimulator.botposition.TurningDirection;
import com.aniketkadam.toyrobotsimulator.movementgrid.MovementGrid;

import org.jetbrains.annotations.NotNull;

/**
 * Handles movement for the bot.
 */

public final class BotMovementPresenter extends BasePresenter implements IBotMovement {

    private final MovementGrid movementGrid;
    private final IBotView view;
    private BotPositionModel currentPositon;
    private final IStringRespository stringRespository;

    public BotMovementPresenter(@NotNull MovementGrid movementGrid, IBotView view, IStringRespository stringRespository) {
        this.movementGrid = movementGrid;
        this.view = view;
        this.stringRespository = stringRespository;
    }

    @Override
    public void report() {
        BotPositionModel position = reportPosition();
        if ( position == null ) {
            view.showReport(stringRespository.reportError());
        } else {
            view.showReport(stringRespository.reportPosition(position));
        }
    }

    @Override
    public void placeClicked() {
        String x = view.getXPosition();
        String y = view.getYPosition();
        BotDirection direction = view.getSelectedDirection();
        if ( x.isEmpty() ) {
            view.showXTextError(stringRespository.valueCannotBeEmpty());
        } else if ( y.isEmpty() ) {
            view.showYTextError(stringRespository.valueCannotBeEmpty());
        } else {
            place(new BotPositionModel(Integer.valueOf(x), Integer.valueOf(y), direction));
        }
    }

    public boolean place(@NotNull BotPositionModel movementModel) {
        boolean result = validatePlace(movementGrid, movementModel);
        if (result) {
            currentPositon = movementModel;
        } else {
            view.showReport(stringRespository.getOutOfBoundsPlaceError());
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
    public void turnToDirection(@NonNull TurningDirection turningDirection) {
        BotDirection newDirection = getNewPostion(currentPositon.getFacing(), turningDirection);
        currentPositon.setFacing(newDirection);
    }

    private @NonNull BotDirection getNewPostion(@NonNull BotDirection facing, @NonNull TurningDirection directionToTurn) {
        BotDirection newDirection = null;
        switch (facing) {

            case NORTH:
                newDirection = (directionToTurn == TurningDirection.LEFT) ? BotDirection.WEST : BotDirection.EAST;
                break;
            case SOUTH:
                newDirection = (directionToTurn == TurningDirection.LEFT) ? BotDirection.EAST : BotDirection.WEST;
                break;
            case EAST:
                newDirection = (directionToTurn == TurningDirection.LEFT) ? BotDirection.NORTH : BotDirection.SOUTH;
                break;
            case WEST:
                newDirection = (directionToTurn == TurningDirection.LEFT) ? BotDirection.SOUTH : BotDirection.NORTH;
                break;
        }

        return newDirection;
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
