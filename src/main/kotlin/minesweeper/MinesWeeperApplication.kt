package minesweeper

import minesweeper.domain.Height
import minesweeper.domain.MineCount
import minesweeper.domain.Width
import minesweeper.view.SettingInputView

fun main() {
    val height = Height(SettingInputView.parseHeight())
    val width = Width(SettingInputView.parseWidth())
    val mineCount = MineCount(SettingInputView.parseMineCount())
}
