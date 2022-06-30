package minesweeper.view

import minesweeper.domain.board.MineBoard
import minesweeper.domain.cell.Cell
import minesweeper.domain.cell.Cell.Empty
import minesweeper.domain.cell.Cell.Mine

object MineBoardView {

    fun printStartOfGame(mineBoard: MineBoard) {
        println("지뢰찾기 게임 시작")
        printMineBoard(mineBoard)
    }

    fun printMineBoard(mineBoard: MineBoard) {
        mineBoard.cells.groupBy { it.position.y }
            .forEach { (_, cells) ->
                cells.forEach(this::printShape)
                println()
            }
    }

    private fun printShape(cell: Cell) =
        when (cell.isClosed()) {
            true -> print("C ")
            false -> print("${cell.shape()} ")
        }

    private fun Cell.shape() =
        when (this) {
            is Mine -> "*"
            is Empty -> "$numberOfNearbyMines"
        }
}
