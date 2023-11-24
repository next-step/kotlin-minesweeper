package view

import domain.GameSettings

class InputView {
    fun readHeight(): Int {
        println("높이를 입력하세요.")
        return readlnOrNull()?.toIntOrNull() ?: GameSettings.DEFAULT_HEIGHT
    }

    fun readWidth(): Int {
        println("너비를 입력하세요.")
        return readlnOrNull()?.toIntOrNull() ?: GameSettings.DEFAULT_WIDTH
    }

    fun readMineCount(): Int {
        println("지뢰는 몇 개인가요?")
        return readlnOrNull()?.toIntOrNull() ?: GameSettings.DEFAULT_MINE_COUNT
    }
}
