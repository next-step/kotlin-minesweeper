package minesweeper.domain

class Board(
    height: PositiveInt,
    width: PositiveInt,
    mineCount: PositiveInt,
) {
    private val emptyCells: Cells = Cells.empty((height * width - mineCount).value)
    private val mineCells: Cells = Cells.mine(mineCount.value)
    val cells: List<Cells> = (emptyCells + mineCells).shuffled().chunked(width.value)
}
