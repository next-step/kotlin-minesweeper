package minesweeper.view

import minesweeper.domain.board.MineBoard
import minesweeper.domain.cell.Cell
import minesweeper.domain.cell.Empty
import minesweeper.domain.cell.Mine

object MineBoardView {

    fun printStartOfGame(mineBoard: MineBoard) {
        println("지뢰찾기 게임 시작")
        printMineBoard(mineBoard)
    }

    private fun printMineBoard(mineBoard: MineBoard) {
        mineBoard.cells.groupBy { it.position.y }
            .forEach { (_, cells) ->
                cells.forEach(this::printShape)
                println()
            }
    }

    private fun printShape(cell: Cell) =
        when (cell) {
            is Mine -> print("* ")
            is Empty -> print("${cell.numberOfNearbyMines} ")
        }
}
