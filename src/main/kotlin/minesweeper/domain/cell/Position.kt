package minesweeper.domain.cell

data class Position(
    val x: Int,
    val y: Int,
) {
    fun getNearbyPosition(): Set<Position> {
        return NearbyDirection.values().map {
            Position(it.x + this.x, it.y + this.y)
        }.toSet()
    }
}
