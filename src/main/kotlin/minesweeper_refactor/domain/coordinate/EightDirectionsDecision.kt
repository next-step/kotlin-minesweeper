package minesweeper_refactor.domain.coordinate

import minesweeper_refactor.domain.coordinate.AroundDecision.Companion.MOVE_BACKWARD
import minesweeper_refactor.domain.coordinate.AroundDecision.Companion.MOVE_FORWARD
import minesweeper_refactor.domain.coordinate.AroundDecision.Companion.STOP

object EightDirectionsDecision : AroundDecision {

    override fun decide(): List<Coordinate> = listOf(
        Coordinate(x = MOVE_BACKWARD, y = MOVE_BACKWARD),
        Coordinate(x = MOVE_BACKWARD, y = STOP),
        Coordinate(x = STOP, y = MOVE_BACKWARD),
        Coordinate(x = MOVE_FORWARD, y = MOVE_FORWARD),
        Coordinate(x = MOVE_FORWARD, y = STOP),
        Coordinate(x = STOP, y = MOVE_FORWARD),
        Coordinate(x = MOVE_BACKWARD, y = MOVE_FORWARD),
        Coordinate(x = MOVE_FORWARD, y = MOVE_BACKWARD),
    )
}
