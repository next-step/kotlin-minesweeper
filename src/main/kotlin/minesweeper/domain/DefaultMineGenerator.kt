package minesweeper.domain

import minesweeper.config.MinesWeeperSetting

class DefaultMineGenerator : MineGenerator {
    override fun generate(setting: MinesWeeperSetting): List<Mine> {
        val generatedMines =
            List(setting.size.height.value * setting.size.width.value) { index ->
                val point = Point(row = index / setting.size.width.value, col = index % setting.size.width.value)
                Mine(point)
            }
        return generatedMines
            .shuffled()
            .take(setting.minesCount.count)
    }
}
