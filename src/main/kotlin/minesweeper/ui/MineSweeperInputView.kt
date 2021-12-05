package minesweeper.ui

import minesweeper.domain.board.BoardSettings

object MineSweeperInputView {

    fun settingsInputPrompt(): BoardSettings {
        val height = inputIntWithMessage("높이를 입력하세요")
        val width = inputIntWithMessage("너비를 입력하세요")
        val mineCounts = inputIntWithMessage("지뢰는 몇 개 인가요")
        return BoardSettings(width, height, mineCounts)
    }

    private fun inputIntWithMessage(message: String): Int {
        println(message)
        return input().toInt()
    }

    private fun input(): String = readLine() ?: ""
}
