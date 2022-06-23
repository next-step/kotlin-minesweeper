package minesweeper.model

data class CellPositions(
    private val positions: List<CellPosition>
): List<CellPosition> by positions {

    fun generateShuffledPositions() = CellPositions(positions.shuffled())

    companion object {
        fun of(width: Int, height: Int) =
            (0 until height).flatMap { y ->
                (0 until width).map { x -> CellPosition.of(x, y) }
            }.let(::CellPositions)
    }
}
