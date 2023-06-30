package minesweeper.domain

class Board(
    height: PositiveInt,
    width: PositiveInt,
    mineCount: PositiveInt,
) {
    private val emptyCells: Cells = Cells(
        List((height * width - mineCount).value) { Cell() },
    )
    private val mineCells: Cells = Cells(
        List(mineCount.value) { Cell(isMine = true) },
    )

    val cells: List<Cells> = (emptyCells + mineCells).shuffled().chunked(width.value)
}
