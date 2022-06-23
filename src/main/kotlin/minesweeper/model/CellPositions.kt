package minesweeper.model

data class CellPositions(
    private var positions: List<CellPosition>
) {
    val size
        get() = positions.size

    fun shuffle() {
        positions = positions.shuffled()
    }

    operator fun get(index: Int) = positions[index]

    companion object {
        fun of(width: Int, height: Int) =
            (0 until height).flatMap { y ->
                (0 until width).map { x -> CellPosition.of(x, y) }
            }.let(::CellPositions)
    }
}
