package com.aniketkadam.toyrobotsimulator.movementgrid

/**
 * Defines the grid that will be used to determine valid positions for the bot.
 * Assumes a grid with lower and upper x (width) bounds and lower and upper y (height) bounds
 */
data class MovementGrid(val lbX : Int,
                        val lbY: Int,
                        val ubX : Int,
                        val ubY : Int)