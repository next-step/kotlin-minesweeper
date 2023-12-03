package minesweeper.view

import minesweeper.domain.MineMapMeta

object InputView {
    fun readMineMapMeta(): MineMapMeta {
        val height = getNumber("높이를 입력하세요.")
        val width = getNumber("너비를 입력하세요.")
        val mineCount = getNumber("지뢰는 몇 개인가요?")
        return MineMapMeta(
            height = height,
            width = width,
            mineCount = mineCount
        )
    }

    private fun getNumber(consoleMsg: String): Int {
        println(consoleMsg)
        val input = readlnOrNull() ?: throw IllegalArgumentException("입력값은 공백이거나 빈 문자열일 수 없습니다.")
        return input.toIntOrNull() ?: throw IllegalArgumentException("숫자가 아닌 입력값은 들어올 수 없습니다.")
    }
}
