package minesweeper.domain

import minesweeper.config.MinesWeeperSetting

class Mines(
    generator: MineGenerator,
    setting: MinesWeeperSetting,
) {
    val elements: List<Mine> = generator.generate(setting)

    operator fun contains(other: Mine): Boolean = elements.contains(other)

    fun countAroundMines(point: Point): Int =
        Direction
            .applyTo(point)
            .count { aroundPoint -> Mine(aroundPoint) in elements }
}
