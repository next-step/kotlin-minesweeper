package minesweeper.view

import minesweeper.domain.Cell
import minesweeper.domain.Status

object ResultView {
    private const val MINE = "*"
    private const val BLANK = "C"
    private const val SPACE = " "

    enum class Message(val context: String) {
        REQUEST_HEIGHT("높이를 입력하세요."),
        REQUEST_WIDTH("너비를 입력하세요."),
        REQUEST_MINE("지뢰는 몇 개인가요?"),
        START("지뢰찾기 게임 시작"),
        OPEN("open:"),
        PROCESSING_GAME("Processing Game."),
        WIN_GAME("Win Game."),
        LOSE_GAME("Lose Game.");
    }

    fun printMessage(message: Message) = println(message.context)

    fun printMap(cells: List<Cell>, columSize: Int) {
        cells.chunked(columSize)
            .forEach { rowCells ->
                val row = toMark(rowCells)
                println(row)
            }

        println()
    }

    private fun toMark(rowCells: List<Cell>): String =
        rowCells.joinToString(SPACE) { cell ->
            when (cell) {
                is Cell.Mine -> BLANK
                is Cell.Blank -> toBlankMark(cell)
            }
        }

    private fun toBlankMark(cell: Cell.Blank): String =
        if (cell.isOpen) cell.mineCount.toString() else BLANK

    fun printResult(status: Status) =
        when (status) {
            Status.WINNING -> println(Message.WIN_GAME)
            Status.LOSING -> println(Message.LOSE_GAME)
            Status.PROCESSING -> println(Message.PROCESSING_GAME)
        }
}
