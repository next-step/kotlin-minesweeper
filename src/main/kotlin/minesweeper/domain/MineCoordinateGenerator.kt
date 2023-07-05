package minesweeper.domain

fun interface MineCoordinateGenerator {
    fun generate(mineCount: Int): Set<Coordinate>
}
