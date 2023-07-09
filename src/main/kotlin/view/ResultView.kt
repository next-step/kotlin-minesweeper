package view

import domain.Cell
import domain.MineSweeperBoard
import domain.Position

object ResultView {
    fun startMessage() {
        println("지뢰찾기 게임 시작")
    }

    fun board(board: MineSweeperBoard) {
        repeat(board.height) { y ->
            repeat(board.width) { x ->
                val currentPosition = Position(x, y)

                val str = comparable(board, currentPosition)
                print(str)
                print(" ")
            }
            println()
        }
    }

    private fun comparable(board: MineSweeperBoard, currentPosition: Position): String {
        val cell = board.getCell(currentPosition)
        if (cell is Cell.MineCell) {
            return cell(cell)
        }
        return board.getMineCountAround(currentPosition).toString()
    }

    private fun cell(cell: Cell): String {
        return when (cell) {
            is Cell.MineCell -> "*"
            is Cell.NormalCell -> "C"
        }
    }
}
