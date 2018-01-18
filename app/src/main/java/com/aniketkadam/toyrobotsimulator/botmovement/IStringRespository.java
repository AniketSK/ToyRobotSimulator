package com.aniketkadam.toyrobotsimulator.botmovement;

import com.aniketkadam.toyrobotsimulator.botposition.BotPositionModel;

/**
 * Provides access to the strings which would only be in layout.strings
 */

public interface IStringRespository {
    String reportError();

    String reportPosition(BotPositionModel botPositionModel);

    String valueCannotBeEmpty();

    String getOutOfBoundsPlaceError();
}
