package com.aniketkadam.toyrobotsimulator.botmovement;

import com.aniketkadam.toyrobotsimulator.botposition.BotDirection;
import com.aniketkadam.toyrobotsimulator.botposition.BotPositionModel;
import com.aniketkadam.toyrobotsimulator.movementgrid.MovementGrid;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Behavioural test for bot movement.
 */
public class BotMovementPresenterTest {

    BotPositionModel model;
    BotMovementPresenter botMovementPresenter;

    @Before
    public void setUp() throws Exception {
        MovementGrid movementGrid = new MovementGrid(5,5);
        botMovementPresenter = new BotMovementPresenter(movementGrid);
    }

    @After
    public void tearDown() throws Exception {
    }

    @Test
    public void place() throws Exception {
    }

    @Test
    public void moveForward() throws Exception {
    }

    @Test
    public void moveLeft() throws Exception {
    }

    @Test
    public void moveRight() throws Exception {
    }

    @Test
    public void reportPosition() throws Exception {
    }

}