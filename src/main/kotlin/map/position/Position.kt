package map.position

data class Position(
    val y: Int,
    val x: Int,
) {
    fun getAdjacentNeighbors(): List<Position> {
        val lu = Pair(y - 1, x - 1)
        val rb = Pair(y + 1, x + 1)

        return (lu.first..rb.first).map { y ->
            (lu.second..rb.second).map { x -> Position(y, x) }
        }
            .flatten()
            .filterNot { it == this }
    }
}
