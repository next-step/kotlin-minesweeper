package view

import domain.BoardInfo

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

    fun printMineBoard(boardInfo: BoardInfo) {
        repeat(boardInfo.boardHeight) { height ->
            printRowInfo(boardInfo, height)

            println()
        }
    }

    private fun printRowInfo(boardInfo: BoardInfo, row: Int) {
        val eachRow = boardInfo.boardItems[row]
        val rowInfoFormat = eachRow.joinToString(BLANK) { it.display }
        print(rowInfoFormat)
    }
}
