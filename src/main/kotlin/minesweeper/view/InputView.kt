package minesweeper.view

import minesweeper.domain.AskType

object InputView {
    private const val ASK_HEIGHT = "높이를 입력하세요"
    private const val ASK_WIDTH = "\n너비를 입력하세요"
    private const val ASK_MINE_COUNT = "\n지뢰는 몇 개인가요?"
    private const val INVALID_INPUT_MSG = "입력값은 숫자만 가능합니다."

    fun askBoardInfo(type: AskType): Int {
        when (type) {
            AskType.HEIGHT -> println(ASK_HEIGHT)
            AskType.WIDTH -> println(ASK_WIDTH)
            AskType.MINE_COUNT -> println(ASK_MINE_COUNT)
        }

        val input = readLine()?.toIntOrNull()
        require(input != null) { INVALID_INPUT_MSG }

        return input
    }
}
