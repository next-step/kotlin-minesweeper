package minesweeper.domain

class PlayableBoard(
    override val cells: Cells,
) : Board {
    init {
        require(cells.isNotEmpty()) {
            "빈 판을 생성할 수 없습니다."
        }
    }

    fun get(
        y: Int,
        x: Int,
    ): Cell? = cells[Coordinate(y, x)]

    fun countMines(
        y: Int,
        x: Int,
    ): Int = cells.countNeighboringMines(Coordinate(y, x))

    fun open(
        y: Int,
        x: Int,
    ): Board {
        val newCells = cells.open(Coordinate(y, x))
        return when {
            newCells.isAnyMineDetonated() -> MineDetonatedBoard(newCells)
            newCells.isAnyEmptyCellClosed() -> PlayableBoard(newCells)
            else -> PlayerWonBoard(newCells)
        }
    }
}
