package mineswipper.domain.map.position

data class Positions(
    val positions: List<Position>
) {
    fun contains(position: Position): Boolean {
        return positions.contains(position)
    }
}
