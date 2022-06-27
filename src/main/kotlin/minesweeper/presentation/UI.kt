package minesweeper.presentation

import minesweeper.domain.Board
import minesweeper.domain.Cell
import minesweeper.domain.GameMessage

object UI {

    fun drawMessage(messageType: GameMessage) {
        when (messageType) {
            GameMessage.Start -> drawStartMessage()
            GameMessage.Win -> drawWinMessage()
            GameMessage.Lose -> drawLoseMessage()
            GameMessage.CellAlreadyOpened -> drawAlreadyOpenedMessage()
            GameMessage.CellNotFound -> drawNotFoundMessage()
        }
    }

    private fun drawStartMessage() {
        println("지뢰찾기 게임 시작")
    }

    private fun drawWinMessage() {
        println("Win Game.")
    }

    private fun drawLoseMessage() {
        println("Lose Game.")
    }

    private fun drawNotFoundMessage() {
        println("좌표를 다시 입력해주세요.")
    }

    private fun drawAlreadyOpenedMessage() {
        println("이미 열려있습니다.")
    }

    fun drawBoard(board: Board) {
        board.cells.groupByColumn().forEach { (_, row) ->
            println(row.joinToString(" ") { convertCellMark(it) })
        }
    }

    private fun List<Cell>.groupByColumn(): Map<Int, List<Cell>> = groupBy(keySelector = { it.coordinate.y })

    private fun convertCellMark(cell: Cell): String {
        if (!cell.isOpened()) return "C"

        return when (cell) {
            is Cell.Mine -> "*"
            is Cell.Block -> cell.aroundMineCount.toString()
        }
    }
}
