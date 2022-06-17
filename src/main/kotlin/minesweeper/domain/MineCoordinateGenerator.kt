package minesweeper.domain

import minesweeper.domain.field.Coordinate

fun interface MineCoordinateGenerator {
    fun generate(coordinates: List<Coordinate>, numberOfMine: Int): List<Coordinate>
}

object RandomMineCoordinateGenerator : MineCoordinateGenerator {
    override fun generate(coordinates: List<Coordinate>, numberOfMine: Int): List<Coordinate> =
        coordinates.shuffled().take(numberOfMine)
}
