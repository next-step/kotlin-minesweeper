package view

import domain.Board

object OutputView {
    private const val MINE_SYMBOL = "* "
    private const val CELL_SYMBOL = "C "

    fun notifyGameStart() {
        println("지뢰찾기 게임 시작")
    }

    fun printBoard(board: Board) {
        val cells = board.cells().allCells()
        val height = cells.maxOf { it.position.row }
        val width = cells.maxOf { it.position.column }

        (1..height).forEach { rowIndex ->
            println(buildRowSymbols(cells, rowIndex, width))
        }
    }

    private fun buildRowSymbols(
        cells: List<domain.Cell>,
        rowIndex: Int,
        width: Int,
    ): String {
        return (1..width).joinToString("") { colIndex ->
            getSymbol(cells, rowIndex, colIndex)
        }
    }

    private fun getSymbol(
        cells: List<domain.Cell>,
        row: Int,
        col: Int,
    ): String {
        val foundCell = cells.first { it.position.row == row && it.position.column == col }
        return if (foundCell.hasMine) MINE_SYMBOL else CELL_SYMBOL
    }
}
