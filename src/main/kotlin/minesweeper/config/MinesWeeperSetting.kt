package minesweeper.config

import minesweeper.domain.MineCount

data class MinesWeeperSetting(
    val size: BoardSize,
    val minesCount: MineCount,
)
