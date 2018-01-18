package com.aniketkadam.toyrobotsimulator.botmovement;

import com.aniketkadam.toyrobotsimulator.botposition.BotPositionModel;
import com.aniketkadam.toyrobotsimulator.botposition.TurningDirection;

/**
 * Ways the bot can move.
 */

public interface IBotMovement {
    boolean place(BotPositionModel movementModel);
    boolean moveForward();
    void turnToDirection(TurningDirection turningDirection);

    /**
     * Returns the current position of the bot if it is placed, or null otherwise. Such as at
     * the beginning of the game where no position is decided.
     * @return A position if the bot is placed, null otherwise.
     */
    BotPositionModel reportPosition();
}
