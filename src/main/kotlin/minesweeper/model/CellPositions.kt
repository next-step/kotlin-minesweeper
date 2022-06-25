package minesweeper.model

data class CellPositions(
    private val positions: List<CellPosition>
) : List<CellPosition> by positions {

    fun generateShuffledPositions(): CellPositions = CellPositions(positions.shuffled())

    companion object {
        fun of(width: Int, height: Int): CellPositions =
            (0 until height).flatMap { y ->
                (0 until width).map { x -> CellPosition.of(x, y) }
            }.let(::CellPositions)
    }
}
