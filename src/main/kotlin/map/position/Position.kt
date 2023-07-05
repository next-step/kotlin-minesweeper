package map.position

data class Position(
    val y: Int,
    val x: Int,
) {
    fun getAdjacentNeighbors(
        pos: Position,
    ): List<Position> {
        val lu = Pair(pos.y - 1, pos.x - 1)
        val rb = Pair(pos.y + 1, pos.x + 1)

        return (lu.first..rb.first).map { y ->
            (lu.second..rb.second).map { x -> Position(y, x) }
        }
            .flatten()
            .filterNot { it == pos }
    }
}
