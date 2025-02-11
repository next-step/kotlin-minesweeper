package minesweeper.domain

data class Position(val x: Int, val y: Int) {
    fun nearbyPositions(): Set<Position> {
        return NearbyDirection.entries.map { direction ->
            Position(
                x + direction.dx(),
                y + direction.dy(),
            )
        }.toSet()
    }
}
