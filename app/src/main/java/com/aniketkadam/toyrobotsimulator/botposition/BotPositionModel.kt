package com.aniketkadam.toyrobotsimulator.botposition

/**
 * Represents the position and orientation of the robot on the board.
 */
data class BotPositionModel(var x : Int, var y : Int, var facing : BotDirection)
{
    fun clone() : BotPositionModel {
        return BotPositionModel(x, y, facing);
    }
}