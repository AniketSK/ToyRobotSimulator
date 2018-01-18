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
    MovementGrid movementGrid;

    @Before
    public void setUp() throws Exception {
        movementGrid = new MovementGrid(0, 0, 5, 5);
        botMovementPresenter = new BotMovementPresenter(movementGrid);
    }

    @After
    public void tearDown() throws Exception {
    }

    @Test
    public void place_outOfGridYHighFails() throws Exception {
        boolean moveSuccessful = botMovementPresenter.place(new BotPositionModel(movementGrid.getLbX(), movementGrid.getUbY() + 1, BotDirection.NORTH));
        assertEquals("Place succeeded when it shouldn't have", false, moveSuccessful);
    }

    @Test
    public void place_outOfGridXHighFails() throws Exception {
        boolean moveSuccessful = botMovementPresenter.place(new BotPositionModel(movementGrid.getUbX() + 1, movementGrid.getLbY(), BotDirection.NORTH));
        assertEquals("Place succeeded when it shouldn't have", false, moveSuccessful);
    }

    @Test
    public void place_outOfGridYLowFails() throws Exception {
        boolean moveSuccessful = botMovementPresenter.place(new BotPositionModel(movementGrid.getLbX(), movementGrid.getLbY() - 1, BotDirection.NORTH));
        assertEquals("Place succeeded when it shouldn't have", false, moveSuccessful);
    }

    @Test
    public void place_outOfGridXLowFails() throws Exception {
        boolean moveSuccessful = botMovementPresenter.place(new BotPositionModel(movementGrid.getLbX() - 1, movementGrid.getLbY(), BotDirection.NORTH));
        assertEquals("Place succeeded when it shouldn't have", false, moveSuccessful);
    }


    @Test
    public void place_allInGridPositonsAreValid() throws Exception {
        for (int y = movementGrid.getLbY(); y <= movementGrid.getUbY(); y++) {
            for (int x = movementGrid.getLbX(); x <= movementGrid.getUbX(); x++) {
                boolean moveSuccessful = botMovementPresenter.place(new BotPositionModel(x, y, BotDirection.NORTH));
                assertEquals(String.format("Place failed when it shouldn't have at x:%d and y:%d", x, y), true, moveSuccessful);
            }
        }
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

    @Test
    public void reportPosition_returnsNullIfBotNotPlaced() throws Exception {
        BotPositionModel report = botMovementPresenter.reportPosition();
        assertEquals("Position reported when none was present", null, report);
    }

    @Test
    public void reportPosition_returnsAccuratelyOnAllValidPlacedPositions() throws Exception {
        BotPositionModel positionModel;
        for (int y = movementGrid.getLbY(); y <= movementGrid.getUbY(); y++) {
            for (int x = movementGrid.getLbX(); x <= movementGrid.getUbX(); x++) {
                positionModel = new BotPositionModel(x, y, BotDirection.NORTH);
                boolean moveSuccessful = botMovementPresenter.place(positionModel);
                BotPositionModel reportedPosition = botMovementPresenter.reportPosition();
                assertEquals(String.format("Actual position x:%d and y:%d , reported position:x: %s, y: %s",
                        x,
                        y,
                        reportedPosition == null ? "Position was null" : reportedPosition.getX(),
                        reportedPosition == null ? "Position was null" : reportedPosition.getY()),
                        positionModel, reportedPosition);
            }
        }
    }

}