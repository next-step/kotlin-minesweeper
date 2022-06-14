package view

import domain.BoardInfo

object PrintView {
    private const val MESSAGE_FOR_HEIGHT = "높이를 입력하세요"
    private const val MESSAGE_FOR_WIDTH = "너비를 입력하세요"
    private const val MESSAGE_FOR_MINE = "지뢰는 몇개 인가요?"
    private const val MINE_CHAR = "X"
    private const val NORMAL_CHAR = "O"
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
        repeat(boardInfo.height) { height ->
            repeat(boardInfo.width) { width ->
                if (boardInfo.mines.contains(height, width)) {
                    print(MINE_CHAR)
                } else {
                    print(NORMAL_CHAR)
                }
                print(BLANK)
            }
            println()
        }
    }
}
