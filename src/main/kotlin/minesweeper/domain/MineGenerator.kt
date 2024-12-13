package minesweeper.domain

import minesweeper.config.MinesWeeperSetting

interface MineGenerator {
    fun generate(setting: MinesWeeperSetting): List<Mine>
}
