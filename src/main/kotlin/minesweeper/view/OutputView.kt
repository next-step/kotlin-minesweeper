package minesweeper.view

import minesweeper.domain.GameBoard

object OutputView {

    fun printGameBoard(gameBoard: GameBoard) {
        println("지뢰찾기 게임 시작")
        gameBoard.cells.cells.groupBy { it.position.y }.forEach {
            println(it.value.joinToString(" ") { cell -> cell.cellType.point })
        }
    }
}
