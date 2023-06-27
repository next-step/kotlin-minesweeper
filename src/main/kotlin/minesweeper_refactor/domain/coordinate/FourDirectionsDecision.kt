package minesweeper_refactor.domain.coordinate

object FourDirectionsDecision: AroundDecision {

    override fun decide(): List<Coordinate> = listOf(
        Coordinate(x = AroundDecision.MOVE_BACKWARD, y = AroundDecision.STOP),
        Coordinate(x = AroundDecision.STOP, y = AroundDecision.MOVE_BACKWARD),
        Coordinate(x = AroundDecision.MOVE_FORWARD, y = AroundDecision.STOP),
        Coordinate(x = AroundDecision.STOP, y = AroundDecision.MOVE_FORWARD),
    )
}
