package view

import domain.Board
import domain.BoardItem

object PrintView {
    private const val MESSAGE_FOR_HEIGHT = "높이를 입력하세요"
    private const val MESSAGE_FOR_WIDTH = "너비를 입력하세요"
    private const val MESSAGE_FOR_MINE = "지뢰는 몇개 인가요?"
    private const val BLANK = " "
    private const val MINE_DISPLAY = "X"

    fun printHeightMessage() {
        println(MESSAGE_FOR_HEIGHT)
    }

    fun printWidthMessage() {
        println(MESSAGE_FOR_WIDTH)
    }

    fun printMineMessage() {
        println(MESSAGE_FOR_MINE)
    }

    fun printMineBoard(boardInfo: Board) {
        repeat(boardInfo.boardHeight) { height ->
            printRowInfo(boardInfo, height)

            println()
        }
    }

    private fun printRowInfo(boardInfo: Board, row: Int) {
        val eachRow = boardInfo.rows[row]
        val rowInfoFormat = eachRow.cells.joinToString(BLANK) {
            takeItemItemDisplay(it)
        }
        print(rowInfoFormat)
    }

    private fun takeItemItemDisplay(boardItem: BoardItem): String {
        return when (boardItem) {
            is BoardItem.Mine -> MINE_DISPLAY
            is BoardItem.Normal -> boardItem.getNearMineCount().toString()
        }
    }
}
