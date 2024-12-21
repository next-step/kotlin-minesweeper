package view

import domain.Board
import domain.Cell

object OutputView {
    private const val MINE_SYMBOL = "*"

    fun notifyGameStart() {
        println("지뢰찾기 게임 시작")
    }

    fun printBoard(board: Board) {
        buildTotalRowSymbols(board).forEach(::println)
    }

    /**
     * 전체 행을 문자열로 만들어 리스트로 반환.
     */
    private fun buildTotalRowSymbols(board: Board): List<String> {
        val cells = board.cells().allCells()
        val height = cells.maxOf { it.position.row }

        return (1..height).map { rowIndex ->
            buildRowSymbol(board, rowIndex)
        }
    }

    /**
     * 한 행을 구성하는 문자열 생성.
     */
    private fun buildRowSymbol(
        board: Board,
        rowIndex: Int,
    ): String {
        val cells = board.cells().allCells()
        val width = cells.maxOf { it.position.column }

        return (1..width).joinToString(" ") { colIndex ->
            val cell = findCell(cells, rowIndex, colIndex)
            getSymbol(board, cell)
        }
    }

    private fun findCell(
        cells: List<Cell>,
        rowIndex: Int,
        colIndex: Int,
    ): Cell {
        return cells.first { it.position.row == rowIndex && it.position.column == colIndex }
    }

    /**
     * 지뢰가 있다면 '*' 아니면 주변 지뢰 개수를 문자열로 반환.
     */
    private fun getSymbol(
        board: Board,
        cell: Cell,
    ): String {
        return if (cell.hasMine) {
            MINE_SYMBOL
        } else {
            val adjacentMines = board.cells().countAdjacentMines(cell)
            adjacentMines.toString()
        }
    }
}
