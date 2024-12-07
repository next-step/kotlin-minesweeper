package minesweeper

object OutputView {
    private const val CELL_SEPARATOR = " "
    private const val MINE = "*"
    private const val CELL = "C"

    fun printStartMessage() {
        println("\n지뢰찾기 게임 시작")
    }

    fun printStats(
        minesweeper: Cells,
        width: Int,
    ) {
        minesweeper.cells.chunked(width).forEach { row ->
            println(row.joinToString(CELL_SEPARATOR) { cell -> display(cell.isMine) })
        }
    }

    private fun display(isMine: Boolean) = if (isMine) MINE else CELL
}
