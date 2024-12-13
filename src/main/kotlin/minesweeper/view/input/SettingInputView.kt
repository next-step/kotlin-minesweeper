package minesweeper.view.input

import minesweeper.common.Height
import minesweeper.common.MineCount
import minesweeper.common.Width

object SettingInputView {
    fun parse(): Triple<Height, Width, MineCount> {
        println("높이를 입력하세요.")
        val height = readln().toInt()

        println("너비를 입력하세요.")
        val width = readln().toInt()

        println("지뢰는 몇 개인가요?")
        val minesCount = readln().toInt()

        return Triple(height, width, minesCount)
    }
}
