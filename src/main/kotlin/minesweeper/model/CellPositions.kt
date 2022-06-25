package minesweeper.model

data class CellPositions(
    private val positions: Set<CellPosition>
) : Set<CellPosition> by positions {

    fun generateShuffledPositions(): CellPositions {
        val shuffledPositions = positions.toList().shuffled()
        return CellPositions(shuffledPositions.toSet())
    }

    companion object {
        fun of(width: Int, height: Int): CellPositions =
            (0 until height).flatMap { y ->
                (0 until width).map { x -> CellPosition.of(x, y) }
            }.toSet().let(::CellPositions)
    }
}
