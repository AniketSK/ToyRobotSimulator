package com.aniketkadam.toyrobotsimulator.botmovement;

import com.aniketkadam.toyrobotsimulator.botposition.BotPositionModel;

/**
 * Ways the bot can move.
 */

public interface IBotMovement {
    boolean place(BotPositionModel movementModel);
    boolean moveForward();
    boolean moveLeft();
    boolean moveRight();
    BotPositionModel reportPosition();
}
