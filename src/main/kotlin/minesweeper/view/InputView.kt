package minesweeper.view

import minesweeper.domain.PositiveInt

object InputView {
    fun readBoardSetting(): BoardSetting {
        return BoardSetting(
            height = readPositiveIntWithMessage("높이를 입력하세요."),
            width = readPositiveIntWithMessage("너비를 입력하세요."),
            mineCount = readPositiveIntWithMessage("지뢰는 몇 개인가요?"),
        )
    }

    private fun readPositiveIntWithMessage(message: String): PositiveInt {
        return PositiveInt(readIntWithMessage(message))
    }

    private fun readIntWithMessage(message: String): Int {
        println(message)
        return readln().toInt()
    }
}
