package mineswipper.domain.map.position

class Positions(
    val positions: List<Position>
) {
    fun contains(position: Position): Boolean {
        return positions.contains(position)
    }
}
