package view

import domain.Board

object PrintView {
    private const val MESSAGE_FOR_HEIGHT = "높이를 입력하세요"
    private const val MESSAGE_FOR_WIDTH = "너비를 입력하세요"
    private const val MESSAGE_FOR_MINE = "지뢰는 몇개 인가요?"
    private const val BLANK = " "

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
        val rowInfoFormat = eachRow.cells.joinToString(BLANK) { it.display }
        print(rowInfoFormat)
    }
}
