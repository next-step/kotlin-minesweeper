package minesweeper.domain.game

import minesweeper.domain.data.PositiveNumber

object MineGenerator {
    fun generate(width: PositiveNumber, height: PositiveNumber, mineCount: PositiveNumber): Mines =
        (PositiveNumber.BASE_NUMBER until width.number).flatMap { row ->
            (PositiveNumber.BASE_NUMBER until height.number).map { col ->
                Coordinate(row, col)
            }
        }.shuffled().takeLast(mineCount.number).toMines()
}
