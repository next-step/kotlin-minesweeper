package next.step.minesweeper.domain.position

data class Position(val x: Int, val y: Int) {

    fun near(): Positions =
        Positions(
            NearPositionDelta.values()
                .map { Position(x + it.dx, y + it.dy) }
                .toSet(),
        )
}
