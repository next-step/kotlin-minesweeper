package minesweeper.domain

import minesweeper.domain.point.Mine

interface MineGenerator {
    fun generate(
        height: Height,
        width: Width,
        count: MineCount,
    ): List<Mine>
}
