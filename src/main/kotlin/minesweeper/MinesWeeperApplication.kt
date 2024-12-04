package minesweeper

import minesweeper.domain.DefaultMineGenerator
import minesweeper.domain.MinesWeeper
import minesweeper.view.input.MinesWeeperSettingView

fun main() {
    val setting = MinesWeeperSettingView.parse()
    val minesWeeper = MinesWeeper(setting, DefaultMineGenerator())
}
