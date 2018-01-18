package com.aniketkadam.toyrobotsimulator.botmovement;

import com.aniketkadam.toyrobotsimulator.botposition.BotDirection;
import com.aniketkadam.toyrobotsimulator.botposition.BotPositionModel;
import com.aniketkadam.toyrobotsimulator.botposition.TurningDirection;
import com.aniketkadam.toyrobotsimulator.movementgrid.MovementGrid;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;

import static org.junit.Assert.assertEquals;

/**
 * Behavioural test for bot movement.
 */
public class BotMovementPresenterTest {

    private BotMovementPresenter botMovementPresenter;
    private MovementGrid movementGrid;
    @Mock IBotView view;
    @Mock IStringRespository stringRespository;

    @Before
    public void setUp() throws Exception {
        movementGrid = new MovementGrid(0, 0, 5, 5);
        botMovementPresenter = new BotMovementPresenter(movementGrid, view, stringRespository);
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
    public void moveForward_worksForNorthValidPositions() throws Exception {
        checkPositions(0, 0, 0, 1, BotDirection.NORTH);
    }

    @Test
    public void moveForward_stopsForNorthInValidPositions() throws Exception {
        checkPositions(0, 0, 0, 0, BotDirection.SOUTH);
    }

    @Test
    public void moveForward_worksForSouthValidPositions() throws Exception {
        checkPositions(0, 1, 0, 0, BotDirection.SOUTH);
    }

    @Test
    public void moveForward_stopsForSouthInvalidPositions() throws Exception {
        checkPositions(0, 0, 0, 0, BotDirection.SOUTH);
    }

    @Test
    public void moveForward_worksForEastValidPositions() throws Exception {
        checkPositions(0, 0, 1, 0, BotDirection.EAST);
    }

    @Test
    public void moveForward_stopsForEastInvalidPositions() throws Exception {
        checkPositions(movementGrid.getUbX(), 0, movementGrid.getUbX(), 0, BotDirection.EAST);
    }

    @Test
    public void moveForward_worksForWestValidPositions() throws Exception {
        checkPositions(1, 0, 0, 0, BotDirection.WEST);
    }

    @Test
    public void moveForward_stopsForWestInvalidPositions() throws Exception {
        checkPositions(0, 0, 0, 0, BotDirection.WEST);
    }

    private void checkPositions(int initialX, int initialY, int expectedX, int expectedY, BotDirection initalDirection) {
        botMovementPresenter.place(new BotPositionModel(initialX,initialY,initalDirection));
        botMovementPresenter.moveForward();
        BotPositionModel reportedPosition = botMovementPresenter.getCurrentPosition();
        assertEquals(String.format("%s movement incorrect", initalDirection), new BotPositionModel(expectedX, expectedY, initalDirection), reportedPosition);
    }

    @Test
    public void turnLeft_worksWith360DegreesMovement() throws Exception {
        checkTurn(BotDirection.NORTH, BotDirection.WEST, TurningDirection.LEFT);
        checkTurn(BotDirection.WEST, BotDirection.SOUTH, TurningDirection.LEFT);
        checkTurn(BotDirection.SOUTH, BotDirection.EAST, TurningDirection.LEFT);
        checkTurn(BotDirection.EAST, BotDirection.NORTH, TurningDirection.LEFT);
    }

    @Test
    public void turnRight_worksWith360DegreesMovement() throws Exception {
        checkTurn(BotDirection.NORTH, BotDirection.EAST, TurningDirection.RIGHT);
        checkTurn(BotDirection.EAST, BotDirection.SOUTH, TurningDirection.RIGHT);
        checkTurn(BotDirection.SOUTH, BotDirection.WEST, TurningDirection.RIGHT);
        checkTurn(BotDirection.WEST, BotDirection.NORTH, TurningDirection.RIGHT);
    }

    void checkTurn(BotDirection initialDirection, BotDirection expectedDirection, TurningDirection turningDirection) {
        BotPositionModel initial = new BotPositionModel(0,0, initialDirection);
        botMovementPresenter.place(initial);
        botMovementPresenter.turnToDirection(turningDirection);
        BotPositionModel expected = new BotPositionModel(0,0,expectedDirection);
        assertEquals(String.format("Bot did not turn to direction: %s", turningDirection.name()), expected, botMovementPresenter.getCurrentPosition());

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
                botMovementPresenter.place(positionModel);
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

    @Test
    public void reportPosition_remainsUnchangedAfterInvalidPlacePostions() throws Exception {
        BotPositionModel validPosition = new BotPositionModel( movementGrid.getLbX(), movementGrid.getUbY(), BotDirection.SOUTH);
        botMovementPresenter.place(validPosition);
        BotPositionModel illegalPosition = new BotPositionModel( movementGrid.getLbX() - 1, movementGrid.getUbY() + 1, BotDirection.NORTH);
        botMovementPresenter.place(illegalPosition);
        assertEquals("Invalid position was reported", validPosition, botMovementPresenter.reportPosition());
    }

    @Test
    public void reportPosition_worksAfterLeftTurn() throws Exception {
        BotPositionModel positionModel = new BotPositionModel( movementGrid.getLbX(), movementGrid.getUbY(), BotDirection.NORTH);
        botMovementPresenter.place(positionModel);
        botMovementPresenter.turnToDirection(TurningDirection.LEFT);
        BotPositionModel expectedPosition = new BotPositionModel( movementGrid.getLbX(), movementGrid.getUbY(), BotDirection.WEST);
        assertEquals("Report did not acknowledge a left turn from north to west", expectedPosition, botMovementPresenter.reportPosition());
    }

    @Test
    public void reportPosition_worksAfterRightTurn() throws Exception {
        BotPositionModel positionModel = new BotPositionModel( movementGrid.getLbX(), movementGrid.getUbY(), BotDirection.WEST);
        botMovementPresenter.place(positionModel);
        botMovementPresenter.turnToDirection(TurningDirection.RIGHT);
        BotPositionModel expectedPosition = new BotPositionModel( movementGrid.getLbX(), movementGrid.getUbY(), BotDirection.NORTH);
        assertEquals("Report did not acknowledge a left turn from north to west", expectedPosition, botMovementPresenter.reportPosition());
    }

}