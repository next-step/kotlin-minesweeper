package minesweeper.view

import minesweeper.domain.CellType
import minesweeper.domain.GameBoard

object OutputView {

    fun printGameBoard(gameBoard: GameBoard) {
        println("지뢰찾기 게임 시작")
        gameBoard.cells.cells.groupBy { it.position.y }.forEach {
            println(it.value.joinToString(" ") { cell -> cell.cellType.toPoint() })
        }
    }

    private fun CellType.toPoint(): String {
        return when (this) {
            CellType.NON_MINE -> "C"
            CellType.MINE -> "*"
        }
    }
}
