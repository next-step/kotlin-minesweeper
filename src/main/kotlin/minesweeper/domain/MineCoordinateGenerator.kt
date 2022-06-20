package minesweeper.domain

import minesweeper.domain.field.Coordinate
import minesweeper.domain.vo.NumberOfMine

fun interface MineCoordinateGenerator {
    fun generate(coordinates: List<Coordinate>, numberOfMine: NumberOfMine): List<Coordinate>
}

object RandomMineCoordinateGenerator : MineCoordinateGenerator {
    override fun generate(coordinates: List<Coordinate>, numberOfMine: NumberOfMine): List<Coordinate> =
        coordinates.shuffled().take(numberOfMine.value)
}
