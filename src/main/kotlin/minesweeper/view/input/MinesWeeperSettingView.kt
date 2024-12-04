package minesweeper.view.input

import minesweeper.config.MinesWeeperSetting

object MinesWeeperSettingView {
    fun parse(): MinesWeeperSetting {
        println("높이를 입력하세요.")
        val height = readln().toInt()

        println("너비를 입력하세요.")
        val width = readln().toInt()

        println("지뢰는 몇 개인가요?")
        val minesCount = readln().toInt()

        return MinesWeeperSetting(
            height = height,
            width = width,
            minesCount = minesCount,
        )
    }
}
