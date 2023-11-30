package domain

class NeighborPositions(private val center: Position, private val boardHeight: Int, private val boardWidth: Int) {
    private val offsets = listOf(
        Position(-1, -1), Position(0, -1), Position(1, -1),
        Position(-1, 0), Position(1, 0),
        Position(-1, 1), Position(0, 1), Position(1, 1)
    )

    val positions: List<Position> by lazy {
        offsets.map { offset -> Position(center.x + offset.x, center.y + offset.y) }
            .filter { it.isWithinBounds() && it != center }
    }

    private fun Position.isWithinBounds(): Boolean =
        x in 0 until boardWidth && y in 0 until boardHeight
}
