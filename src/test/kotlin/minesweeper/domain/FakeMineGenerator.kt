package minesweeper.domain

import minesweeper.config.MinesWeeperSetting

class FakeMineGenerator(
    private val points: List<Point>,
) : MineGenerator {
    override fun generate(setting: MinesWeeperSetting): List<Mine> = points.map { Mine(it) }
}
