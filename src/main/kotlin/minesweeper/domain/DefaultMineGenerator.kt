package minesweeper.domain

import minesweeper.domain.point.Mine

class DefaultMineGenerator : MineGenerator {
    override fun generate(
        height: Height,
        width: Width,
        count: MineCount,
    ): List<Mine> {
        val points =
            List(height.value * width.value) { index ->
                val row = index / width.value
                val col = index % width.value
                Mine(row, col)
            }

        return points.shuffled().take(count.value)
    }
}
