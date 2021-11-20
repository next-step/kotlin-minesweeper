package domain

abstract class PositionSelector(private val height: Int, private val width: Int) {
    abstract fun selectMinePositions(number: Int, excludedPosition: Position): List<Position>

    fun adjacentPositions(position: Position): List<Position> {
        val (i, j) = position.pair
        val adjacentI = (Integer.max(i - 1, 1)..Integer.min(i + 1, height))
        val adjacentJ = (Integer.max(j - 1, 1)..Integer.min(j + 1, width))
        return adjacentI.map { itI -> adjacentJ.map { itJ -> Position(itI, itJ) } }.flatten()
    }

    fun allPositions(iRange: IntRange = (1..height), jRange: IntRange = (1..width)): List<Position> =
        iRange.map { i -> jRange.map { j -> Position(i, j) } }.flatten()
}
