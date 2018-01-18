package com.aniketkadam.toyrobotsimulator;

import com.aniketkadam.toyrobotsimulator.botmovement.BotMovementPresenter;
import com.aniketkadam.toyrobotsimulator.botmovement.IBotView;
import com.aniketkadam.toyrobotsimulator.botmovement.IStringRespository;
import com.aniketkadam.toyrobotsimulator.botposition.BotDirection;
import com.aniketkadam.toyrobotsimulator.botposition.BotPositionModel;
import com.aniketkadam.toyrobotsimulator.movementgrid.MovementGrid;

import org.junit.Before;
import org.junit.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.ArgumentMatcher;
import org.mockito.ArgumentMatchers;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import static org.junit.Assert.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.atLeastOnce;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.only;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

/**
 * Created by nicky on 18/1/18.
 */
public class MainActivityTest {

    @Mock
    IBotView view;
    private final int lowerBoundX = 0;
    MovementGrid movementGrid = new MovementGrid(lowerBoundX, 0, 5, 5);
    @Mock
    IStringRespository stringRespository;
    BotMovementPresenter botMovementPresenter;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void showReport_showsErrorMessageWhenBotNotPlaced() throws Exception {
        when(view.getXPosition()).thenReturn("");
        when(view.getYPosition()).thenReturn("");

        String errorString = "error";
        when(stringRespository.reportError()).thenReturn(errorString);

        botMovementPresenter = new BotMovementPresenter(movementGrid, view, stringRespository);
        botMovementPresenter.report();
        verify(view).showReport(errorString);

    }

    @Test
    public void showReport_showsPositionMessageWhenBotPlaced() throws Exception {
        when(view.getXPosition()).thenReturn("1");
        when(view.getYPosition()).thenReturn("2");
        String correctString = "correct";
        when(stringRespository.reportPosition(any(BotPositionModel.class))).thenReturn(correctString);

        botMovementPresenter = new BotMovementPresenter(movementGrid, view, stringRespository);
        botMovementPresenter.place(new BotPositionModel(1,2, BotDirection.SOUTH));
        botMovementPresenter.report();

        verify(view).showReport(correctString);

    }

    @Test
    public void place_showErrorIfXIsEmptyAndNotYError() throws Exception{
        when(view.getXPosition()).thenReturn("");
        when(view.getYPosition()).thenReturn("2");
        when(view.getSelectedDirection()).thenReturn(BotDirection.EAST);
        String errorString = "error";

        when(stringRespository.valueCannotBeEmpty()).thenReturn(errorString);

        botMovementPresenter = new BotMovementPresenter(movementGrid, view, stringRespository);
        botMovementPresenter.placeClicked();

        verify(view).showXTextError(errorString);
        verify(view, never()).showYTextError(anyString());

    }

    @Test
    public void place_showErrorIfYIsEmptyAndNotXError() throws Exception{
        when(view.getXPosition()).thenReturn("1");
        when(view.getYPosition()).thenReturn("");
        when(view.getSelectedDirection()).thenReturn(BotDirection.EAST);
        String errorString = "error";

        when(stringRespository.valueCannotBeEmpty()).thenReturn(errorString);

        botMovementPresenter = new BotMovementPresenter(movementGrid, view, stringRespository);
        botMovementPresenter.placeClicked();

        verify(view).showYTextError(errorString);
        verify(view, never()).showXTextError(anyString());

    }

    @Test
    public void place_showNoErrorIfAllValuesFilled() throws Exception{
        when(view.getXPosition()).thenReturn("1");
        when(view.getYPosition()).thenReturn("2");
        when(view.getSelectedDirection()).thenReturn(BotDirection.EAST);
        String errorString = "error";

        when(stringRespository.valueCannotBeEmpty()).thenReturn(errorString);

        botMovementPresenter = new BotMovementPresenter(movementGrid, view, stringRespository);
        botMovementPresenter.placeClicked();

        verify(view, never()).showXTextError(anyString());
        verify(view, never()).showYTextError(anyString());
    }

    @Test
    public void place_showErrorIfOutOfBounds() throws Exception{
        when(view.getXPosition()).thenReturn(String.valueOf(lowerBoundX - 1));
        when(view.getYPosition()).thenReturn("2");
        when(view.getSelectedDirection()).thenReturn(BotDirection.EAST);
        String outOfBoundsError = "error";
        when(stringRespository.getOutOfBoundsPlaceError()).thenReturn(outOfBoundsError);

        botMovementPresenter = new BotMovementPresenter(movementGrid, view, stringRespository);
        botMovementPresenter.placeClicked();

        verify(view, never()).showXTextError(anyString());
        verify(view, never()).showYTextError(anyString());

        verify(view).showReport(outOfBoundsError);
    }


}