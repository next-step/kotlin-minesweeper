package minesweeper.domain

import minesweeper.config.MinesWeeperSetting
import minesweeper.domain.point.Mines

class MinesWeeper(
    setting: MinesWeeperSetting,
    mineGenerator: MineGenerator,
) {
    val board: Board

    init {
        val mines = Mines(setting.height, setting.width, setting.minesCount, mineGenerator)
        board = Board(setting.height, setting.width, mines)
    }
}
