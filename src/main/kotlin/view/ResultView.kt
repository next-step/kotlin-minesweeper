package view

import domain.Cell
import domain.MineStatus
import domain.MineSweeperBoard

object ResultView {
    fun startMessage() {
        println("지뢰찾기 게임 시작")
    }

    fun board(board: MineSweeperBoard) {
        repeat(board.boardSize.height) { y ->
            board.board.slice(y * board.boardSize.width until (y + 1) * board.boardSize.width)
                .joinToString(" ") { cell(it) }.also {
                    println(it)
                }
        }
    }

    private fun cell(cell: Cell): String {
        return when (cell.getMineStatus()) {
            MineStatus.Exists -> "*"
            MineStatus.Empty -> "C"
        }
    }
}
