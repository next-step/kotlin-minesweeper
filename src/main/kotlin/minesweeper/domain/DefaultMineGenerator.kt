package minesweeper.domain

import minesweeper.domain.point.Mine

class DefaultMineGenerator : MineGenerator {
    override fun generate(
        height: Int,
        width: Int,
        count: Int,
    ): List<Mine> {
        val points =
            (0 until height).flatMap { row ->
                (0 until width).map { col -> Mine(row, col) }
            }
        return points.shuffled().take(count)
    }
}
