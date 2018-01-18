package com.aniketkadam.toyrobotsimulator.botposition

import com.aniketkadam.toyrobotsimulator.botposition.BotDirection.Direction

/**
 * Represents the position and orientation of the robot on the board.
 */
data class BotPositionModel(var x : Int, var y : Int, var facing : BotDirection)
