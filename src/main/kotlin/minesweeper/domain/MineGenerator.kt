package minesweeper.domain

import minesweeper.domain.point.Mine

interface MineGenerator {
    fun generate(
        height: Int,
        width: Int,
        count: Int,
    ): List<Mine>
}
